package selenide_tests;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.testng.ScreenShooter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import selenide_tests.page_elements.LoginPage;
import selenide_tests.page_elements.SideBarPage;
import utils.dto.User;

@Listeners({ ScreenShooter.class})
public class ReportPortalTests {

    private final LoginPage loginPage = new LoginPage();
    private final SideBarPage sideBarPage = new SideBarPage();
    private final User defaultUser = new User();
    private final static String WEB_SITE_URL = "https://rp.epam.com/ui/#login";

    @BeforeTest
    public void takeScreenshot() {
        ScreenShooter.captureSuccessfulTests = true;
        Configuration.reportsFolder = "target/screenshots";
    }

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
