package grouch.message.provider;

import grouch.message.model.Message;
import grouch.message.model.TrashSchedule;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultMessageProviderTest {

    @Test
    @DisplayName("should return a Message")
    void testGetMessage() {
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