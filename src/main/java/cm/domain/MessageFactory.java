package cm.domain;

import cm.values.InputArgs;
import cm.values.Message;

@FunctionalInterface
public interface MessageFactory {
    Message fromInput(InputArgs inputArgs);
}
