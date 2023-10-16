package steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.steps.Parameters;

public class LoginPageSteps extends BaseSteps {

    @Given("User opens the '$url' web site")
    public void openSite(String url) {
        getDriverManager().open(url);
    }

    @When("User logs in with the next data: $table")
    public void fillNameInput(ExamplesTable table) {
        Parameters parameters = table.getRowsAsParameters().get(0);
        String login = parameters.valueAs("login", String.class);
        String password = parameters.valueAs("password", String.class);

        getElement(loginPage().getNameInput()).sendKeys(login);
        getElement(loginPage().getPasswordInput()).sendKeys(password);
        getElement(loginPage().getSubmitButton()).click();
    }

    @When("On Common RP page, user clicks on the 'LAUNCHES' tab")
    public void clickOnTab() {
        getElement(loginPage().getLaunchesTab()).click();
    }

    @When("User closes the current browser window")
    public void closeBrowser() {
        getDriverManager().closeDriver();
    }
}
