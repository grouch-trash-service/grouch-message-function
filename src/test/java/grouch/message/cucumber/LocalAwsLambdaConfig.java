package grouch.message.cucumber;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!prod")
public class LocalAwsLambdaConfig {

    @Value("${lambda.local.endpoint}")
    private String endpoint;

    @Value("${lambda.local.region}")
    private String region;

    @Bean
    public AWSLambda awsLambda() {
        return AWSLambdaClientBuilder.standard()
                .withEndpointConfiguration(endpointConfiguration()).build();
    }

    private AwsClientBuilder.EndpointConfiguration endpointConfiguration() {
        return new AwsClientBuilder.EndpointConfiguration(endpoint, region);
    }
}
