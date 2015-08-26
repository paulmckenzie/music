package exercise.main;

import exercise.values.Message;
import exercise.values.InputArgs;
import exercise.inputs.InputArgumentParser;
import exercise.inputs.InputHandler;
import exercise.inputs.InputType;

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
    public List<Message> handleUserInput(final String input) {
        final InputArgs args = argumentParser.parse(input);
        return handlers.get(args.getInputType()).handleUserInput(args);
    }
}
