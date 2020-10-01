package grouch.message.provider;

import grouch.message.model.HolidayTrashSchedule;
import grouch.message.model.TrashSchedule;

public final class MessageProviderFactory {
    public static MessageProvider build(final TrashSchedule trashSchedule) {
        if (trashSchedule instanceof HolidayTrashSchedule) {
            return new HolidayMessageProvider((HolidayTrashSchedule) trashSchedule);
        } else {
            return new DefaultMessageProvider(trashSchedule);
        }
    }

    private MessageProviderFactory() {

    }
}
