package grouch.message.provider;

import org.openapitools.model.Message;

public interface MessageProvider {
    Message getMessage();
    String messageAsJson(Message message);
}
