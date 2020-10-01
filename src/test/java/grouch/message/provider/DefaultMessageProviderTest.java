package grouch.message.provider;

import grouch.message.model.Message;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultMessageProviderTest {

    @Test
    @DisplayName("should return a Message")
    public void testGetMessage() {
        TrashSchedule trashSchedule = new TrashSchedule();
        trashSchedule.setSchedule("Tuesday");
        trashSchedule.setType("default");

        DefaultMessageProvider defaultMessageProvider = new DefaultMessageProvider(trashSchedule);
        Message message = defaultMessageProvider.getMessage();

        Message expectedMessage = new Message();
        expectedMessage.setText(String.format("Trash Pickup is on %s...Now Scram!", trashSchedule.getSchedule()));
        assertEquals(expectedMessage, message);
    }
}