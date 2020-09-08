package grouch.message.function;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

public class FunctionHandler extends SpringBootRequestHandler<APIGatewayProxyRequestEvent,
        APIGatewayProxyResponseEvent> {
}
