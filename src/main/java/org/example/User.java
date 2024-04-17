package org.example;

import java.util.Iterator;

// A class representing a user of the chat application.
// It should have methods to send messages to other users, receive messages from other users, and undo the last message sent.
public class User implements IterableByUser {
    private String name;
    private ChatHistory chatHistory;
    private ChatServer chatServer;
    private MessageMemento memento;

    public User(String name) {
        this.name = name;
        this.chatHistory = new ChatHistory();
        this.chatServer = new ChatServer();
        this.memento = new MessageMemento();
    }

    public void sendMessage(Message message) {
        chatServer.sendMessage(message);
        chatHistory.addMessage(message);
        memento.setState(message);
    }

    public void receiveMessage(Message message) {
        chatHistory.addMessage(message);
    }

    public void undoMessage() {
        Message oldMsg = memento.getState();
        oldMsg.getSender().chatHistory.getMessageHistory().remove(oldMsg);
        for(User x : oldMsg.getRecipient()){
            x.chatHistory.getMessageHistory().remove(oldMsg);
        }
    }

    public String getName() {
        return name;
    }

    public ChatHistory getChatHistory() {
        return chatHistory;
    }

    public ChatServer getChatServer() {
        return chatServer;
    }

    public MessageMemento getMemento() {
        return memento;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Iterator iterator(User userToSearchWith) {
        return new searchMessagesByUser(this.chatHistory, userToSearchWith);
    }
}
