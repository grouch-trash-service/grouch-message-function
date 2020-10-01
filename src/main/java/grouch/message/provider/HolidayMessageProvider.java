package grouch.message.provider;

import grouch.message.model.Message;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HolidayMessageProvider implements MessageProvider {

    private final HolidayTrashSchedule holidayTrashSchedule;

    @Override
    public Message getMessage() {
        return new Message(String.format("Because of lousy %s, %s", holidayTrashSchedule.getHoliday(),
                holidayTrashSchedule.getSchedule()));
    }
}
