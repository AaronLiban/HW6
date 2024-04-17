package org.example;

// A class that represents a snapshot of a message sent by a user.
// It should have properties for the message content and timestamp.
public class MessageMemento {

    private  Message state;
    public MessageMemento(){

    }
    public Message getState() {
        return state;
    }
    public void setState(Message message) {
        this.state = message;
    }
}
