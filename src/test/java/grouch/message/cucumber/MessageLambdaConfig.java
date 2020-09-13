package grouch.message.cucumber;

import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.invoke.LambdaInvokerFactory;
import grouch.message.function.MessageLambdaFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MessageLambdaConfig {

    @Autowired
    private AWSLambda awsLambda;

    @Bean
    MessageLambdaFunction messageLambdaFunction() {
        return LambdaInvokerFactory
                .builder()
                .lambdaClient(awsLambda)
                .build(MessageLambdaFunction.class);
    }
}
