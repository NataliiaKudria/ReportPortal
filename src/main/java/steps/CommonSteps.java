package steps;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CommonSteps extends BaseSteps {

    @When("On Common RP page, user clicks on the 'LAUNCHES' tab")
    public void clickOnTab() {
        sideBarPage().getLaunchesTab().click();
    }

    @When("User closes the current window")
    public void closeWindow() {
        closeDriver();
    }

    @When("On Common RP page in sidebar, user clicks on the 'PROJECTS SELECTOR' tab")
    public void clickOnProjectSelectorTab() {
        sideBarPage().getProjectsSelectorTab().click();
    }

    @Then("On Common RP page in sidebar, user should see the 'PROJECTS SELECTOR' tab")
    public void getProjectSelectorTab() {
        Assert.assertTrue(sideBarPage().getProjectsSelectorTab().isDisplayed());
    }

    @When("On Common RP page in Project Selector dropdown, user selects '$projectName' project")
    public void clickOnProjectSelectorTab(String projectName) {
        sideBarPage().getProjectsSelectorDropdown(projectName).click();
    }

    @Then("On Common RP page, user should see the table with runs")
    public void getTable() {
        Assert.assertTrue(commonPage().getTableWithRuns().isDisplayed());
    }

    @Then("On Common RP page, user verifies that the number of rows in the table is equal to value in counter")
    public void checkRunNumber() {
        List<WebElement> rows = commonPage().getListOfRowsInTable();
        int counter = Integer.parseInt(commonPage().getRowsNumberCounter().getText().split("of")[1].strip());
        Assert.assertEquals(counter, rows.size());
    }
}
