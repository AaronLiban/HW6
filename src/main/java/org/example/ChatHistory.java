package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// A class that stores the chat history for a user.
// It should have methods to add a new message to the history and get the last message sent.

public class ChatHistory implements IterableByUser {
    private List<Message> messageHistory;


    public ChatHistory() {
        this.messageHistory = new ArrayList<Message>();
    }


    public void addMessage(Message message) {
        messageHistory.add(message);
    }

    public Message getLastMessage() {
        return messageHistory.get(messageHistory.size()-1);
    }

    public void setMessageHistory(List<Message> messageHistory) {
        this.messageHistory = messageHistory;
    }

    public List<Message> getMessageHistory() {
        return messageHistory;
    }

    @Override
    public Iterator iterator(User userToSearchWith) {
        return new searchMessagesByUser(this, userToSearchWith);
    }
}
