package org.campusmolndal;

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

    //Crud
    public void createUser(User user) {
        this.database.createUser(user);
    }

    //cRud
    public User findUserByUsername(String username) {
        return this.database.findUserByUsername(username);
    }

    //crUd
    public void updateUser(User newUser, String oldName) {
        this.database.updateUser(newUser, oldName);
    }

    //cruD
    public void deleteUser(User user) {
        this.database.deleteUser(user);
    }
}
