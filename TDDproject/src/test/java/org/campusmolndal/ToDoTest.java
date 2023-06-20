package org.campusmolndal;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    private ToDo todo;

    @BeforeEach
    public void setUp() {
        todo = new ToDo();
    }

    @Test
    public void testGetTitle() {
        String title = "lsta";
        todo.setTitle(title);
        Assert.assertEquals(title, todo.getTitle());
    }

    @Test
    public void testAddTask() {
        Task task = new Task("test", false);
        todo.addTask(task);
        Assert.assertEquals(1, todo.getTasks().size());
        Assert.assertEquals(task, todo.getTasks().get(0));
    }

    @Test
    public void testUpdateTask() {
        Task task = new Task("test", false);
        todo.addTask(task);
        Task updatedTask = new Task("Updated task", true);
        todo.updateTask(0, updatedTask);
        Assert.assertEquals(updatedTask, todo.getTasks().get(0));
    }

    @Test
    public void testSetDoneOrNotDone() {
        Task task = new Task("test", false);
        todo.addTask(task);
        todo.setDoneOrNotDone(0, true);
        Assert.assertTrue(todo.getTasks().get(0).isDone());
    }

    @Test
    public void testDeleteTask() {
        Task task = new Task("test", false);
        todo.addTask(task);
        todo.deleteTask(0);
        Assert.assertEquals(0, todo.getTasks().size());
    }

    @Test
    public void testToString() {
        Task task1 = new Task("Task 1", true);
        Task task2 = new Task("Task 2", false);
        todo.addTask(task1);
        todo.addTask(task2);

        StringBuilder expectedOutput = new StringBuilder();
        int counter = 0;
        for (Task task : todo.getTasks()) {
            expectedOutput.append(todo.getTitle()).append("\n");
            expectedOutput.append("Task " + counter++ + ": ").append(task.getDescription()).append("\n");
            expectedOutput.append("Is Done: ").append(task.isDone()).append("\n");
            expectedOutput.append("-----------------------------\n");
        }
        Assert.assertEquals(expectedOutput.toString(), todo.toString());
    }
}