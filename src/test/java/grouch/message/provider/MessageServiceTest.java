package grouch.message.provider;

import grouch.message.model.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageServiceTest {

    private MessageService messageService;
    private Message message;

    @Before
    public void setUp() {
        messageService = new MessageService();
        message = new Message("Trash Pickup is on Tuesday...Now Scram!");
    }

    @DisplayName("should get messages")
    @Test
    public void testGetMessage() {
        Message actualMessage = messageService.getMessage();
        assertEquals(message, actualMessage);
    }
}