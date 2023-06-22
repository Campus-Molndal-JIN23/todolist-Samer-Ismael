package org.campusmolndal;

import java.util.List;

public class DatabseFacade {

    private Databse database;
    private String DbName;

    public DatabseFacade(String name) {
        this.database = new Databse(name);

    }

    public void closeDatabase() {
        this.database.close();
    }

    //For Mock testing
    public void setDatabse(Databse database) {
        this.database = database;
    }

    //Crudl
    public void createUser(User user) {
        this.database.createUser(user);
    }

    //cRudl
    public User findUserByUsername(String username) {
        return this.database.findUserByUsername(username);
    }

    //crUdl
    public void updateUser(User newUser, String oldName) {
        this.database.updateUser(newUser, oldName);
    }

    //cruDl
    public void deleteUser(User user) {
        this.database.deleteUser(user);
    }

    //crudL
    public List<User> listAllUsers (){
        List<User> users = this.database.listAllUsers();
        return users;
    }
}
