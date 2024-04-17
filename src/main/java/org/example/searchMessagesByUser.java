package org.example;

import java.util.Iterator;
import java.util.List;

public class searchMessagesByUser implements Iterator{

    private int indexInMessageHistory;
    private int messageHistorySize;
    private User userToSearchWith;
    private List<Message> messageHistory;

    public searchMessagesByUser(ChatHistory chatHistory, User userToSearchWith) {
        this.userToSearchWith = userToSearchWith;
        this.messageHistory = chatHistory.getMessageHistory();
        this.indexInMessageHistory = 0;
        this.messageHistorySize = messageHistory.size();
    }


    @Override
    public boolean hasNext() {
        User user = null;
        while(indexInMessageHistory < messageHistorySize) {
            user = messageHistory.get(indexInMessageHistory).getSender();
            if(userToSearchWith.equals(user)) {
                return true;
            }
            else {
                indexInMessageHistory++;
            }
        }
        return false;
    }

    @Override
    public Message next() {
        if (hasNext()) {
            return messageHistory.get(indexInMessageHistory++);
        }
        return null;
    }

    @Override
    public void remove() {
        Iterator.super.remove();
    }
}
