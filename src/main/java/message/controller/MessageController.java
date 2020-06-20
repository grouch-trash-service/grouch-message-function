package message.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@RestController
@EnableWebMvc
@RequestMapping(path = "/v1")
public class MessageController {

    @GetMapping(path = "/message")
    public Message getMessage() {
        return new Message("Hello. Trash pickup will be on Tuesday... Now SCRAM!");
    }

    private class Message {
        public final String text;

        public Message(String text) {
            this.text = text;
        }
    }

}