package grouch.message.cucumber;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import grouch.message.function.MessageLambdaFunction;
import grouch.message.model.Message;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

public class StepDefinitions extends SpringCucumberContext {

    private APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent;
    private APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent;

    @Autowired
    MessageLambdaFunction messageLambdaFunction;

    @Given("^a valid api gateway request$")
    public void validRequest() {
         apiGatewayProxyRequestEvent = new APIGatewayProxyRequestEvent();
    }

    @When("^a user requests a message$")
    public void requestMessage() {
        apiGatewayProxyResponseEvent =
                messageLambdaFunction.apply(apiGatewayProxyRequestEvent);
    }

    @Then("a grouchy message about when trash pickup is returned")
    public void validateMessage() throws JsonProcessingException {
        Message message = new ObjectMapper().readValue(apiGatewayProxyResponseEvent.getBody(), Message.class);
        assertTrue(message.getText().contains("Trash"));
    }
}
