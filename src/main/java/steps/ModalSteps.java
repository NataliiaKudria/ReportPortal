package steps;

import org.jbehave.core.annotations.When;
import org.openqa.selenium.WebElement;
import utils.CustomLogger;
import java.util.Random;

public class ModalSteps extends BaseSteps {

    @When("On the 'ADD FILTER' modal, user fills in the name input field with random value and saves it to DataHolder")
    public void setFilterName() {
        String filterName = getRandomString();
        WebElement input = filterModal().getNameInputOnModal();
        clearAndThenFillInputField(input, filterName);
        unFocus();
        dataHolder.setName(filterName);
        CustomLogger.getLogger().info("The new filter name is: {}", dataHolder.getName());
    }

    @When("On the 'ADD FILTER' modal, user clicks on the '$button' button")
    public void clickOnButton(String button) {
        filterModal().getModalBtnByName(button).click();
    }

    private void unFocus() {
        filterModal().getModalTitle().click();
    }

    private String getRandomString() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
    }
}
