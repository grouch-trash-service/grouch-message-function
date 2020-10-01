package grouch.message.provider;

import grouch.message.model.Message;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultMessageProvider implements MessageProvider {

    private final TrashSchedule trashSchedule;

    @Override
    public Message getMessage() {
        return new Message(String.format("Trash Pickup is on %s...Now Scram!", trashSchedule.getSchedule()));
    }
}
