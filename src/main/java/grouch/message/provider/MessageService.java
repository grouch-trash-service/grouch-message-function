package grouch.message.provider;

import org.openapitools.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MessageService implements MessageProvider {
    private final Logger log = LoggerFactory.getLogger(MessageService.class);

    public Message getMessage() {
        return new Message().text("Trash Pickup is on Tuesday...Now Scram!");
    }
}
