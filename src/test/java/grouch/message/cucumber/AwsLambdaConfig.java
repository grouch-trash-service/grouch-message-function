package grouch.message.cucumber;

import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class AwsLambdaConfig {
    @Bean
    public AWSLambda awsLambda() {
        return AWSLambdaClientBuilder.defaultClient();
    }
}

