package crowdmix;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@SuppressWarnings("unused")
public class StepDef {

    @Given("^some pre-conditions$")
    public void preConditions() {
    }

    @When("^an action happens$")
    public void anAction() {
    }

    @Then("^some post-conditions$")
    public void postConditions() {
        assertThat(1, equalTo(1));
    }
}