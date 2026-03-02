package base;

import config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

/**
 * Base class cho tất cả test. Khởi tạo WebDriver qua WebDriverManager,
 * mở ứng dụng BookingHub và đóng driver sau mỗi test.
 */
public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        driver = createDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Config.getImplicitWaitSeconds()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Config.getPageLoadTimeoutSeconds()));
        driver.manage().window().maximize();
        driver.get(getBaseUrl());
    }

    /** URL mở khi setUp. Mặc định: web-customer. Override trong AdminBaseTest cho web-admin. */
    protected String getBaseUrl() {
        return Config.getBaseUrl();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    private WebDriver createDriver() {
        io.github.bonigarcia.wdm.WebDriverManager.getInstance(Config.getBrowser()).setup();
        return switch (Config.getBrowser().toLowerCase()) {
            case "firefox" -> new FirefoxDriver(new FirefoxOptions());
            case "edge" -> new EdgeDriver();
            default -> new ChromeDriver(new ChromeOptions());
        };
    }
}
