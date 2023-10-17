package steps;

import org.jbehave.core.annotations.When;

public class CommonSteps extends BaseSteps{

    @When("On Common RP page, user clicks on the 'LAUNCHES' tab")
    public void clickOnTab() {
        getElement(commonPage().getLaunchesTab()).click();
    }

    @When("On Common RP page, user clicks on the 'FILTERS' tab")
    public void clickOnFilterTab() {
        getElement(commonPage().getFiltersTab()).click();
    }

    @When("User closes the current browser window")
    public void close() {
        closeDriver();
    }
}
