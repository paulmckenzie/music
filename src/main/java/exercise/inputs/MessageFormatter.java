package exercise.inputs;

import exercise.values.Message;

@FunctionalInterface
public interface MessageFormatter {
    String format(Message message);
}
