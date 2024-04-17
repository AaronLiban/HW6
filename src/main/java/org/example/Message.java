package org.example;// A class representing a message sent by a user.
// It should have properties for the sender, recipient(s), timestamp, and message content

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Message {
    private User sender;
    private List<User> recipient = new ArrayList<User>();
    private String timestamp;
    private String msgContent;

    public Message(User sender, List<User> recipient, String msgContent) {
        this.sender = sender;
        this.recipient = recipient;
        this.timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        this.msgContent = msgContent;
    }

    public User getSender() {
        return sender;
    }

    public List<User> getRecipient() {
        return recipient;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getMsgContent() {
        return msgContent;
    }
}
