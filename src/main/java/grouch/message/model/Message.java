package grouch.message.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Message {

    @JsonProperty("text")
    private String text;

    public Message(final String text) {
        this.text = text;
    }

    public Message() {
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Message message = (Message) o;
        return Objects.equals(text, message.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "Message{"
                + "text='" + text + '\''
                + '}';
    }
}
