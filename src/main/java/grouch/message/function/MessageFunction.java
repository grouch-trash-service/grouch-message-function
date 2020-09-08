package grouch.message.function;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import grouch.message.provider.MessageProvider;
import org.openapitools.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Collections;
import java.util.function.Function;


public class MessageFunction implements Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private final MessageProvider messageProvider;

    @Autowired
    public MessageFunction(final MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    @Override
    public APIGatewayProxyResponseEvent apply(final APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent) {
        Message message = messageProvider.getMessage();
        String body = messageProvider.messageAsJson(message);
        return new APIGatewayProxyResponseEvent().withBody(body)
                .withHeaders(Collections.singletonMap("Content-Type", MediaType.APPLICATION_JSON_VALUE));
    }
}
