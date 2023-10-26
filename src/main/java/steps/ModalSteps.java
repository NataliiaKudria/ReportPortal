package steps;

import org.jbehave.core.annotations.When;
import org.openqa.selenium.WebElement;
import utils.CustomLogger;
import utils.FileHelper;
import java.util.Random;

public class ModalSteps extends BaseSteps {

    @When("On the 'ADD FILTER' modal, user fills in the name input field with random value and saves it to DataHolder")
    public void setFilterName() {
        String filterName = getRandomStringFromFile("filterNaming");
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

    private String getRandomStringFromFile(String file) {
        String initialText = deleteNonAlphabeticChars(new FileHelper().readFile(file));
        StringBuilder builderForString = new StringBuilder();
        Random random = new Random();
        while (builderForString.length() < 7) {
            int index = (int) (random.nextFloat() * initialText.length());
            builderForString.append(initialText.charAt(index));
        }
        return builderForString.toString();
    }

    private String deleteNonAlphabeticChars(String string) {
        return string.replaceAll("[^a-zA-Z]", "");
    }
}
