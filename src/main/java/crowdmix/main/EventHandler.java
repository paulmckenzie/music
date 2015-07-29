package crowdmix.main;

import crowdmix.events.*;

import java.util.List;

class EventHandler {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    EventHandler(final UserRepository userRepository, final MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    public void handle(final MessagePostedEvent event) {
        userRepository.findOrCreate(event.getUserName()).addPost(event.getTimestamp());
        messageRepository.saveMessage(event.getTimestamp(), event.getMessage());

    }

    public List<Message> handle(final ReadTimelineEvent event) {
        final User user = userRepository.findOrCreate(event.getUserName());
        return messageRepository.getMessages(user.getPostIds());
    }
}
