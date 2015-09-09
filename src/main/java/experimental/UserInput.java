package experimental;

import java.util.function.Supplier;

public class UserInput implements Supplier<String> {
    @Override
    public String get() {
        return "AAA";
    }
}
