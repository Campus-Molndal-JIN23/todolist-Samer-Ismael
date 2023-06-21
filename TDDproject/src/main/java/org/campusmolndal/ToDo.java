package org.campusmolndal;

import java.util.ArrayList;
import java.util.List;

public class ToDo {
    private String title;
    private final List<Task> tasks;

    public ToDo() {
        tasks = new ArrayList<>();
    }

    // for updating to do
    public ToDo(String title, List<Task> tasks) {
        this.tasks = tasks;
        this.title = title;

    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void updateTask(int index, Task updatedTask) {
        if (0 <= index && index < this.tasks.size()) {
            this.tasks.set(index, updatedTask);
        }
    }

    public void setDoneOrNotDone(int index, boolean done) {
        if (0 <= index && index < this.tasks.size()) {
            Task task = this.tasks.get(index);
            task.setIsDone(done);
        }
    }

    public void deleteTask(int index) {
        if (0 <= index && index < this.tasks.size()) {
            this.tasks.remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        sb.append(title).append("\n");
        for (Task task : tasks) {
            sb.append("Task ID " + counter + ": ").append(task.getDescription()).append(" " + isDoneConvert(task.isDone()));
            counter++;
            sb.append("\n");
        }
        sb.append("-----------------------------\n");
        if (tasks.isEmpty()) sb.append("You dont have any tasks. ");
        return sb.toString();
    }
    private String isDoneConvert (Boolean isDone){
        String word = "< Not done! >";
        if (isDone == true) word = "< Done! >";
        return word;
    }
}
