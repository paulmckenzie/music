package cm.values;

import cm.inputs.InputType;

import java.util.Optional;

public class InputArgs {
    private final InputType inputType;
    private final String username;
    private final Optional<String> argument;

    public InputArgs(final InputType inputType, final String username, final Optional<String> argument) {
        this.inputType = inputType;
        this.username = username;
        this.argument = argument;
    }

    public InputType getInputType() {
        return inputType;
    }

    public String getUsername() {
        return username;
    }

    public Optional<String> getArgument() {
        return argument;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InputArgs inputArgs = (InputArgs) o;

        if (inputType != inputArgs.inputType) return false;
        if (username != null ? !username.equals(inputArgs.username) : inputArgs.username != null) return false;
        return !(argument != null ? !argument.equals(inputArgs.argument) : inputArgs.argument != null);

    }

    @Override
    public int hashCode() {
        int result = inputType != null ? inputType.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (argument != null ? argument.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "InputArgs{" +
                "inputType=" + inputType +
                ", username='" + username + '\'' +
                ", argument=" + argument +
                '}';
    }
}
