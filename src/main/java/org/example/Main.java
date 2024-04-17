package org.example;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Registering Anne, Bob, and Carl");
        User anne = new User("Anne");
        User bob = new User("Bob");
        User carl = new User("Carl");

        ChatServer cs = new ChatServer();
        cs.registerUser(anne);
        cs.registerUser(bob);
        cs.registerUser(carl);

        System.out.println("Anne is sending a message to Bob and Carl:");
        List<User> recipients1 = new ArrayList<User>();
        recipients1.add(bob);
        recipients1.add(carl);
        Message m1 = new Message(anne, recipients1, "Hello");

        anne.sendMessage(m1);

        Message x = anne.getChatHistory().getLastMessage();
        System.out.println(x.getMsgContent());

        System.out.println("Bob sends messages:");

        List<User> recipients2 = new ArrayList<User>();
        recipients2.add(anne);
        recipients2.add(carl);
        Message m2 = new Message(bob, recipients2, "Bye");
        bob.sendMessage(m2);
        Message m3 = new Message(bob, recipients2, "Lol");
        bob.sendMessage(m3);
        List<Message> y = bob.getChatHistory().getMessageHistory();
        for(Message msg : y){
            System.out.println(msg.getMsgContent());
        }

        System.out.println("Search Anne's messages");
        searchMessagesByUser smbu = (searchMessagesByUser) carl.iterator(anne);
        while(smbu.hasNext()) {
            System.out.println(smbu.next().getMsgContent());
        }

        System.out.println("Undoing Bob's message");
        bob.undoMessage();
        List<Message> z = bob.getChatHistory().getMessageHistory();
        for(Message msg : z){
            System.out.println(msg.getMsgContent());
        }

        System.out.println("Blocking Carl");
        cs.blockUser(carl);
        List<User> recipients3 = new ArrayList<User>();
        recipients3.add(bob);
        recipients3.add(anne);
        Message m4 = new Message(carl, recipients3, "Hi");
        cs.sendMessage(m4);


    }
}
