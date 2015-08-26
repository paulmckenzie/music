package exercise.main;

import exercise.values.Message;

import java.util.List;

@FunctionalInterface
public interface MessageHandler {
    List<Message> handleUserInput(String input);
}
