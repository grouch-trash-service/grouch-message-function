package message.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@RestController
@EnableWebMvc
@RequestMapping(path = "/v1")
public class MessageController {

    @RequestMapping(path = "/message", method = RequestMethod.GET)
    public Message getMessage() {
        return new Message();
    }

    private class Message {
        public String text = "Hello. Trash pickup will be on Tuesday... Now SCRAM!";
    }

}
