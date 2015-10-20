package exercise.main;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Application {
    private final Stream<String> source;
    private final MessageHandler processor;
    private final Consumer<List<String>> sink;

    public Application(final Stream<String> source, final MessageHandler processor, final Consumer<List<String>> sink) {
        this.source = source;
        this.processor = processor;
        this.sink = sink;
    }

    public void run() {
        source.map(processor::handleUserInput).forEach(sink::accept);
    }

    public static void main(String[] args) throws Exception {
        final Supplier<String> source = new LineReader();
        final Consumer<List<String>> sink = messages -> messages.forEach(message -> System.out.println("> " + message));
        final MessageHandler messageHandler = ApplicationAssembly.makeMessageHandler();
        final Application application = new Application(Stream.generate(source), messageHandler, sink);
        application.run();
    }
}
