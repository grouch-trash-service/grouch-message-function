package grouch.message.provider;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openapitools.model.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageServiceTest {

    private MessageService messageService;
    private Message message;

    @Before
    public void setUp() {
        messageService = new MessageService();
        message = new Message().text("Trash Pickup is on Tuesday...Now Scram!");
    }

    @DisplayName("should get messages")
    @Test
    public void testGetMessage() {
        Message actualMessage = messageService.getMessage();
        assertEquals(message, actualMessage);
    }
}