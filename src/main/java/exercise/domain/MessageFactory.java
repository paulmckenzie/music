package exercise.domain;

import exercise.values.InputArgs;
import exercise.values.Message;

@FunctionalInterface
public interface MessageFactory {
    Message fromInput(InputArgs inputArgs);
}
