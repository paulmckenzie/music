package crowdmix.events;

import java.util.List;

public interface MessageRepository {
    void saveMessage(final Long id, final String text);

    List<Message> getMessages(List<Long> ids);
}
