package exercise.domain;

import exercise.values.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class InMemoryMessageRepository implements MessageRepository {
    private final Map<Long, Message> repo = new HashMap<>();

    @Override
    public void saveMessage(Message message) {
        if (repo.containsKey(message.getId()))
            throw new IllegalArgumentException("Message already stored with key " + message.getId());
        else
            repo.put(message.getId(),message);
    }

    @Override
    public List<Message> getMessages(List<Long> ids) {
        return ids.stream().map(repo::get).collect(toList());
    }
}