package grouch.message.config;

import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.invoke.LambdaInvokerFactory;
import grouch.message.function.TrashFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LambdaConfig {
    @Bean
    public AWSLambda awsLambda() {
        return AWSLambdaClientBuilder.defaultClient();
    }

    @Bean
    TrashFunction messageLambdaFunction() {
        return LambdaInvokerFactory
                .builder()
                .lambdaClient(awsLambda())
                .build(TrashFunction.class);
    }
}
