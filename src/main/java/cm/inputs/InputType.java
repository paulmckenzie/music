package cm.inputs;

import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

public enum InputType {
    POST, WALL, FOLLOW, READ;

    private static final Map<String, InputType> TokenLookupMap = new HashMap<>(InputType.values().length);

    static {
        TokenLookupMap.put("wall", WALL);
        TokenLookupMap.put("follows", FOLLOW);
        TokenLookupMap.put("->", POST);
        TokenLookupMap.put("read", READ);
    }

    static InputType fromToken(final String token) {
        if (TokenLookupMap.containsKey(token))
            return TokenLookupMap.get(token);
        else
            throw new IllegalArgumentException(format("Unknown token: %s", token));
    }
}
