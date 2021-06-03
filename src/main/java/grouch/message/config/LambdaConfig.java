package grouch.message.config;

import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.invoke.LambdaInvokerFactory;
import grouch.message.function.TrashFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class LambdaConfig {
    @Bean
    public AWSLambda awsLambda() {
        return AWSLambdaClientBuilder.defaultClient();
    }

    @Bean
    TrashFunction messageLambdaFunction(final AWSLambda awsLambda) {
        return LambdaInvokerFactory
                .builder()
                .lambdaClient(awsLambda)
                .build(TrashFunction.class);
    }
}
