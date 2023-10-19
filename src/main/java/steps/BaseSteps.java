package steps;

import static drivermanager.CustomWebDriverManager.getDriverManagerInstance;

import org.openqa.selenium.WebDriver;
import pages.CommonPage;
import pages.LoginPage;
import pages.components.SideBarPage;
import utils.CustomLogger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaseSteps {

    public WebDriver getWebDriver() {
        return getDriverManagerInstance().getDriver();
    }

    public LoginPage loginPage() {
        return new LoginPage();
    }

    public SideBarPage sideBarPage() {
        return new SideBarPage();
    }

    public CommonPage commonPage() {
        return new CommonPage();
    }

    public void closeDriver() {
        CustomLogger.getLogger().info("Closing the current driver");
        getWebDriver().manage().deleteAllCookies();
        getWebDriver().close();
        getWebDriver().quit();
        try {
            if (isChromeProcessRunning()) {
                CustomLogger.getLogger().info("Killing .exe processes");
                killChromedriverExeProcess();
            }
        } catch (Exception error) {
            CustomLogger.getLogger().debug("Error occurred", error);
        }
    }

    private static void killChromedriverExeProcess() {
        String systemType = System.getProperty("os.name").toLowerCase();
        if (systemType.contains("win")) {
            try {
                Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
                CustomLogger.getLogger().info(".exe process is killed");
            } catch (IOException e) {
                CustomLogger.getLogger().error("Failed to close one or more driver .exe files");
            }
        }
    }

    private static boolean isChromeProcessRunning() throws Exception {
        Process process = Runtime.getRuntime().exec("tasklist");
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        boolean processPunning = false;
        while ((line = reader.readLine()) != null) {
            if (line.contains("chromedriver.exe")) {
                processPunning = true;
            }
        }
        return processPunning;
    }
}
