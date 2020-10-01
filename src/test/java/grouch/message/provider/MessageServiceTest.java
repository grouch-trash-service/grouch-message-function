package grouch.message.provider;

import grouch.message.function.TrashFunction;
import grouch.message.function.TrashFunctionEvent;
import grouch.message.model.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MessageServiceTest {

    @Mock
    TrashFunction trashFunction;

    private MessageService messageService;
    private Message message;
    private TrashSchedule trashSchedule;

    @Before
    public void setUp() {
        messageService = new MessageService(trashFunction);
        message = new Message("Trash Pickup is on Tuesday...Now Scram!");

        trashSchedule = new TrashSchedule();
        trashSchedule.setType("default");
        trashSchedule.setSchedule("Tuesday");
    }

    @DisplayName("should get messages")
    @Test
    public void testGetMessage() {
        String date = new SimpleDateFormat(MessageService.DATE_FORMAT).format(new Date());
        TrashFunctionEvent trashFunctionEvent = new TrashFunctionEvent(date);
        doReturn(trashSchedule).when(trashFunction).getTrashSchedule(trashFunctionEvent);

        Message actualMessage = messageService.getMessage();

        assertEquals(message, actualMessage);
        verify(trashFunction).getTrashSchedule(trashFunctionEvent);
    }
}