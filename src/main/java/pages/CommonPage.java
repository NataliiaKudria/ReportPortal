package pages;

import org.openqa.selenium.By;

public class CommonPage {
    public By getLaunchesTab() {
        return By.xpath("//div[contains(@class,'top-block')]/div[2]");
    }
    public By getFiltersTab() {
        return By.xpath("//div[contains(@class,'top-block')]/div[3]");
    }
}
