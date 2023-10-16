package pages;

import org.openqa.selenium.By;

public class LoginPage {

    public By getNameInput() {
        return By.xpath("//div[contains(@class,'login-field')]//input");
    }

    public By getPasswordInput() {
        return By.xpath("//div[contains(@class,'type-password')]//input");
    }

    public By getSubmitButton() {
        return By.tagName("button");
    }
}
