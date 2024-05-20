package Common;

import helpers.PropertiesHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.driver.DriverManager;
import utils.driver.WebUI;

import java.time.Duration;


@Listeners(TestListener.class)
public class SetupBrowser extends LinkedPage {

    public SetupBrowser() {
        PropertiesHelper.loadAllFiles();
    }


    @BeforeMethod
    @Parameters({"browser"})
    public static void createDriver(@Optional("chrome") String browser) {
        WebDriver driver = SetupBrowser(browser);
        DriverManager.setDriver(driver);
    }

    public static WebDriver SetupBrowser(String browserName) {
        WebDriver driver;
        switch (browserName.toLowerCase()) {
            case "chrome":
                driver = initChromeDriver();
                break;
            case "firefox":
                driver = initFirefoxDriver();
                break;
            case "edge":
                driver = initEdgeDriver();
                break;
            default:
                System.out.println("Browser: " + browserName + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver();
        }
        return driver;
    }

    private static WebDriver initChromeDriver() {
        WebDriver driver;

        System.out.println("Launching Chrome browser...");
        WebDriverManager.chromedriver().clearDriverCache().setup();
//        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver initEdgeDriver() {
        WebDriver driver;

        System.out.println("Launching Edge browser...");
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver initFirefoxDriver() {
        WebDriver driver;

        System.out.println("Launching Firefox browser...");
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        return driver;
    }

    @AfterMethod
    public static void close(ITestResult result) throws InterruptedException {
        if (DriverManager.getDriver() != null) {
            WebUI.Sleep(3);
            DriverManager.quit();
        }
    }
}
