package grouch.message.function;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import grouch.message.model.Error;
import grouch.message.model.Message;
import grouch.message.provider.MessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MessageFunction implements MessageLambdaFunction {

    private final MessageProvider messageProvider;
    private final ObjectMapper objectMapper;

    @Autowired
    public MessageFunction(final MessageProvider messageProvider, final ObjectMapper objectMapper) {
        this.messageProvider = messageProvider;
        this.objectMapper = objectMapper;
    }

    @Override
    public APIGatewayProxyResponseEvent apply(final APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent) {
        log.info("Starting get message");
        APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent;
        try {
            apiGatewayProxyResponseEvent = getMessage();
        } catch (Exception e) {
            apiGatewayProxyResponseEvent = getError(e);
        }
        log.info("Completed get message. body={}, status={}", apiGatewayProxyResponseEvent.getBody(),
                apiGatewayProxyResponseEvent.getStatusCode());

        return apiGatewayProxyResponseEvent;
    }

    private APIGatewayProxyResponseEvent getError(final Throwable exception) {
        log.error("Error occurred.", exception);
        Error error = new Error(exception.getMessage());
        String body = bodyAsString(error);
        return new APIGatewayProxyResponseEvent().withBody(body)
                .withHeaders(getHeaders()).withStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    private APIGatewayProxyResponseEvent getMessage() {
        Message message = messageProvider.getMessage();
        String body = bodyAsString(message);
        log.info("Got message. message={}", body);
        return new APIGatewayProxyResponseEvent().withBody(body)
                .withHeaders(getHeaders()).withStatusCode(HttpStatus.OK.value());
    }

    protected String bodyAsString(final Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Error converting body to json", e);
            return e.getMessage();
        }
    }

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        headers.put(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        return headers;
    }
}
