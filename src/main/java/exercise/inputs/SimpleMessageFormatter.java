package exercise.inputs;

import exercise.values.Message;

public class SimpleMessageFormatter implements MessageFormatter {
    @Override
    public String format(Message message) {
        return message.getText();
    }
}
