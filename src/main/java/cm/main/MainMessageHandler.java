package cm.main;

import cm.inputs.InputArgumentParser;
import cm.inputs.InputHandler;
import cm.inputs.InputType;
import cm.values.InputArgs;

import java.util.List;
import java.util.Map;

public class MainMessageHandler implements MessageHandler {
    private final InputArgumentParser argumentParser;
    private final Map<InputType, InputHandler> handlers;

    public MainMessageHandler(final InputArgumentParser argumentParser, final Map<InputType, InputHandler> handlers) {
        this.argumentParser = argumentParser;
        this.handlers = handlers;
    }

    @Override
    public List<String> handleUserInput(final String input) {
        final InputArgs args = argumentParser.parse(input);
        return handlers.get(args.getInputType()).handleUserInput(args);
    }
}
