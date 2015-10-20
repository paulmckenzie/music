package cm.inputs;

import cm.values.Message;

@FunctionalInterface
public interface MessageFormatter {
    String format(Message message);
}
