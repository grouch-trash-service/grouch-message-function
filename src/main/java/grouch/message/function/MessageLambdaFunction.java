package grouch.message.function;

import com.amazonaws.services.lambda.invoke.LambdaFunction;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.util.function.Function;

public interface MessageLambdaFunction extends Function<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Override
    @LambdaFunction(functionName = "GrouchMessageFunction")
    APIGatewayProxyResponseEvent apply(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent);
}
