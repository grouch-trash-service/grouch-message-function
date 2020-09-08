package grouch.message.function;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import grouch.message.provider.MessageProvider;
import grouch.message.provider.MessageService;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.openapitools.model.Message;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MessageFunctionTest {

    @Mock
    private MessageProvider messageProvider;

    @Mock
    private APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent;

    @DisplayName("Should return a Response Event with a message")
    @Test
    public void testReturnResponseEventWithMessage() throws IOException {
        Message expectedMessage = expectedMessage();
        mockMessageProvider(expectedMessage);
        MessageFunction messageFunction = new MessageFunction(messageProvider);

        APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent = messageFunction.apply(apiGatewayProxyRequestEvent);

        Message message = actualMessage(apiGatewayProxyResponseEvent);
        assertEquals(expectedMessage, message);
    }

    private String messageAsJson(Message message) throws IOException {
        return new ObjectMapper().writeValueAsString(message);
    }

    private Message expectedMessage() {
        String text = "Trash Pickup is on Tuesday...Now Scram!";
        return new Message().text(text);
    }

    private void mockMessageProvider(Message expectedMessage) throws IOException {
        doReturn(expectedMessage).when(messageProvider).getMessage();
        doReturn(messageAsJson(expectedMessage)).when(messageProvider).messageAsJson(any(Message.class));
    }

    private Message actualMessage(APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent) throws IOException {
        String body = apiGatewayProxyResponseEvent.getBody();
        return new ObjectMapper().readValue(body, Message.class);
    }
}