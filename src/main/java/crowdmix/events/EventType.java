package crowdmix.events;

import java.util.HashMap;
import java.util.Map;

enum EventType {
    POST, WALL, FOLLOW, READ;

    private static final Map<String, EventType> TokenLookupMap = new HashMap<>();

    static {
        TokenLookupMap.put("wall", WALL);
        TokenLookupMap.put("follows", FOLLOW);
        TokenLookupMap.put("->", POST);
        TokenLookupMap.put("read", READ);
    }

    static EventType fromToken(final String token) {
        return TokenLookupMap.get(token == null ? "read" : token);
    }
}
