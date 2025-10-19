package com.keyin;

/*
 * Description: Datastructures and Algorithms - Midterm Sprint: Main class to run the User and Task Management System
 * Author: Joseph Gallant
 * Date: October 18, 2025
 */

import com.keyin.TaskManagement.TaskService;
import com.keyin.UserManagement.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int LOGIN_OPTION = 1;
        final int REGISTER_OPTION = 2;
        final int EXIT_OPTION = 3;
        int menuOption;

        Scanner scanner = new Scanner(System.in);

        UserService userService = new UserService();
        TaskService taskService = new TaskService();

        do {
            System.out.println("\nWelcome to the Task Management System\n");
            System.out.println("----------------------------------------------");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.println("----------------------------------------------");
            System.out.print("Please make a selection: ");

            // TODO Input validation
            menuOption = scanner.nextInt();

            switch (menuOption) {
                case LOGIN_OPTION:
                    loginMenu(scanner, userService);

                    if (userService.getLoggedInUser() != null) {
                        taskManagementMenu(scanner, userService, taskService);
                    }
                    break;
                case REGISTER_OPTION:
                    registerMenu(scanner, userService);
                    break;
                case EXIT_OPTION:
                    System.out.println("\nThank you for using the Task Management System. Have a good day!");
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }

        } while (menuOption != EXIT_OPTION);
    }

    private static void loginMenu(Scanner scanner, UserService userService) {
        String username;
        String password;

        System.out.println("\nEnter your credentials to log in.\n");

        // TODO Input validation
        System.out.print("Username: ");
        username = scanner.next();

        System.out.print("Password: ");
        password = scanner.next();

        userService.login(username, password);
    }

    private static void registerMenu(Scanner scanner, UserService userService) {
        String username;
        String password;
        String firstName;
        String lastName;
        String email;
        System.out.println("\nEnter your details to register.\n");

        // TODO Input validation
        System.out.print("Username: ");
        username = scanner.next();

        System.out.print("Password: ");
        password = scanner.next();

        System.out.print("First Name: ");
        firstName = scanner.next();

        System.out.print("Last Name: ");
        lastName = scanner.next();

        System.out.print("Email: ");
        email = scanner.next();

        userService.register(username, password, firstName, lastName, email);
    }

    private static void taskManagementMenu(Scanner scanner, UserService userService, TaskService taskService) {
        // TODO Implement task management menu
    }
}