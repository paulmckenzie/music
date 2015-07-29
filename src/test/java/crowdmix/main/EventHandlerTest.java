package crowdmix.main;

import crowdmix.events.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static java.util.stream.LongStream.range;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EventHandlerTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private MessageRepository messageRepository;

    private EventHandler eventHandler;
    private User user1;
    private List<Message> user1Messages;
    private List<MessagePostedEvent> user1Events;
    private List<Long> user1Ids;

    @Before
    public void setUp() {
        user1 = new User("User1");
        user1Events = eventsForUser(user1, 100, 105);
        user1Messages = extractMessagesFromEvents(user1Events);
        user1Ids = extractIdsFromMessages(user1Messages);
        eventHandler = new EventHandler(userRepository, messageRepository);
    }

    private List<Long> extractIdsFromMessages(final List<Message> messages) {
        return messages.stream().map(Message::getId).collect(toList());
    }

    private List<Message> extractMessagesFromEvents(final List<MessagePostedEvent> events) {
        return events.stream().map(this::extractMessageFromMessagePostedEvent).collect(toList());
    }

    private Message extractMessageFromMessagePostedEvent(final MessagePostedEvent event) {
        return new Message(event.getTimestamp(), event.getMessage());
    }

    private List<MessagePostedEvent> eventsForUser(final User user, final long startInclusive, final long endExclusive) {
        return range(startInclusive, endExclusive)
                .boxed()
                .map(id -> new MessagePostedEvent(user.getUserName(), id, "some text here -" + id))
                .collect(toList());
    }

    @Test
    public void aMessagePostedIsStoredByTheUserAndTheMessageRepository() {
        when(userRepository.findOrCreate(user1.getUserName())).thenReturn(user1);
        user1Events.forEach(eventHandler::handle);
        assertEquals(user1Ids, user1.getPostIds());
        user1Messages.forEach(msg -> verify(messageRepository).saveMessage(msg.getId(), msg.getText()));
    }

    @Test
    public void readTimelineGetsAllTheMessagesForTheUser() {
        user1Ids.stream().forEach(user1::addPost);
        when(userRepository.findOrCreate(user1.getUserName())).thenReturn(user1);
        when(messageRepository.getMessages(user1Ids)).thenReturn(user1Messages);
        final List<Message> gottenMessages = eventHandler.handle(new ReadTimelineEvent(user1.getUserName()));
        assertEquals(user1Messages, gottenMessages);
    }
}