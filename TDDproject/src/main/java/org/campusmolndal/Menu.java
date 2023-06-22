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
            //I start by showing the list of ToDos you have,
            // then I call option handler and pass the various options you have as arguments in the method
            showToDoLists();
            optionHandlerForToDo(optionsForTodo());
        }
    }

    public void start() {
        // Ask who you are and see if you are an old or new user
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

    // This method creates a new user with the given name and age.
    public void createUser(String name) {
        // Create new User
        try {
            System.out.println("How old are you? ");
            int age = ScannerUtil.getTheInput().nextInt();
            user = new User(name, age);
            databse.createUser(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // This method displays the user's to-do lists.
    public void showToDoLists() {
        System.out.println(user.toString());
    }

    // This method displays the tasks in a specific to-do list.
    public void showTasks(int index) {
        List<ToDo> list = user.getToDoList();
        System.out.println(list.get(index).toString());
    }

    // This method displays the available options for managing to-do lists.
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

    // This method handles the chosen option for managing to-do lists.
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

    // This method displays the available options for managing tasks in a to-do list.
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

    // This method handles the chosen option for managing tasks in a to-do list.
    public void optionHandlerForTasks(int listNumber, int choice) {
        try {
            if (1 == choice) createNewTask(listNumber);
            if (2 == choice || 3 == choice) setDoneAndNotDoneTask(listNumber, choice);
            if (4 == choice) DeleteTask(listNumber);
            if (5 == choice) {
                editTask(listNumber);
            } else {
                //Do nothing
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //Private methods start here

    // Methods for account handling

    // This method allows the user to access and modify their account settings.
    private void accountSettings() {
        System.out.println("1. Delete account");
        System.out.println("2. Change your name");
        System.out.println("3. List all users");
        int choice = ScannerUtil.getTheInput().nextInt();

        if (1 == choice) accountSettingsDeleteAccount();
        if (2 == choice) accountSettingsChangeName();
        if (3 == choice) AccountSettingsListAll();
    }

    // This method lists all users in the database.
    private void AccountSettingsListAll() {
        List<User> users = databse.listAllUsers();
        for (User user1 : users) {

            System.out.println(user1.toString());
            System.out.println("--------------");
        }
    }

    // This method allows the user to change their account name.
    private void accountSettingsChangeName() {
        System.out.println("Enter your new name: ");
        String newName = ScannerUtil.getTheInput().nextLine();
        user.setUsername(newName);
        databse.updateUser(user, olName);
    }

    // This method allows the user to delete their account.
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

    // Methods for task handling

    // This method allows the user to update the name of a specific to-do list.
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

    // This method allows the user to delete a specific to-do list.
    private void deleteToDo() {
        System.out.println("Enter list ID: ");
        showToDoLists();
        List<ToDo> list = user.getToDoList();
        int listNumber = ScannerUtil.getTheInput().nextInt();
        user.removeFromToDoList(list.remove(listNumber));
        databse.updateUser(user, olName);
        System.out.println("Done!");
    }

    // This method allows the user to open a specific to-do list and perform operations on its tasks.
    private void openToDo() {
        System.out.println("Enter list ID: ");
        int listNumber = ScannerUtil.getTheInput().nextInt();
        int taskNumber = 0;
        while (taskNumber != 6) {
            showTasks(listNumber);
            taskNumber = optionForTasks();
            optionHandlerForTasks(listNumber, taskNumber);
        }
    }

    // This method allows the user to create a new to-do list.
    private void createNewTodo() {
        ToDo todo = new ToDo();
        System.out.println("Enter list name: ");
        String name = ScannerUtil.getTheInput().nextLine();
        todo.setTitle(name);
        user.addToDoList(todo);

        databse.updateUser(user, olName);
    }

    // Methods for task handling

    // This method allows the user to edit a specific task in a to-do list.
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

    // This method allows the user to delete a specific task from a to-do list.
    private void DeleteTask(int listNumber) {
        System.out.println("Enter Enter task ID: ");
        int index = ScannerUtil.getTheInput().nextInt();
        List<ToDo> list = user.getToDoList();
        ToDo tempList = list.get(listNumber);
        tempList.deleteTask(index);

        databse.updateUser(user, olName);
    }

    // This method allows the user to mark a task as done or not done.
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

    // This method allows the user to create a new task in a to-do list.
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
