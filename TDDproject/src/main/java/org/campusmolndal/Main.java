package org.campusmolndal;

import java.util.ArrayList;

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


        ToDo list = new ToDo();
        list.setTitle("sa");
        System.out.println(list.getTitle());
        list.addTask(new Task("Dish",false));
        list.addTask(new Task("Clean",true));
        list.addTask(new Task("Go",false));
        System.out.println(list.toString());
        System.out.println("--------------");
        list.setDoneOrNotDone(0, true);
        System.out.println(list.toString());


    }
}

