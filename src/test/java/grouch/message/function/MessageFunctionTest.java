package grouch.message.function;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import grouch.message.model.Message;
import grouch.message.provider.MessageProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;
import grouch.message.model.Error;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MessageFunctionTest {

    @Mock
    private MessageProvider messageProvider;

    @Mock
    private APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent;

    private ObjectMapper objectMapper;
    private MessageFunction messageFunction;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
        messageFunction = new MessageFunction(messageProvider,objectMapper);
    }

    @DisplayName("Should return a Response Event with a message")
    @Test
    public void testReturnResponseEventWithMessage() throws IOException {
        Message expectedMessage = expectedMessage();
        doReturn(expectedMessage).when(messageProvider).getMessage();

        APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent = messageFunction.apply(apiGatewayProxyRequestEvent);

        assertEquals(MediaType.APPLICATION_JSON_VALUE,
                apiGatewayProxyResponseEvent.getHeaders().get(HttpHeaders.CONTENT_TYPE));

        assertEquals("*", apiGatewayProxyResponseEvent.getHeaders().get(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN));

        Message message = actualMessage(apiGatewayProxyResponseEvent);
        assertEquals(expectedMessage, message);
    }

    @DisplayName("Should return Error message when an error occurs")
    @Test
    public void testError() throws IOException {
        Error expectedError = new Error("An Error occurred");
        doThrow(new RuntimeException(expectedError.getMessage())).when(messageProvider).getMessage();

        APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent = messageFunction.apply(apiGatewayProxyRequestEvent);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), apiGatewayProxyResponseEvent.getStatusCode());
        Error error = actualError(apiGatewayProxyResponseEvent);

        assertEquals(expectedError, error);
    }

    @DisplayName("Should write body as json string")
    @Test
    public void testWriteBodyAsString() throws IOException {
        Message message = expectedMessage();
        String expectedString = objectMapper.writeValueAsString(message);

        String messageAsString = messageFunction.bodyAsString(message);

        assertEquals(expectedString, messageAsString);
    }

    @DisplayName("Should write error when converting to json")
    @Test
    public void testWriteErrorMessageWhenGettingBodyAsString() throws JsonProcessingException {
        Message message = expectedMessage();
        objectMapper = mock(ObjectMapper.class);

        doThrow(JsonProcessingException.class).when(objectMapper).writeValueAsString(any());
        messageFunction = new MessageFunction(messageProvider, objectMapper);


        String body = messageFunction.bodyAsString(message);

        assertEquals("N/A", body);
    }

    private String messageAsJson(Message message) throws IOException {
        return new ObjectMapper().writeValueAsString(message);
    }

    private Message expectedMessage() {
        String text = "Trash Pickup is on Tuesday...Now Scram!";
        return new Message(text);
    }

    private Message actualMessage(APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent) throws IOException {
        String body = apiGatewayProxyResponseEvent.getBody();
        return new ObjectMapper().readValue(body, Message.class);
    }

    private Error actualError(APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent) throws IOException {
        String body = apiGatewayProxyResponseEvent.getBody();
        return new ObjectMapper().readValue(body, Error.class);
    }
}