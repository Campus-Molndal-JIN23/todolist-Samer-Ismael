package org.campusmolndal;

import java.util.ArrayList;
import java.util.List;

public class ToDo {
    private String title;
    private List<Task> tasks;

    public ToDo() {
        this.tasks = new ArrayList<>();
    }
    // for updating to do
    public ToDo (String title, List<Task> tasks){
        this.tasks = tasks;
        this.title = title;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
    public void updateTask(int index, Task updatedTask) {
        if (index >= 0 && index < tasks.size()) {
            tasks.set(index, updatedTask);
        }
    }
     public void setDoneOrNotDone (int index, boolean done){
         if (index >= 0 && index < tasks.size()) {
             Task task = tasks.get(index);
             task.setIsDone(done);
         }
     }
    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (Task task : tasks) {
            sb.append(counter++ + ". " + getTitle()).append("\n");
            sb.append("Description: ").append(task.getDescription()).append("\n");
            sb.append("Is Done: ").append(task.isDone()).append("\n");
            sb.append("-----------------------------\n");
        }
        return sb.toString();
    }
}
