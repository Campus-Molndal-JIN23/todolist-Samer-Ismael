package org.campusmolndal;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class Databse {
    private final String connString = "mongodb://localhost:27017";
    private final String DBName = "ToDo Database";
    private final String userCollection = "lusers";
    private MongoClient mongoClient;
    private MongoDatabase database;

    public Databse (String DBName) {
        mongoClient = MongoClients.create(connString);
        database = mongoClient.getDatabase(DBName);
    }

    public void close() {
        mongoClient.close();
    }

    public void createUser(User user) {
        try {
            MongoCollection<Document> collection = database.getCollection(userCollection);
            List<Document> toDoListsArray = new ArrayList<>();
            for (ToDo toDoList : user.getToDoList()) {
                List<Document> tasksArray = new ArrayList<>();
                for (Task task : toDoList.getTasks()) {
                    Document taskDocument = new Document()
                            .append("description", task.getDescription())
                            .append("isDone", task.isDone());
                    tasksArray.add(taskDocument);
                }

                Document toDoListDocument = new Document()
                        .append("title", toDoList.getTitle())
                        .append("tasks", tasksArray);

                toDoListsArray.add(toDoListDocument);
            }

            Document userDocument = new Document()
                    .append("_id", new ObjectId())
                    .append("username", user.getUsername())
                    .append("age", user.getAge())
                    .append("toDoList", toDoListsArray);

            collection.insertOne(userDocument);
        } catch (MongoException e) {
            System.out.println(e.getMessage());
        }
    }
    public User findUserByUsername(String username) {
        try {
            MongoCollection<Document> collection = database.getCollection(userCollection);
            Document query = new Document("username", username);
            Document userDocument = collection.find(query).first();
            if (userDocument != null) {
                String foundUsername = userDocument.getString("username");
                int age = userDocument.getInteger("age");

                List<ToDo> toDoList = new ArrayList<>();
                List<Document> toDoListsArray = (List<Document>) userDocument.get("toDoList");
                for (Document toDoListDocument : toDoListsArray) {
                    String title = toDoListDocument.getString("title");

                    List<Task> tasks = new ArrayList<>();
                    List<Document> tasksArray = (List<Document>) toDoListDocument.get("tasks");
                    for (Document taskDocument : tasksArray) {
                        String description = taskDocument.getString("description");
                        boolean isDone = taskDocument.getBoolean("isDone");
                        tasks.add(new Task(description, isDone));
                    }

                    toDoList.add(new ToDo(title, tasks));
                }
                return new User(foundUsername, age, toDoList);
            }
        } catch (MongoException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }

    public void updateUser(User user) {
        MongoCollection<Document> collection = database.getCollection(userCollection);
        Document query = new Document("username", user.getUsername());
        Document update = new Document("$set", new Document("age", user.getAge()));
        collection.updateOne(query, update);
    }

    public void deleteUser(User user) {
        MongoCollection<Document> collection = database.getCollection(userCollection);
        Document query = new Document("username", user.getUsername());
        collection.deleteOne(query);
    }
}
