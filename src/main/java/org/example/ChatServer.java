package org.example;

import java.util.ArrayList;
import java.util.List;

// The mediator class that manages communication between users.
// It should have methods to register and unregister users, send messages from one user to one or more other users, and block messages from specific users.
public class ChatServer {
    private List<User> users;
    private List<User> blockList;

    public ChatServer() {
        this.users = new ArrayList<User>();
        this.blockList = new ArrayList<User>();
    }

    public void registerUser(User user) {
        users.add(user);

    }

    public void unregisterUser(User user) {
        users.remove(user);
    }

    public void sendMessage(Message message) {
        try {
            for (User user : blockList) {
                if (user.equals(message.getSender())) {
                    throw new RuntimeException();
                }
            }
            for (User x : message.getRecipient()) {
                x.receiveMessage(message);
            }
        } catch (Exception e) {
            System.out.println("You are blocked");
        }
    }

    public void blockUser(User user) {
        blockList.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public List<User> getBlockList() {
        return blockList;
    }
}
