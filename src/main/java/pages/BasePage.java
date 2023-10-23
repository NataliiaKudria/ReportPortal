package pages;

import static drivermanager.CustomWebDriverManager.getDriverInstance;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class BasePage {

    public WebElement getElement(By elem) {
        return getDriverInstance().findElement(elem);
    }
}
