package grouch.message.provider;

import grouch.message.model.HolidayTrashSchedule;
import grouch.message.model.Message;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HolidayMessageProviderTest {

    @Test
    @DisplayName("should return a Message")
    void testGetMessage() {
        HolidayTrashSchedule trashSchedule = new HolidayTrashSchedule();
        trashSchedule.setSchedule("Routes are delayed by one day.");
        trashSchedule.setType("holiday");
        trashSchedule.setHoliday("Christmas");

        HolidayMessageProvider defaultMessageProvider = new HolidayMessageProvider(trashSchedule);
        Message message = defaultMessageProvider.getMessage();

        Message expectedMessage = new Message();
        expectedMessage.setText(String.format("Because of lousy %s, %s", trashSchedule.getHoliday(), trashSchedule.getSchedule()));
        assertEquals(expectedMessage, message);
    }
}