package selenide_tests;

import static com.codeborne.selenide.Selenide.*;

import org.testng.annotations.Test;
import selenide_tests.page_elements.LoginPage;
import selenide_tests.page_elements.SideBarPage;
import utils.dto.User;

public class ReportPortalTests {

    private final LoginPage loginPage = new LoginPage();
    private final SideBarPage sideBarPage = new SideBarPage();
    private final User defaultUser = new User();
    private final static String WEB_SITE_URL = "https://rp.epam.com/ui/#login";

    @Test
    public void checkUserCanLogIn() {
        open(WEB_SITE_URL);
        loginPage.setLogin(defaultUser.getLogin());
        loginPage.setPassword(defaultUser.getPassword());
        loginPage.clickOnSubmitBtn();
        loginPage.waitTillOauthPageVisible();
        back();
        loginPage.clickOnSubmitBtn();
        sideBarPage.checkSideBarIsVisible();
    }
}
