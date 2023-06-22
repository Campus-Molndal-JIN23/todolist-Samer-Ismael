package org.campusmolndal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DatabseFacadeTest {

    private DatabseFacade databaseFacade;
    private Databse mockedDatabase;

    @BeforeEach
    public void setup() {
        mockedDatabase = mock(Databse.class);
        databaseFacade = new DatabseFacade("name");
        databaseFacade.setDatabse(mockedDatabase);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        databaseFacade.createUser(user);
        // Verifies that the 'createUser' method is called on 'mocked Database' with the 'user' argument.
        verify(mockedDatabase).createUser(user);
    }

    @Test
    public void testFindUserByUsername() {
        String username = "Samer";
        databaseFacade.findUserByUsername(username);
        verify(mockedDatabase).findUserByUsername(username);
    }

    @Test
    public void testUpdateUser() {
        User newUser = new User();
        String oldName = "Lars";
        databaseFacade.updateUser(newUser, oldName);
        verify(mockedDatabase).updateUser(newUser, oldName);
    }

    @Test
    public void testDeleteUser() {
        User user = new User();
        databaseFacade.deleteUser(user);
        verify(mockedDatabase).deleteUser(user);
    }
}