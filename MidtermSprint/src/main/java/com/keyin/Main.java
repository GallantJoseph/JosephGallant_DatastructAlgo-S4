package com.keyin;

/*
 * Description: Datastructures and Algorithms - Midterm Sprint: Main class to run the User and Task Management System
 * Author: Joseph Gallant
 * Date: October 18, 2025
 */

import com.keyin.TaskManagement.TaskService;
import com.keyin.UserManagement.UserService;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserService();
        TaskService taskService = new TaskService();

        System.out.println("Welcome to the User and Task Management System\n");
    }
}