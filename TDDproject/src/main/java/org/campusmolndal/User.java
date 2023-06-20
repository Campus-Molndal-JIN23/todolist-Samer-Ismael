package org.campusmolndal;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private int age;
    private List<ToDo> toDoList;

    public User() {
        this.toDoList = new ArrayList<>();
    }

    public User(String username, int age) {
        this.username = username;
        this.age = age;
        this.toDoList = new ArrayList<>();
    }

    // for updating user
    public User(String username, int age, List<ToDo> toDoList) {
        this.username = username;
        this.age = age;
        this.toDoList = toDoList;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }

    public List<ToDo> getToDoList() {
        return toDoList;
    }

    public void addToDoList(ToDo toDo) {
        toDoList.add(toDo);
    }

    public void removeFromToDoList(ToDo toDo) {
        toDoList.remove(toDo);
    }

    @Override
    public String toString() {
        int counter = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("User: ").append(username).append("\n");
        sb.append("Age: ").append(age).append("\n");
        sb.append("To-Do Lists:\n");
        for (ToDo toDo : toDoList) {
            sb.append(counter++ + ". " +toDo.getTitle()).append("\n");
        }
        return sb.toString();
    }
}
