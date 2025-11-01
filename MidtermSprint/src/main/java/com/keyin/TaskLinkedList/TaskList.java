package com.keyin.TaskLinkedList;

import com.keyin.Model.Task;

public class TaskList {
    Node head;
    Node tail;
    int count;

    public TaskList() {
        this.head = null;
        this.tail = null;
        this.count = 0;
    }

    public TaskList(Task task) {
        Node newTaskList = new Node(task);

        this.head = newTaskList;
        this.tail = newTaskList;
        this.count = 1;
    }

    public int getCount() {
        return count;
    }

    public void addTask(Task task) {
        Node newTaskList = new Node(task);

        if (this.head == null) {
            this.head = newTaskList;
            this.tail = newTaskList;
        } else {
            this.tail.setNext(newTaskList);
            this.tail = newTaskList;
        }

        this.count++;

        System.out.println("\nTask added successfully.\n");
    }

    public Task getTask(int index) {
        if (index < 0 || index >= this.count) {
            System.out.println("Index out of bounds");
            return null;
        }

        Node current = this.head;

        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        return current.getTask();
    }

    public void removeTask(int index) {
        if (index < 0 || index >= this.count) {
            System.out.println("Index out of bounds");
        }

        if (index == 0) {
            this.head = this.head.getNext();

            if (this.head == null) {
                this.tail = null;
            }

        } else {
            Node current = this.head;

            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }

            current.setNext(current.getNext().getNext());

            if (current.getNext() == null) {
                this.tail = current;
            }
        }

        this.count--;

        System.out.println("\nTask removed successfully.\n");
    }

    public void viewAllTasks() {
        if (this.count == 0) {
            System.out.println("\nNo tasks available.\n");
            return;
        }

        int currentIndex = 0;
        Node current = this.head;

        do {
            Task currentTask = current.getTask();
            String status = currentTask.isCompleted() ? "Completed" : "Pending";
            System.out.println(currentIndex + 1 + ". " + currentTask.getDescription() + " [" + status + "]");

            current = current.getNext();
            currentIndex++;
        } while (current != null);
    }

    public void viewTasksByCompletionStatus(boolean isCompleted) {
        if (this.count == 0) {
            System.out.println("\nNo tasks available.\n");
            return;
        }

        int currentIndex = 0;
        Node current = this.head;
        boolean found = false;

        do {
            Task currentTask = current.getTask();

            if (currentTask.isCompleted() == isCompleted) {
                String status = currentTask.isCompleted() ? "Completed" : "Pending";
                System.out.println(currentIndex + 1 + ". " + currentTask.getDescription() + " [" + status + "]");
                found = true;
            }

            current = current.getNext();
            currentIndex++;
        } while (current != null);

        if (!found) {
            System.out.println("\nNo tasks found with the specified completion status.\n");
        }
    }

    public void updateTaskDescription(int taskIndex, String newDescription) {
        if (taskIndex < 0 || taskIndex >= this.count) {
            System.out.println("Index out of bounds");
            return;
        }

        Task task = getTask(taskIndex);

        System.out.printf("Old Description: %s\n", task.getDescription());

        task.setDescription(newDescription);
        System.out.printf("New Description: %s\n", task.getDescription());

        System.out.println("\nTask description updated successfully.\n");
    }

    public void markTaskAsCompleted(int taskIndex, boolean isCompleted) {
        if (taskIndex < 0 || taskIndex >= this.count) {
            System.out.println("Index out of bounds");
            return;
        }

        Task task = this.getTask(taskIndex);
        task.setCompleted(isCompleted);
    }
}
