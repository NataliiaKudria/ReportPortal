package selenide_tests.page_elements;

import static utils.SelenideElementHelper.findByXpath;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class SideBarPage {

    public SelenideElement getProjectsSelectorTab() {
        return findByXpath("//div[contains(@class,'sidebar__main-block')]/div");
    }

    public void checkSideBarIsVisible() {
        getProjectsSelectorTab().shouldBe(Condition.visible);
    }
}
