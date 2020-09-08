package grouch.message;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.springframework.http.MediaType;

import java.util.Collections;
import java.util.function.Function;

public class MessageFunction implements Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    @Override
    public APIGatewayProxyResponseEvent apply(final APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent) {
        return new APIGatewayProxyResponseEvent().withBody("test")
                .withHeaders(Collections.singletonMap("Content-Type", MediaType.TEXT_PLAIN_VALUE));
    }
}
