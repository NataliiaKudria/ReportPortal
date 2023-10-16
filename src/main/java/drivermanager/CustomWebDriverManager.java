package drivermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.CustomLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CustomWebDriverManager {

    private WebDriver driver;

    public CustomWebDriverManager() {
    }

    public WebDriver getDriver() {
        CustomLogger.getLogger().info("Driver Setup");
        if (driver == null) driver = createDriver();
        return driver;
    }

    private WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
        return driver;
    }

    public void closeDriver() {
        CustomLogger.getLogger().info("Closing the current driver");
        driver.close();
        driver.quit();
        try {
            if (isChromeProcessRunning()) {
                CustomLogger.getLogger().info("Killing .exe processes");
                killChromedriverExeProcess();
            }
        } catch (Exception error) {
            CustomLogger.getLogger().debug("Error occurred", error);
        }
    }

    public void open(String url) {
        driver.get(url);
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

