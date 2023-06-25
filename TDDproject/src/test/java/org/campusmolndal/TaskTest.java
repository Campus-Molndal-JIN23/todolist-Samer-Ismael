package org.campusmolndal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private Task task;

    @BeforeEach
    public void setUp() {
        String description = "test";
        boolean isDone = false;
        task = new Task(description, isDone);
    }

    @Test
    public void testGetDescription() {
        String description = "test";
        assertEquals(description, task.getDescription());
    }

    @Test
    public void testSetDescription() {
        String description = "test";
        task.setDescription(description);
        assertEquals(description, task.getDescription());
    }

    @Test
    public void testIsDone() {
        assertFalse(task.isDone());
    }

    @Test
    public void testSetIsDone() {
        task.setIsDone(true);
        assertTrue(task.isDone());
    }
}