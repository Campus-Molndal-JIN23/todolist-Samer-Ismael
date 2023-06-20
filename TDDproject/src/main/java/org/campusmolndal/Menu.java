package org.campusmolndal;

import java.util.List;

public class Menu {

    private Databse databse;
    private User user;
    private String olName;

    public Menu(){
        this.databse = new Databse("ToDo");
        start();
        while (true) {
            showToDoLists();
            optionHandlerForToDo(optionsForTodo());
        }
    }
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
    public int optionsForTodo (){
        System.out.println("1. create new.");
        System.out.println("2. Open.");
        System.out.println("3. Delete.");
        System.out.println("4. Update.");
        System.out.println("5. Exit");
        System.out.println("-------------------");
        int number = ScannerUtil.getTheInput().nextInt();
        return number;
    }
    public int optionForTasks (){
        System.out.println("1. create new.");
        System.out.println("2. Set as done.");
        System.out.println("3. Set as not done.");
        System.out.println("4. Delete.");
        System.out.println("5. Edit.");
        System.out.println("---------------------");
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

            databse.updateUser(user,olName);

        }
        if (number == 2) {
            System.out.println("Enter the list number: ");
            int listNumber = ScannerUtil.getTheInput().nextInt();
            showTasks(listNumber);
            int taskNumber = optionForTasks();
            optionHandlerForTasks(listNumber,taskNumber);
        }
        if (number == 3) {
            System.out.println("Enter the list number: ");
            showToDoLists();
            List<ToDo> list = user.getToDoList();
            int listNumber = ScannerUtil.getTheInput().nextInt();
            user.removeFromToDoList(list.remove(listNumber));
            databse.updateUser(user, olName);
            System.out.println("Done!");
        }
        if (number == 4) {
            System.out.println("Enter the list number: ");
            showToDoLists();
            List<ToDo> list = user.getToDoList();
            int listNumber = ScannerUtil.getTheInput().nextInt();
            System.out.println("Enter the new list name: ");
            String newName = ScannerUtil.getTheInput().nextLine();
            list.get(listNumber).setTitle(newName);
            databse.updateUser(user, olName);
            System.out.println("Done!");
        }
        if (number == 5) {
            System.exit(0);
        } else {
            //do nothing, but please don't crash
        }
    }
    public void optionHandlerForTasks (int listNumber, int choice){

        if (choice == 1) {
            List<ToDo> list = user.getToDoList();
            ToDo temp = list.get(listNumber);
            System.out.println("Enter you task");
            String description = ScannerUtil.getTheInput().nextLine();
            System.out.println("Is it done (Y,N)");
            String done = ScannerUtil.getTheInput().nextLine();
            Boolean isDone = false;
            if (done.toLowerCase().equals("y")) isDone = true;
            temp.addTask(new Task(description, isDone));

            databse.updateUser(user,olName);

        }
        if (choice == 2 || choice == 3) {
            System.out.println("Enter the number of the task: ");
            int index = ScannerUtil.getTheInput().nextInt();
            List<ToDo> list = user.getToDoList();
            ToDo tempList = list.get(listNumber);
            Task tempTask = tempList.getTasks().get(index);

            if (choice == 2) tempTask.setIsDone(true);
            if (choice == 3) tempTask.setIsDone(false);

            databse.updateUser(user,olName);
        }
        if (choice == 4) {
            System.out.println("Enter the number of the task: ");
            int index = ScannerUtil.getTheInput().nextInt();
            List<ToDo> list = user.getToDoList();
            ToDo tempList = list.get(listNumber);
            tempList.deleteTask(index);

            databse.updateUser(user, olName);
        } else {
            // do nothing, but please don't crash
        }
    }
}
