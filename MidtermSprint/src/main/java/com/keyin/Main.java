package com.keyin;

/*
 * Description: Datastructures and Algorithms - Midterm Sprint: Main class to run the User and Task Management System
 * Author: Joseph Gallant
 * Dates: October 18, 2025 - November 2, 2025
 */

import com.keyin.Model.Task;
import com.keyin.TaskLinkedList.TaskList;
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

        do {
            System.out.println("\nWelcome to the Task Management System");
            System.out.println("----------------------------------------------");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.println("----------------------------------------------");

            do {
                System.out.print("Please make a selection: ");

                if (scanner.hasNextInt()) {
                    menuOption = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } else {
                    scanner.nextLine();
                    System.out.println("\nInvalid entry. Please enter a number.");
                }
            } while (true);

            switch (menuOption) {
                case LOGIN_OPTION:
                    loginMenu(scanner, userService);

                    if (userService.getLoggedInUser() != null) {
                        taskManagementMenu(scanner, userService);
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

        System.out.print("Username: ");
        username = scanner.nextLine();

        System.out.print("Password: ");
        password = scanner.nextLine();

        userService.login(username, password);

        if (userService.getLoggedInUser() == null) {
            pressEnterToContinue(scanner);
        }
    }

    private static void registerMenu(Scanner scanner, UserService userService) {
        String username;
        String password;
        String firstName;
        String lastName;
        String email;
        System.out.println("\nEnter your details to register.\n");

        System.out.print("Username: ");
        username = scanner.nextLine();

        System.out.print("Password: ");
        password = scanner.nextLine();

        System.out.print("First Name: ");
        firstName = scanner.nextLine();

        System.out.print("Last Name: ");
        lastName = scanner.nextLine();

        System.out.print("Email: ");
        email = scanner.nextLine();

        userService.register(username, password, firstName, lastName, email);
    }

    private static void taskManagementMenu(Scanner scanner, UserService userService) {
        final int VIEW_ALL_TASKS_OPTION = 1;
        final int VIEW_PENDING_TASKS_OPTION = 2;
        final int VIEW_COMPLETED_TASKS_OPTION = 3;
        final int ADD_TASK_OPTION = 4;
        final int MARK_TASK_COMPLETED_OPTION = 5;
        final int MARK_TASK_PENDING_OPTION = 6;
        final int EDIT_TASK_DESCRIPTION_OPTION = 7;
        final int DELETE_TASK_OPTION = 8;
        final int LOGOUT_OPTION = 9;
        int menuOption;

        if (userService.getLoggedInUser() == null) {
            System.out.println("No user is currently logged in.");
            return;
        }

        do {
            TaskList userTasks = userService.getLoggedInUser().getTasks();

            System.out.printf("\nTask Management Main Menu. Welcome %s!\n", userService.getLoggedInUser().getFirstName());
            System.out.println("--------------------------------------------------");
            System.out.println("1. View All Tasks");
            System.out.println("2. View Pending Tasks");
            System.out.println("3. View Completed Tasks");
            System.out.println("4. Add Task");
            System.out.println("5. Mark Task as Completed");
            System.out.println("6. Mark Task as Pending");
            System.out.println("7. Edit Task Description");
            System.out.println("8. Delete a Task");
            System.out.println("9. Logout");
            System.out.println("--------------------------------------------------");

            do {
                System.out.print("\nPlease make a selection: ");

                if (scanner.hasNextInt()) {
                    menuOption = scanner.nextInt();
                    scanner.nextLine();
                    break;
                }

                scanner.nextLine();
                System.out.println("\nInvalid entry. Please enter a number.");
            } while (true);

            switch (menuOption) {
                case VIEW_ALL_TASKS_OPTION:
                    System.out.println();
                    userTasks.viewAllTasks();

                    pressEnterToContinue(scanner);
                    break;
                case VIEW_PENDING_TASKS_OPTION:
                    System.out.println();
                    userTasks.viewTasksByCompletionStatus(false);

                    pressEnterToContinue(scanner);
                    break;
                case VIEW_COMPLETED_TASKS_OPTION:
                    System.out.println();
                    userTasks.viewTasksByCompletionStatus(true);

                    pressEnterToContinue(scanner);
                    break;
                case ADD_TASK_OPTION:
                    String taskDescription;

                    do {
                        System.out.print("\nEnter the task description: ");

                        taskDescription = scanner.nextLine().trim();

                        if (!taskDescription.isEmpty()) {
                            break;
                        }

                        System.out.println("The description can't be blank.");
                    } while (true);

                    Task newTask = new Task(taskDescription);

                    userTasks.addTask(newTask);

                    pressEnterToContinue(scanner);
                    break;
                case MARK_TASK_COMPLETED_OPTION:
                    completeTaskMenu(userTasks, scanner, true);
                    pressEnterToContinue(scanner);
                    break;
                case MARK_TASK_PENDING_OPTION:
                    userTasks.markTaskAsCompleted(0, false);
                    completeTaskMenu(userTasks, scanner, false);
                    pressEnterToContinue(scanner);
                    break;
                case EDIT_TASK_DESCRIPTION_OPTION:
                    editTaskDescriptionMenu(userTasks, scanner);
                    pressEnterToContinue(scanner);
                    break;
                case DELETE_TASK_OPTION:
                    deleteTaskMenu(userTasks, scanner);
                    pressEnterToContinue(scanner);
                    break;
                case LOGOUT_OPTION:
                    userService.logout();
                    pressEnterToContinue(scanner);
                    break;
                default:
                    System.out.println("Invalid selection. Please try again.");
            }

        } while (menuOption != LOGOUT_OPTION);
    }

    private static void completeTaskMenu(TaskList taskList, Scanner scanner, boolean isCompleted) {
        int taskId;

        do {
            System.out.println("\nEnter the task number to update: ");

            if (scanner.hasNextInt()) {
                taskId = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Invalid input. Please try again.\n");
                scanner.nextLine();
            }
        } while (true);

        taskList.markTaskAsCompleted(taskId - 1, isCompleted);
    }

    private static void editTaskDescriptionMenu(TaskList taskList, Scanner scanner) {
        int taskId;
        Task task;
        String description;

        do {
            System.out.println("\nEnter the task number to update: ");

            if (scanner.hasNextInt()) {
                taskId = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Invalid input. Please try again.\n");
                scanner.nextLine();
            }
        } while (true);

        if (taskId > taskList.getCount()) {
            System.out.println("Invalid task number.");
            return;
        }

        task = taskList.getTask(taskId - 1);

        System.out.printf("\nCurrent task description: %s", task.getDescription());
        System.out.print("\nNew task description: ");

        description = scanner.nextLine();

        taskList.updateTaskDescription(taskId - 1, description);
    }

    private static void deleteTaskMenu(TaskList taskList, Scanner scanner) {
        int taskId;

        do {
            System.out.println("\nEnter the task number to delete: ");

            if (scanner.hasNextInt()) {
                taskId = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Invalid input. Please try again.\n");
                scanner.nextLine();
            }
        } while (true);

        if (taskId > taskList.getCount()) {
            System.out.println("Invalid task number.");
            return;
        }

        taskList.removeTask(taskId - 1);
    }

    private static void pressEnterToContinue(Scanner scanner) {
        System.out.println("Press Enter to Continue.");
        scanner.nextLine();
    }
}