package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public class CommonPage extends BasePage {

    public WebElement getTableWithRuns() {
        return getElement(By.cssSelector("[class*='grid__grid']"));
    }

    public List<WebElement> getListOfRowsInTable() {
        return getTableWithRuns().findElements(By.xpath("./div[contains(@class,'gridRow')]"));
    }

    public WebElement getRowsNumberCounter() {
        return getElement(By.cssSelector("[class*='item-counter']"));
    }
}
