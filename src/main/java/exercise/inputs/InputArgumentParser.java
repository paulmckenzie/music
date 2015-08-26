package exercise.inputs;

import exercise.values.InputArgs;

@FunctionalInterface
public interface InputArgumentParser {
    InputArgs parse(String userInput);
}
