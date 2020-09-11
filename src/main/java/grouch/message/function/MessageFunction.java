package grouch.message.function;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import grouch.message.model.Message;
import grouch.message.provider.MessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;
import java.util.function.Function;

@Slf4j
public class MessageFunction implements Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final MessageProvider messageProvider;
    private final ObjectMapper objectMapper;

    @Autowired
    public MessageFunction(final MessageProvider messageProvider, final ObjectMapper objectMapper) {
        this.messageProvider = messageProvider;
        this.objectMapper = objectMapper;
    }

    @Override
    public APIGatewayProxyResponseEvent apply(final APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent) {
        Message message = messageProvider.getMessage();
        String body = bodyAsString(message);
        return new APIGatewayProxyResponseEvent().withBody(body)
                .withHeaders(Collections.singletonMap(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));
    }

    protected String bodyAsString(final Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Error converting body to json", e);
            return e.getMessage();
        }
    }
}
