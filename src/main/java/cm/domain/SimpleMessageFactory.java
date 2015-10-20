package cm.domain;

import cm.values.InputArgs;
import cm.values.Message;

public class SimpleMessageFactory implements MessageFactory {
    private final IdProvider idProvider;
    private final TimeProvider timeProvider;

    public SimpleMessageFactory(final IdProvider idProvider, final TimeProvider timeProvider) {
        this.idProvider = idProvider;
        this.timeProvider = timeProvider;
    }

    @Override
    public Message fromInput(final InputArgs inputArgs) {
        return new Message(idProvider.nextId(), inputArgs.getUsername(), inputArgs.getArgument().orElseThrow(IllegalArgumentException::new), timeProvider.now());
    }
}
