package grouch.message.provider;

import org.openapitools.model.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageService implements MessageProvider {

    public Message getMessage() {
        return new Message().text("Trash Pickup is on Tuesday...Now Scram!");
    }
}
