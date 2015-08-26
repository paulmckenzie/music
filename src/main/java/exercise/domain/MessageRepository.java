package exercise.domain;


import exercise.values.Message;

import java.util.List;

public interface MessageRepository {
    void saveMessage(Message message);

    List<Message> getMessages(List<Long> ids);
}
