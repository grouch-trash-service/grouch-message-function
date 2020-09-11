package grouch.message.provider;

import grouch.message.model.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageService implements MessageProvider {

    public Message getMessage() {
        return new Message("Trash Pickup is on Tuesday...Now Scram!");
    }
}
