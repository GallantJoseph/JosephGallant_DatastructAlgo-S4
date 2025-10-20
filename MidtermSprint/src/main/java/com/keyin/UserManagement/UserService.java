package com.keyin.UserManagement;

import com.keyin.Model.User;

public class UserService {
    private User[] users;
    private int userCount = 0;

    private User loggedInUser = null;

    public UserService() {
        users = new User[0];
    }

    public int getUserCount() {
        return userCount;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void login(String username, String password) {
        // TODO Password hashing for security
        for (int i = 0; i < users.length; i++) {
            if (users[i].getUsername().equals(username) && users[i].getPassword().equals(password)) {
                loggedInUser = users[i];
                System.out.println("Logged in successfully.");
                return;
            }
        }

        System.out.println("Invalid username or password.");
    }

    public void logout() {
        loggedInUser = null;
        System.out.println("Logged out successfully.");
    }

    public void register(String username, String password, String firstName, String lastName, String email) {
        if (usernameExists(username)) {
            System.out.println("Username already exists. Please choose a different username.");
            return;
        }

        User newUser = new User(username, password, firstName, lastName, email);

        try {
            addUser(newUser);
            System.out.println("User registered successfully: " + username);
        } catch (Exception e) {
            System.out.println("Error registering user: " + e.getMessage());
        }
    }

    public boolean usernameExists(String username) {
        for (int i = 0; i < users.length; i++) {
            if (users[i].getUsername().equals(username)) {
                return true;
            }
        }

        return false;
    }

    private void addUser(User user) {
        User[] newUsers = new User[users.length + 1];

        System.arraycopy(users, 0, newUsers, 0, users.length);
        newUsers[users.length] = user;
        users = newUsers;
        userCount++;
    }

    private void removeUser(String userName) {
        int foundIndex = -1;

        for (int i = 0; i < users.length; i++) {
            if (users[i].getUsername().equals(userName)) {
                foundIndex = i;
                break;
            }
        }

        if (foundIndex == -1) {
            System.out.println("User not found: " + userName);
            return;
        }

        // Found at position 0 with only one user
        if (userCount == 1) {
            users = new User[0];
            userCount--;
            return;
        }

        User[] newUsers = new User[users.length - 1];

        System.arraycopy(users, 0, newUsers, 0, foundIndex);

        // If not removing the last user, copy the remaining users
        if (foundIndex != users.length - 1) {
            System.arraycopy(users, foundIndex + 1, newUsers, foundIndex, users.length - foundIndex - 1);
        }

        users = newUsers;
        userCount--;
    }
}
