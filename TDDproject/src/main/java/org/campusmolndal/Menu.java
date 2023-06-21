package org.campusmolndal;

import java.util.List;

public class Menu {

    private final DatabseFacade databse;
    private User user;
    private String olName;

    public Menu() {
        databse = new DatabseFacade("ToDo");
        start();
        while (true) {
            //Jag börjar med att visa lista med ToDos man har,
            // sen kallar jag option hanterare och passerar jag de olika options man har som argument i metoden
            showToDoLists();
            optionHandlerForToDo(optionsForTodo());
        }
    }

    public void start() {
        // Fråga vem man är och se om man är gammal eller ny användare
        try {
            System.out.println("Welcome, Enter your name or 'Exit' if you want to exit: ");
            String name = ScannerUtil.getTheInput().nextLine();
            if ("exit".equals(name.toLowerCase())) System.exit(0);

            if (null == databse.findUserByUsername(name)) {
                System.out.println("Hmmm I see.... new user ");
                createUser(name);
            } else {
                user = databse.findUserByUsername(name);
            }
            olName = user.getUsername();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void createUser(String name) {
        // Skapa ny användare om man är ny
        try {
            System.out.println("How old are you? ");
            int age = ScannerUtil.getTheInput().nextInt();
            user = new User(name, age);
            databse.createUser(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void showToDoLists() {
        System.out.println(user.toString());
    }

    public void showTasks(int index) {
        List<ToDo> list = user.getToDoList();
        System.out.println(list.get(index).toString());
    }

    public int optionsForTodo() {
        System.out.println("1. create new.");
        System.out.println("2. Open.");
        System.out.println("3. Delete.");
        System.out.println("4. Update.");
        System.out.println("5. Account settings.");
        System.out.println("6. Exit");
        System.out.println("-------------------");
        int number = 0;
        try {
            number = ScannerUtil.getTheInput().nextInt();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return number;
    }

    public void optionHandlerForToDo(int number) {
        try {
            if (1 == number) createNewTodo();
            if (2 == number) openToDo();
            if (3 == number) deleteToDo();
            if (4 == number) updateToDo();
            if (5 == number) accountSettings();
            if (6 == number) {
                System.exit(0);
            } else {
                //do nothing, but please don't crash
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int optionForTasks() {
        System.out.println("1. create new.");
        System.out.println("2. Set as done.");
        System.out.println("3. Set as not done.");
        System.out.println("4. Delete.");
        System.out.println("5. Edit.");
        System.out.println("6. Go back.");
        System.out.println("---------------------");
        int number = 0;
        try {
            number = ScannerUtil.getTheInput().nextInt();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return number;
    }

    public void optionHandlerForTasks(int listNumber, int choice) {
        try {
            if (1 == choice) createNewTask(listNumber);
            if (2 == choice || 3 == choice) setDoneAndNotDoneTask(listNumber, choice);
            if (4 == choice) DeleteTask(listNumber);
            if (5 == choice) {
                editTask(listNumber);
            } else {
                // do nothing
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Här börjar alla privata metoder.
    private void accountSettings() {
        System.out.println("1. Delete account");
        System.out.println("2. Change your name");
        System.out.println("3. List all users");
        int choice = ScannerUtil.getTheInput().nextInt();

        if (1 == choice) accountSettingsDeleteAccount();
        if (2 == choice) accountSettingsChangeName();
        if (3 == choice) AccountSettingsListAll();
    }

    private void AccountSettingsListAll() {
        List<User> users = databse.listAllUsers();
        for (User user1 : users) {

            System.out.println(user1.toString());
            System.out.println("--------------");
        }
    }

    private void accountSettingsChangeName() {
        System.out.println("Enter your new name: ");
        String newName = ScannerUtil.getTheInput().nextLine();
        user.setUsername(newName);
        databse.updateUser(user, olName);
    }

    private void accountSettingsDeleteAccount() {
        System.out.println("Are you shore you want to delete it (Y,N)");
        String answer = ScannerUtil.getTheInput().nextLine();
        if ("y".equals(answer.toLowerCase())) {
            databse.deleteUser(user);
            var menu = new Menu();
        } else {
            //Do nothing
        }
    }

    private void updateToDo() {
        System.out.println("Enter list ID: ");
        showToDoLists();
        List<ToDo> list = user.getToDoList();
        int listNumber = ScannerUtil.getTheInput().nextInt();
        System.out.println("Enter the new list name: ");
        String newName = ScannerUtil.getTheInput().nextLine();
        list.get(listNumber).setTitle(newName);
        databse.updateUser(user, olName);
        System.out.println("Done!");
    }

    private void deleteToDo() {
        System.out.println("Enter list ID: ");
        showToDoLists();
        List<ToDo> list = user.getToDoList();
        int listNumber = ScannerUtil.getTheInput().nextInt();
        user.removeFromToDoList(list.remove(listNumber));
        databse.updateUser(user, olName);
        System.out.println("Done!");
    }

    private void openToDo() {
        System.out.println("Enter list ID: ");
        int listNumber = ScannerUtil.getTheInput().nextInt();
        showTasks(listNumber);
        int taskNumber = optionForTasks();
        optionHandlerForTasks(listNumber, taskNumber);
    }

    private void createNewTodo() {
        ToDo todo = new ToDo();
        System.out.println("Enter list ID: ");
        String name = ScannerUtil.getTheInput().nextLine();
        todo.setTitle(name);
        user.addToDoList(todo);

        databse.updateUser(user, olName);
    }

    private void editTask(int listNumber) {
        System.out.println("Enter Enter task ID: ");
        int index = ScannerUtil.getTheInput().nextInt();
        List<ToDo> list = user.getToDoList();
        ToDo tempList = list.get(listNumber);

        System.out.println("Enter your new text: ");
        String newText = ScannerUtil.getTheInput().nextLine();
        System.out.println("Is it done? (Y,N)");
        String done = ScannerUtil.getTheInput().nextLine();
        Boolean isDone = false;
        if ("y".equals(done.toLowerCase())) isDone = true;
        Task tempTask = new Task(newText, isDone);
        tempList.updateTask(index, tempTask);

        databse.updateUser(user, olName);
    }

    private void DeleteTask(int listNumber) {
        System.out.println("Enter Enter task ID: ");
        int index = ScannerUtil.getTheInput().nextInt();
        List<ToDo> list = user.getToDoList();
        ToDo tempList = list.get(listNumber);
        tempList.deleteTask(index);

        databse.updateUser(user, olName);
    }

    private void setDoneAndNotDoneTask(int listNumber, int choice) {
        System.out.println("Enter task ID: ");
        int index = ScannerUtil.getTheInput().nextInt();
        List<ToDo> list = user.getToDoList();
        ToDo tempList = list.get(listNumber);
        Task tempTask = tempList.getTasks().get(index);

        if (2 == choice) tempTask.setIsDone(true);
        if (3 == choice) tempTask.setIsDone(false);

        databse.updateUser(user, olName);
    }

    private void createNewTask(int listNumber) {
        Boolean exit = true;
        do {
            List<ToDo> list = user.getToDoList();
            ToDo temp = list.get(listNumber);
            System.out.println("Enter you tasks, or type 'Done' to leave this meny");
            String description = ScannerUtil.getTheInput().nextLine();
            if (description.toLowerCase().equals("done")) exit = false;
            if (!description.toLowerCase().equals("done")) {
                System.out.println("Do you want to set it to done? (Y,N)");
                String done = ScannerUtil.getTheInput().nextLine();
                Boolean isDone = false;
                if ("y".equals(done.toLowerCase())) isDone = true;
                temp.addTask(new Task(description, isDone));

                databse.updateUser(user, olName);
            }
        } while (exit);
    }
}
