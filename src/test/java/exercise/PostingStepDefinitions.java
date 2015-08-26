package exercise;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import exercise.main.MessageHandler;
import exercise.main.ApplicationAssembly;
import exercise.values.Message;
import org.junit.Assert;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public class PostingStepDefinitions {
    private final MessageHandler messageHandler = ApplicationAssembly.makeMessageHandler();

    @When("^(.+) posts these messages$")
    public void postMessages(final String user, final List<String> messages) throws Throwable {
        messages.forEach(message -> messageHandler.handleUserInput(user + " -> " + message));
    }

    @When("^(.+) follows (.+)$")
    public void follow(final String user, final String followedUser) throws Throwable {
        messageHandler.handleUserInput(user + " follows " + followedUser);
    }

    @Then("^the messages for (.+) are$")
    public void showMessages(final String user, final List<String> messages) throws Throwable {
        final List<String> responses =  messageHandler.handleUserInput(user).stream().map(Message::getText).collect(Collectors.toList());
        Assert.assertEquals(messages, responses);
    }

    @Then("^the wall messages for (.+) are$")
    public void wall(final String user, final List<String> messages) throws Throwable {
        final List<String> responses =  messageHandler.handleUserInput(user + " wall").stream().map(Message::getText).collect(Collectors.toList());
        Assert.assertEquals(messages, responses);
    }
}