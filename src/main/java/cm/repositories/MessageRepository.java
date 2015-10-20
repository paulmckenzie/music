package cm.repositories;


import cm.values.Message;

import java.util.List;

public interface MessageRepository {
    void saveMessage(Message message);

    List<Message> getMessages(List<Long> ids);
}
