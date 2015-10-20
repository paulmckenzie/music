package exercise;

import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import exercise.main.ApplicationAssembly;
import exercise.main.MessageHandler;
import org.junit.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@SuppressWarnings("unused")
public class PostingStepDefinitions {
    private LocalDateTime now = LocalDateTime.MIN;
    private final MessageHandler messageHandler = ApplicationAssembly.makeMessageHandler(() -> now);

    @When("^(.+) posts these messages$")
    public void postMessages(final String user, final DataTable messages) throws Throwable {
        messages.raw().forEach(message -> {
            now = LocalDateTime.of(LocalDate.now(), LocalTime.parse(message.get(1)));
            messageHandler.handleUserInput(user + " -> " + message.get(0));
        });
    }

    @When("^the time is (.+)$")
    public void theTimeIs(final String time) throws Throwable {
        now = LocalDateTime.of(LocalDate.now(), LocalTime.parse(time));
    }

    @When("^(.+) follows (.+)$")
    public void follow(final String user, final String followedUser) throws Throwable {
        messageHandler.handleUserInput(user + " follows " + followedUser);
    }

    @Then("^the messages for (.+) are$")
    public void showMessages(final String user, final List<String> messages) throws Throwable {
        final List<String> responses = messageHandler.handleUserInput(user);
        Assert.assertEquals(messages, responses);
    }

    @Then("^the wall messages for (.+) are$")
    public void wall(final String user, final List<String> messages) throws Throwable {
        final List<String> responses = messageHandler.handleUserInput(user + " wall");
        Assert.assertEquals(messages, responses);
    }
}