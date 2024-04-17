import org.example.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChatTest {
    User u1 = new User("User1");
    User u2 = new User("User2");
    User u3 = new User("User3");

    ChatServer cs = new ChatServer();
    ChatHistory ch = new ChatHistory();


    List<User> recipients = new ArrayList<User>();

    @Test
    public void testSendMessage() {
        cs.registerUser(u1);
        cs.registerUser(u2);

        recipients.add(u2);

        Message expected = new Message(u1, recipients, "Hello");
        u1.sendMessage(expected);
        Message actual = u2.getChatHistory().getLastMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void testReceiveMessage() {
        cs.registerUser(u1);
        cs.registerUser(u2);

        recipients.add(u2);

        Message expected = new Message(u1, recipients, "Hello");
        Message m = expected;
        u1.receiveMessage(m);
        Message actual = u1.getChatHistory().getLastMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void testUndoMessage() {
        cs.registerUser(u1);
        cs.registerUser(u2);

        recipients.add(u2);

        Message expected = new Message(u1, recipients, "Hello");
        Message m1 = expected;
        Message m2 = new Message(u1, recipients, "Bye");
        u1.sendMessage(m1);
        u1.sendMessage(m2);
        u1.undoMessage();
        Message actual = u2.getChatHistory().getLastMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void testRegister() {
        List<User> expected = new ArrayList<User>();
        expected.add(u1);
        cs.registerUser(u1);
        List<User> actual = cs.getUsers();
        assertEquals(expected, actual);
    }

    @Test
    public void testUnregister() {
        List<User> expected = new ArrayList<User>();
        cs.registerUser(u1);
        cs.unregisterUser(u1);
        List<User> actual = cs.getUsers();
        assertEquals(expected, actual);
    }

    @Test
    public void testBlockUser() {
        List<User> expected = new ArrayList<User>();
        expected.add(u1);
        cs.registerUser(u1);
        cs.blockUser(u1);
        List<User> actual = cs.getBlockList();
        assertEquals(expected, actual);
    }

    @Test
    public void testAddMessage() {
        cs.registerUser(u1);
        cs.registerUser(u2);

        recipients.add(u2);

        Message m = new Message(u1, recipients, "Hello");
        List<Message> expected = new ArrayList<>();
        expected.add(m);
        ch.addMessage(m);
        List<Message> actual = ch.getMessageHistory();
        assertEquals(expected, actual);
    }
    @Test
    public void testLastMessageSent() {
        cs.registerUser(u1);
        cs.registerUser(u2);

        recipients.add(u2);

        Message expected = new Message(u1, recipients, "Hello");
        u1.sendMessage(expected);
        Message actual = u2.getChatHistory().getLastMessage();
        assertEquals(expected, actual);
    }
    @Test
    public void testSearchMessagesByUser() {
        cs.registerUser(u1);
        cs.registerUser(u2);
        cs.registerUser(u3);

        recipients.add(u2);

        Message m1 = new Message(u1, recipients, "Hello");
        Message m2 = new Message(u3, recipients, "Hi");
        Message m3 = new Message(u1, recipients, "Bye");
        List<Message> expected = new ArrayList<>();
        expected.add(m1);
        expected.add(m3);
        cs.sendMessage(m1);
        cs.sendMessage(m2);
        cs.sendMessage(m3);
        searchMessagesByUser smbu = (searchMessagesByUser) u2.iterator(u1);
        List<Message> actual = new ArrayList<Message>();
        while(smbu.hasNext()) {
            actual.add(smbu.next());
        }
        assertEquals(expected,actual);
    }
}
