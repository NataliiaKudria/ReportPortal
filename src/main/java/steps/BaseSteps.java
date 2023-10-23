package steps;

import pages.CommonPage;
import pages.LoginPage;
import pages.components.SideBarPage;

public class BaseSteps {

    public LoginPage loginPage() {
        return new LoginPage();
    }

    public SideBarPage sideBarPage() {
        return new SideBarPage();
    }

    public CommonPage commonPage() {
        return new CommonPage();
    }
}
