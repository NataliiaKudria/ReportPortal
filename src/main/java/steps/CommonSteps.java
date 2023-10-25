package steps;

import static org.junit.Assert.assertTrue;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import utils.CustomLogger;
import java.util.List;
import java.util.Optional;

public class CommonSteps extends BaseSteps {

    @When("On Main RP page, user clicks on the 'LAUNCHES' tab")
    public void clickOnTab() {
        sideBarPage().getLaunchesTab().click();
    }

    @When("On Main RP page in sidebar, user clicks on the 'PROJECTS SELECTOR' tab")
    public void clickOnProjectSelectorTab() {
        sideBarPage().getProjectsSelectorTab().click();
    }

    @Then("On Main RP page in sidebar, user should see the 'PROJECTS SELECTOR' tab")
    public void getProjectSelectorTab() {
        assertTrue(sideBarPage().getProjectsSelectorTab().isDisplayed());
    }

    @When("On Main RP page in Project Selector dropdown, user selects '$projectName' project")
    public void clickOnProjectSelectorTab(String projectName) {
        sideBarPage().getProjectsSelectorDropdown(projectName).click();
    }

    @Then("On Main RP page, user should see the table with runs")
    public void getTable() {
        assertTrue(mainPage().getTableWithRuns().isDisplayed());
    }

    @Then("On Main RP page, user verifies that the number of rows in the table is equal to value in counter")
    public void checkRunNumber() {
        List<WebElement> rows = mainPage().getListOfRowsInTable();
        int counter = Integer.parseInt(mainPage().getRowsNumberCounter().getText().split("of")[1].strip());
        Assert.assertEquals(counter, rows.size());
    }

    @When("On Main RP page, user clicks on the '$button' button")
    public void clickOnAddFilerBtn(String button) {
        mainPage().getBtnByName(button).click();
    }

    @When("On Main RP page in 'Launch Name' input, user enters the '$value' value")
    public void enterValue(String value) {
        WebElement input = mainPage().getNewLaunchNameInput();
        clearAndThenFillInputField(input, value);
    }

    @Then("On Main RP page on the header, user should see just created filter with name from DataHolder")
    public void getFilter() {
        String expectedFilterValue = dataHolder.getName();
        CustomLogger.getLogger().info("The expected filter name value is: {}", expectedFilterValue);
        Optional<WebElement> filterName = mainPage().getFilterList().stream().filter(newFilter -> newFilter.getText()
            .equals(expectedFilterValue)).findAny();
        assertTrue(filterName.isPresent());
    }

    @When("On Main RP page on the header, user deletes just created filter")
    public void deleteNewFilter() {
        mainPage().getFilterDeleteIcon(dataHolder.getName()).click();
    }
}
