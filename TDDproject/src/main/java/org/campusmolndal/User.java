package org.campusmolndal;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private int age;
    private final List<ToDo> toDoList;

    public User() {
        toDoList = new ArrayList<>();
    }

    public User(String username, int age) {
        this.username = username;
        this.age = age;
        toDoList = new ArrayList<>();
    }

    // for updating user
    public User(String username, int age, List<ToDo> toDoList) {
        this.username = username;
        this.age = age;
        this.toDoList = toDoList;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return this.age;
    }

    public List<ToDo> getToDoList() {
        return this.toDoList;
    }

    public void addToDoList(ToDo toDo) {
        this.toDoList.add(toDo);
    }

    public void removeFromToDoList(ToDo toDo) {
        this.toDoList.remove(toDo);
    }

    @Override
    public String toString() {
        int counter = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("User: ").append(this.username).append("\n");
        sb.append("Age: ").append(this.age).append("\n");
        sb.append("To-Do Lists:");
        sb.append("\n------------------\n");
        for (ToDo toDo : toDoList) {
            sb.append("List ID " + counter + ":" + toDo.getTitle()).append("\n");
            counter++;
        }
        if (toDoList.isEmpty()) System.out.println("You dont have any lists. ");
        return sb.toString();
    }
}
