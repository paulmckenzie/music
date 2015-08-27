package exercise.inputs;

import exercise.values.InputArgs;
import exercise.values.Message;

import java.util.List;

@FunctionalInterface
public interface InputHandler {
    List<String> handleUserInput(InputArgs args);
}
