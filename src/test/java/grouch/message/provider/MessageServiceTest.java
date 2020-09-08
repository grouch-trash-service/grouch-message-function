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

    private ObjectMapper objectMapper = new ObjectMapper();
    private MessageService messageService;
    private Message message;

    @Before
    public void setUp() {
        messageService = new MessageService(objectMapper);
        message = new Message().text("Trash Pickup is on Tuesday...Now Scram!");
    }

    @DisplayName("should get messages")
    @Test
    public void testGetMessage() {
        Message actualMessage = messageService.getMessage();
        assertEquals(message, actualMessage);
    }

    @DisplayName("should convert message to json")
    @Test
    public void testGetAsJson() throws IOException {
        String expected = objectMapper.writeValueAsString(message);

        String json = messageService.messageAsJson(message);

        assertEquals(expected, json);
    }

    @DisplayName("should return error as String when converting to json")
    @Test
    public void testGetAsJsonError() throws IOException {
        objectMapper = mock(ObjectMapper.class);
        String errorMessage = "an IOException happened";
        doThrow(new IOException(errorMessage)).when(objectMapper).writeValueAsString(any());

        messageService = new MessageService(objectMapper);
        String error = messageService.messageAsJson(message);

        assertEquals(errorMessage, error);
    }
}