package org.campusmolndal;

import java.util.List;

public class Menu {

    private Databse databse;
    private User user;
    private String olName;
    public void start (){

        System.out.println("Welcome, Enter your name");
        String name = ScannerUtil.getTheInput().nextLine();

        if (databse.findUserByUsername(name) == null) {
            System.out.println("Hmmm I see.... new user ");
            createUser();
        } else {
            user = databse.findUserByUsername(name);
        }
        olName = user.getUsername();
    }
    public void createUser (){
        System.out.println("Whats your name? ");
        String name = ScannerUtil.getTheInput().nextLine();
        System.out.println("How old are you? ");
        int age = ScannerUtil.getTheInput().nextInt();
        user = new User(name,age);
        databse.createUser(user);
    }
    public void showToDoLists (){
        System.out.println(user.toString());
    }
    public void showTasks (int index){
        List<ToDo> list = user.getToDoList();
        System.out.println(list.get(index).toString());
    }
    public int options (){
        System.out.println("1. create new.");
        System.out.println("2. Open.");
        System.out.println("3. Delete.");
        System.out.println("4. Update.");
        System.out.println("-------------------");
        int number = ScannerUtil.getTheInput().nextInt();
        return number;
    }
    public void optionHandlerForToDo (int number){

        if (number == 1) {
            ToDo todo = new ToDo();
            System.out.println("Enter list name: ");
            String name = ScannerUtil.getTheInput().nextLine();
            todo.setTitle(name);
            user.addToDoList(todo);

        }
        if (number == 2) {
            System.out.println("Enter the list number: ");
            int listNumber = ScannerUtil.getTheInput().nextInt();
            showTasks(listNumber);
        }
        if (number == 3) {
            System.out.println("Enter the list number: ");
            showToDoLists();
            List<ToDo> list = user.getToDoList();
            int listNumber = ScannerUtil.getTheInput().nextInt();
            user.removeFromToDoList(list.remove(listNumber));
        }
        if (number == 4) {
            System.out.println("Enter the list number: ");
            showToDoLists();
            List<ToDo> list = user.getToDoList();
            int listNumber = ScannerUtil.getTheInput().nextInt();
            String newName = ScannerUtil.getTheInput().nextLine();
            list.get(listNumber).setTitle(newName);

        }

    }
}
