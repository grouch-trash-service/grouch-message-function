package grouch.message.provider;

import org.codehaus.jackson.map.ObjectMapper;
import org.openapitools.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MessageService implements MessageProvider {
    private final ObjectMapper objectMapper;
    private final Logger log = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    public MessageService(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Message getMessage() {
        return new Message().text("Trash Pickup is on Tuesday...Now Scram!");
    }

    public String messageAsJson(final Message message) {
        try {
            return objectMapper.writeValueAsString(message);
        } catch (IOException e) {
            log.error("Error converting to json", e);
            return e.getMessage();
        }
    }
}
