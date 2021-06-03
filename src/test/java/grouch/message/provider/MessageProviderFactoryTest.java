package grouch.message.provider;

import grouch.message.model.HolidayTrashSchedule;
import grouch.message.model.TrashSchedule;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageProviderFactoryTest {

    @Test
    @DisplayName("should return a default message provider when given a default trash schedule")
    void testDefaultMessageProvider() {
        TrashSchedule trashSchedule = new TrashSchedule();
        MessageProvider messageProvider = MessageProviderFactory.build(trashSchedule);
        assertTrue(messageProvider instanceof DefaultMessageProvider);
    }

    @Test
    @DisplayName("should return a holiday message provider when given a holiday trash schedule")
    void testHolidayMessageProvider() {
        HolidayTrashSchedule trashSchedule = new HolidayTrashSchedule();
        MessageProvider messageProvider = MessageProviderFactory.build(trashSchedule);
        assertTrue(messageProvider instanceof HolidayMessageProvider);
    }
}