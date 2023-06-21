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
        User user = new User("JohnDoe", 25);
        ToDo todo1 = new ToDo();
        todo1.setTitle("Shopping");
        ToDo todo2 = new ToDo();
        todo2.setTitle("Cleaning");
        user.addToDoList(todo1);
        user.addToDoList(todo2);

        String expected = "User: JohnDoe\n" +
                "Age: 25\n" +
                "To-Do Lists:\n" +
                "------------------\n" +
                "List ID 0: Shopping\n" +
                "List ID 1: Cleaning\n";

        assertEquals(expected, user.toString());
    }
}