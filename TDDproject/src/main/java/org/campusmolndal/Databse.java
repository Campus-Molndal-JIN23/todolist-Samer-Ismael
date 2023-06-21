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
    private final String DBName = "ToDoDatabase";
    private final String userCollection = "lusers";
    private final MongoClient mongoClient;
    private final MongoDatabase database;

    public Databse(String DBName) {
        this.mongoClient = MongoClients.create(this.connString);
        this.database = this.mongoClient.getDatabase(DBName);
    }

    public void close() {
        this.mongoClient.close();
    }

    public String getDBName() {
        return DBName;
    }

    public void createUser(User user) {
        // för att lägga till en User så behöver man loppa igenom todo listan
        //  och sen för varje lista ska man göra en loop som hämnat alla tasks i den listan.
        try {
            MongoCollection<Document> collection = this.database.getCollection(this.userCollection);
            List<Document> toDoListsArray = new ArrayList<>();
            for (ToDo toDoList : user.getToDoList()) {
                List<Document> tasksArray = new ArrayList<>();
                for (Task task : toDoList.getTasks()) {
                    // För varje task i en todo lista skapar vi document
                    Document taskDocument = new Document()
                            .append("description", task.getDescription())
                            .append("isDone", task.isDone());
                    tasksArray.add(taskDocument);
                }
                //För varje todo lista i User klassen skapar vi Document
                Document toDoListDocument = new Document()
                        .append("title", toDoList.getTitle())
                        .append("tasks", tasksArray);

                toDoListsArray.add(toDoListDocument);
            }
            // Nu skapar vi en User Document men de andra två listor i den.
            Document userDocument = new Document()
                    .append("_id", new ObjectId())
                    .append("username", user.getUsername())
                    .append("age", user.getAge())
                    .append("toDoList", toDoListsArray);

            //in i databasen i 20 minuter på 250 grader.
            collection.insertOne(userDocument);
        } catch (MongoException e) {
            System.out.println(e.getMessage());
        }
    }

    public User findUserByUsername(String username) {
        try {
            MongoCollection<Document> collection = this.database.getCollection(this.userCollection);
            Document query = new Document("username", username);
            Document userDocument = collection.find(query).first();
            if (null != userDocument) {
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
            } else {
                return null;
            }
        } catch (MongoException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void updateUser(User newUser, String oldName) {
        try {
            User temp = this.findUserByUsername(oldName);
            if (null != temp) {
                // Skapar temp här för att kunna hitta den användaren som ska uppdateras.
                // Och se till att den finns i databasen

                MongoCollection<Document> collection = this.database.getCollection(this.userCollection);
                Document query = new Document("username", temp.getUsername());

                List<Document> toDoListsArray = new ArrayList<>();
                for (ToDo toDoList : newUser.getToDoList()) {
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
                        .append("username", newUser.getUsername())
                        .append("age", newUser.getAge())
                        .append("toDoList", toDoListsArray);

                Document update = new Document("$set", userDocument);
                collection.updateOne(query, update);
            }
        } catch (MongoException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteUser(User user) {
        MongoCollection<Document> collection = this.database.getCollection(this.userCollection);
        Document query = new Document("username", user.getUsername());
        collection.deleteOne(query);
    }
}
