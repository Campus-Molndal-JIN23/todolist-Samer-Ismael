package org.campusmolndal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;
    private ToDo toDo1;
    private ToDo toDo2;

    @BeforeEach
    public void setUp() {
        user = new User("Samer", 30);
        toDo1 = new ToDo();
        toDo2 = new ToDo();
    }

    @Test
    public void testGetUsername() {
        assertEquals("Samer", user.getUsername());
    }

    @Test
    public void testGetAge() {
        assertEquals(30, user.getAge());
    }

    @Test
    public void testGetToDoList() {
        List<ToDo> toDoList = user.getToDoList();
        assertNotNull(toDoList);
        assertEquals(0, toDoList.size());
    }

    @Test
    public void testAddToDoList() {
        user.addToDoList(toDo1);
        List<ToDo> toDoList = user.getToDoList();
        assertNotNull(toDoList);
        assertEquals(1, toDoList.size());
        assertTrue(toDoList.contains(toDo1));
    }

    @Test
    public void testRemoveFromToDoList() {
        user.addToDoList(toDo1);
        user.addToDoList(toDo2);
        user.removeFromToDoList(toDo1);
        List<ToDo> toDoList = user.getToDoList();
        assertNotNull(toDoList);
        assertEquals(1, toDoList.size());
        assertFalse(toDoList.contains(toDo1));
        assertTrue(toDoList.contains(toDo2));
    }

    @Test
    public void testToString() {
        ToDo toDo1 = new ToDo();
        toDo1.setTitle("Task 1");
        ToDo toDo2 = new ToDo();
        toDo2.setTitle("Task 2");

        user.addToDoList(toDo1);
        user.addToDoList(toDo2);

        StringBuilder expectedOutput = new StringBuilder();
        expectedOutput.append("User: ").append(user.getUsername()).append("\n");
        expectedOutput.append("Age: ").append(user.getAge()).append("\n");
        expectedOutput.append("To-Do Lists:\n");
        int counter = 0;
        for (ToDo toDo : user.getToDoList()) {
            expectedOutput.append(counter++).append(". ").append(toDo.getTitle()).append("\n");
        }

        assertEquals(expectedOutput.toString(), user.toString());
    }
}