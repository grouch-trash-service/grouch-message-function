package grouch.message;

import org.openapitools.model.Message;

public interface MessageProvider {
    Message getMessage();
    String messageAsJson(Message message);
}
