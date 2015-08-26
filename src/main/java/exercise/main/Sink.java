package exercise.main;

import exercise.values.Message;

@FunctionalInterface
public interface Sink {
    void printMessage(Message message);
}
