package crowdmix.main;

import java.util.HashMap;
import java.util.Map;

enum CommandType {
    POST, WALL, FOLLOW, READ;

    private static final Map<String, CommandType> TokenLookupMap = new HashMap<String, CommandType>() {
        {
            put("read", CommandType.READ);
            put("->", CommandType.POST);
            put("follows", CommandType.FOLLOW);
            put("wall", CommandType.WALL);
        }
    };

    static CommandType fromToken(final String token) {
        return TokenLookupMap.get(token == null ? "read" : token);
    }
}
