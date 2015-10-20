package cm.main;

import java.util.List;

@FunctionalInterface
public interface MessageHandler {
    List<String> handleUserInput(String input);
}
