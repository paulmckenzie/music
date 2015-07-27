package crowdmix.events;

import java.util.HashMap;
import java.util.Map;

enum EventType {
    POST, WALL, FOLLOW, READ;

    private static final Map<String, EventType> TokenLookupMap = new HashMap<String, EventType>() {
        {
            put("read", EventType.READ);
            put("->", EventType.POST);
            put("follows", EventType.FOLLOW);
            put("wall", EventType.WALL);
        }
    };

    static EventType fromToken(final String token) {
        return TokenLookupMap.get(token == null ? "read" : token);
    }
}
