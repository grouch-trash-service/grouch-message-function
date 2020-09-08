package grouch.message.provider;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openapitools.model.Message;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

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