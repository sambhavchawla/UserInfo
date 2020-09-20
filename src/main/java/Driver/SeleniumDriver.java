package Driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Class to return different browser based on Driver passed
 */
public class SeleniumDriver {

    private static String DRIVER_DIR_PATH = System.getProperty("user.dir") + "/src/main/resources/Driver/";

    public static WebDriver getWebDriver(Driver driverEnum) {
        switch (driverEnum) {
            case CHROME:
                return getChromeDriver();
            case FIREFOX:
                return getFirefoxDriver();
        }
        return null;
    }

    public static WebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.driver", DRIVER_DIR_PATH + "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver getFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", DRIVER_DIR_PATH + "geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
