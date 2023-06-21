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
        ToDo todo = new ToDo();
        todo.setTitle("My Tasks");
        todo.addTask(new Task("Task 1", false));
        todo.addTask(new Task("Task 2", true));

        String expected = "My Tasks\n" +
                "Task ID 0: Task 1 < Not done! >\n" +
                "-----------------------------\n" +
                "My Tasks\n" +
                "Task ID 1: Task 2 < Done! >\n" +
                "-----------------------------\n";

        assertEquals(expected, todo.toString());
    }
}