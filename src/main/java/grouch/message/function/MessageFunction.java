package grouch.message.function;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import grouch.message.provider.MessageProvider;
import org.openapitools.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Collections;
import java.util.function.Function;


public class MessageFunction implements Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final MessageProvider messageProvider;
    private final ObjectMapper objectMapper;
    private final Logger log = LoggerFactory.getLogger(MessageFunction.class);

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
                .withHeaders(Collections.singletonMap("Content-Type", MediaType.APPLICATION_JSON_VALUE));
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
