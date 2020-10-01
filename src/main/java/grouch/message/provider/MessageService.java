package grouch.message.provider;

import grouch.message.function.TrashFunction;
import grouch.message.function.TrashFunctionEvent;
import grouch.message.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class MessageService implements MessageProvider {

    private final TrashFunction trashFunction;
    public static final String DATE_FORMAT = "YYYY-MM-dd";
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);

    @Autowired
    public MessageService(final TrashFunction trashFunction) {
        this.trashFunction = trashFunction;
    }

    public Message getMessage() {
        String date = simpleDateFormat.format(new Date());
        TrashFunctionEvent trashFunctionEvent = new TrashFunctionEvent(date);
        TrashSchedule trashSchedule = trashFunction.getTrashSchedule(trashFunctionEvent);
        MessageProvider messageProvider = MessageProviderFactory.build(trashSchedule);
        return messageProvider.getMessage();
    }
}
