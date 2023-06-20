package org.campusmolndal;

public class DatabseFacade {

    private Databse database;
    private String DbName;
    public DatabseFacade (String name){
        database = new Databse(name);

    }
    public void closeDatabase() {
        database.close();
    }
    //For Mock testing
    public void setDatabse(Databse database) {
        this.database = database;
    }

    //Crud
    public void createUser(User user) {
        database.createUser(user);
    }

    //cRud
    public User findUserByUsername(String username) {
        return database.findUserByUsername(username);
    }

    //crUd
    public void updateUser(User newUser, String oldName) {
        database.updateUser(newUser, oldName);
    }

    //cruD
    public void deleteUser(User user) {
        database.deleteUser(user);
    }
}
