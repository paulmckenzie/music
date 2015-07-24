package crowdmix;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.List;

@SuppressWarnings("unused")
public class PostingStepDefinitions {
    @When("^(.+) posts these messages$")
    public void postMessages(final String user, final List<String> messages) throws Throwable {
    }


    @Then("^the messages for (.+) are$")
    public void showMessages(final String user, final List<String> messages) throws Throwable {
    }
}