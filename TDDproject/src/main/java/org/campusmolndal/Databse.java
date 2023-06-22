package org.campusmolndal;

import com.mongodb.MongoException;
import com.mongodb.client.*;
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
        // To add a User, we need to iterate through the to-do list
        // and for each list, iterate through all the tasks in that list.
        try {
            MongoCollection<Document> collection = this.database.getCollection(this.userCollection);
            List<Document> toDoListsArray = new ArrayList<>();
            for (ToDo toDoList : user.getToDoList()) {
                List<Document> tasksArray = new ArrayList<>();
                for (Task task : toDoList.getTasks()) {
                    // For each task in a to-do list, we create a document
                    Document taskDocument = new Document()
                            .append("description", task.getDescription())
                            .append("isDone", task.isDone());
                    tasksArray.add(taskDocument);
                }
                // For each to-do list in the User class, we create a Document
                Document toDoListDocument = new Document()
                        .append("title", toDoList.getTitle())
                        .append("tasks", tasksArray);

                toDoListsArray.add(toDoListDocument);
            }
            // Now we create a User Document with the other two lists inside it.
            Document userDocument = new Document()
                    .append("_id", new ObjectId())
                    .append("username", user.getUsername())
                    .append("age", user.getAge())
                    .append("toDoList", toDoListsArray);
            // Insert the userDocument into the database at 250 degrees for 20 minutes.
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
                // Retrieve the username and age from the userDocument
                String foundUsername = userDocument.getString("username");
                int age = userDocument.getInteger("age");
                List<ToDo> toDoList = new ArrayList<>();
                List<Document> toDoListsArray = (List<Document>) userDocument.get("toDoList");
                for (Document toDoListDocument : toDoListsArray) {

                    // Retrieve the title of the to-do list
                    String title = toDoListDocument.getString("title");
                    List<Task> tasks = new ArrayList<>();
                    List<Document> tasksArray = (List<Document>) toDoListDocument.get("tasks");
                    for (Document taskDocument : tasksArray) {

                        // Retrieve the description and isDone status of each task
                        String description = taskDocument.getString("description");
                        boolean isDone = taskDocument.getBoolean("isDone");
                        tasks.add(new Task(description, isDone));
                    }
                    toDoList.add(new ToDo(title, tasks)); // Create a new ToDo object with the retrieved title and tasks
                }
                return new User(foundUsername, age, toDoList); // Create a new User object with the retrieved username, age, and to-do list
            } else {
                return null; // If no userDocument is found, return null
            }
        } catch (MongoException e) {
            System.out.println(e.getMessage()); // Print any MongoDB-related exception messages
            return null; // Return null in case of an exception
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
    public List<User> listAllUsers() {
        MongoCollection<Document> collection = this.database.getCollection(this.userCollection);
        FindIterable<Document> documents = collection.find();

        List<User> userList = new ArrayList<>();
        for (Document document : documents) {
            String username = document.getString("username");

            User user = findUserByUsername(username);
            userList.add(user);
        }
        return userList;
    }
}
