package cm.inputs;

import cm.values.InputArgs;

@FunctionalInterface
public interface InputArgumentParser {
    InputArgs parse(String userInput);
}
