package exercise.inputs;

import exercise.values.InputArgs;

import java.util.List;

@FunctionalInterface
public interface InputHandler {
    List<String> handleUserInput(InputArgs args);
}
