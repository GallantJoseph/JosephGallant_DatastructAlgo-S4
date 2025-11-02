package com.keyin.Model;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.keyin.TaskLinkedList.TaskList;

public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private final TaskList tasks = new TaskList();

    public User(String username, String password, String firstName, String lastName, String email) {
        this.username = username;
        setPassword(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean verifyPassword(String password) {
        return BCrypt.verifyer().verify(password.toCharArray(), this.password).verified;
    }

    public void setPassword(String password) {
        this.password = BCrypt.withDefaults().hashToString(10, password.toCharArray());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TaskList getTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        return this.tasks.getTask(index);
    }

    public void addTask(Task task) {
        this.tasks.addTask(task);
    }

    public void removeTask(int index) {
        this.tasks.removeTask(index);
    }
}
