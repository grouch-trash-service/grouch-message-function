package message;


import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.internal.testutils.Timer;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import message.error.FailedToStartException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class StreamLambdaHandler implements RequestStreamHandler {
    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
    static {
        try {
            handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(Application.class);
        } catch (ContainerInitializationException e) {
            // if we fail here. We re-throw the exception to force another cold start
            throw new FailedToStartException("Could not initialize Spring Boot application", e);
        }
    }

    public StreamLambdaHandler() {
        // we enable the timer for debugging. This SHOULD NOT be enabled in production.
        Timer.enable();
    }

    @Override
    public void handleRequest(final InputStream inputStream, final OutputStream outputStream, final Context context)
            throws IOException {
        handler.proxyStream(inputStream, outputStream, context);
    }
}
