package org.campusmolndal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 * ----------------------------------------------------------------------------
 * Copyright (c) 2019-2023 Marcus Medina, Campus Mölndal
 * Licensed under the Apache License, Version 3.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 * ----------------------------------------------------------------------------
 */
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Databse databse = new Databse("ToDo");

        User Samer = new User("Samer", 4);

        ToDo list1 = new ToDo();
        list1.setTitle("asdf");
        list1.addTask(new Task("d", false));

        ToDo list2 = new ToDo();
        list2.setTitle("UasdfasdfUU");
        list2.addTask(new Task("d ", false));

        Samer.addToDoList(list1);
        Samer.addToDoList(list2);

        //databse.createUser(Samer);
        //databse.updateUser(Samer,"m");
        //databse.deleteUser(Samer);


        User fromDB = databse.findUserByUsername("Samer");
        //System.out.println(fromDB.toString());

        //List<ToDo> lis = fromDB.getToDoList();
        //System.out.println(lis.get(0).toString());




    }
}

