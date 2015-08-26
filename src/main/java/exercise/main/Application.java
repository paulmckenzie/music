package exercise.main;

import exercise.values.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Application {
    static final String QUIT = "QUIT";
    private final Source source;
    private final MessageHandler processor;
    private final Sink sink;

    public Application(final Source source, final MessageHandler processor, final Sink sink) {
        this.source = source;
        this.processor = processor;
        this.sink = sink;
    }

    public static void main(String[] args) throws Exception {
        final Source source = new Source() {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            @Override
            public String nextInput() {
                try {
                    System.out.print("> ");
                    return bufferedReader.readLine().trim();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        final Sink sink = message -> System.out.println("> " + message.getText());


        final MessageHandler messageHandler = ApplicationAssembly.makeMessageHandler();
        final Application application = new Application(source, messageHandler, sink);
        application.run();
    }

    public void run() {
        // TODO see if I can make source emit a stream
        while (true) {
            final String input = source.nextInput();
            if (QUIT.equals(input)) {
                return;
            }
            processor.handleUserInput(input).forEach(sink::printMessage);
        }
    }
}
