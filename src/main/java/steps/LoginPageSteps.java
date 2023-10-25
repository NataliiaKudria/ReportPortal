package steps;

import static drivermanager.CustomWebDriverManager.getDriverInstance;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.steps.Parameters;

public class LoginPageSteps extends BaseSteps {

    @Given("User opens the '$url' web site")
    public void openSite(String url) {
        getDriverInstance().get(url);
    }

    @When("User logs in with the next data: $table")
    public void fillNameInput(ExamplesTable table) {
        Parameters parameters = table.getRowsAsParameters().get(0);
        String login = parameters.valueAs("login", String.class);
        String password = parameters.valueAs("password", String.class);

        loginPage().getNameInput().sendKeys(login);
        loginPage().getPasswordInput().sendKeys(password);
        loginPage().getSubmitButton().click();
    }
}
