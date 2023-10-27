package steps;

import static drivermanager.CustomWebDriverManager.waitForTime;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utils.StringUtils.getRandomStringFromFile;

import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import utils.CustomLogger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class CommonSteps extends BaseSteps {

    private static final String FILE_FOR_FILTER_NAMING = "filterNaming";
    private static final String ADD_FILTER_BUTTON = "Add filter";
    private static final String SAVE_FILTER_BUTTON = "Save";
    private static final String ADD_BUTTON = "Add";

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

    @When("User creates '$number' new launch {filter|filters} and saves {their names|it} to DataHolder")
    public void createNewFilters(int number) {
        ArrayList<String> filters = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            clickOnAddFilerBtn(ADD_FILTER_BUTTON);
            enterValue(getRandomStringFromFile(FILE_FOR_FILTER_NAMING, 3));
            clickOnAddFilerBtn(SAVE_FILTER_BUTTON);

            String filterName = getRandomStringFromFile(FILE_FOR_FILTER_NAMING, 7);
            filters.add(filterName);

            WebElement input = filterModal().getNameInputOnModal();
            clearAndThenFillInputField(input, filterName);
            filterModal().getModalTitle().click();
            filterModal().getModalBtnByName(ADD_BUTTON).click();

            dataHolder.setListOfFilters(filters);
            CustomLogger.getLogger().info("The new filter name is: {}", dataHolder.getListOfFilters().get(i));
        }
    }

    @When("User deletes all newly created filters that were saved to DataHolder")
    public void deleteNewFilters() {
        waitForTime(50, TimeUnit.SECONDS);
        List<String> newFilters = dataHolder.getListOfFilters();
        deleteFilters(newFilters);
    }

    @When("On the Main page, user deletes all filters from UI")
    public void deleteAllFilters() {
        waitForTime(50, TimeUnit.SECONDS);
        Optional<List<String>> list = Optional.of(mainPage().getFilterList()
            .stream().map(WebElement::getText).collect(Collectors.toList()));
        if (!list.get().isEmpty()) {
            deleteFilters(list.get());
        }
    }

    private void deleteFilters(List<String> list) {
        CustomLogger.getLogger().info("The filters to be deleted: {}", list);
        for (String s : list) {
            String currentFilter = mainPage().getFilterByName(s).getText();
            try {
                mainPage().getFilterDeleteIcon(currentFilter);
            } catch (NoSuchElementException ex) {
                mainPage().getFilterByName(currentFilter).click();
            }
            mainPage().getFilterDeleteIcon(currentFilter).click();
            CustomLogger.getLogger().info("Filter with name '{}' has been deleted", currentFilter);
        }
    }

    @Then("User verifies that newly created and saved to DataHolder filters are all displayed on UI")
    public void checkNewFiltersDisplayed() {
        waitForTime(60, TimeUnit.SECONDS);
        List<String> newFilters = dataHolder.getListOfFilters().stream().sorted().collect(Collectors.toList());
        CustomLogger.getLogger().info("Filters list from DataHolder is: {}", newFilters);
        List<String> filtersFromUi = mainPage().getFilterList()
            .stream().map(WebElement::getText).sorted().collect(Collectors.toList());
        CustomLogger.getLogger().info("Filters list from UI is: {}", filtersFromUi);
        assertEquals(newFilters, filtersFromUi);
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
