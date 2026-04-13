package base;

import config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

/**
 * Base class cho tất cả test.
 * Mở browser 1 lần cho cả class (@BeforeClass), các test method dùng chung driver.
 * Trước mỗi method chỉ reset về base URL (@BeforeMethod).
 */
public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void initDriver() {
        driver = createDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Config.getImplicitWaitSeconds()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Config.getPageLoadTimeoutSeconds()));
        driver.manage().window().maximize();
        driver.get(getBaseUrl());
    }

    @BeforeMethod(alwaysRun = true)
    public void resetToBaseUrl() {
        if (driver != null) {
            driver.get(getBaseUrl());
        }
    }

    @AfterMethod(alwaysRun = true)
    public void delayAfterTest() {
        int seconds = Config.getDelayAfterTestSeconds();
        if (seconds > 0 && driver != null) {
            try {
                Thread.sleep(seconds * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /** Subclass có thể gọi super.setUp() trong @BeforeMethod rồi navigate tiếp. */
    public void setUp() {
        resetToBaseUrl();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    /** URL mở khi init. Mặc định: web-customer. Override trong AdminBaseTest cho web-admin. */
    protected String getBaseUrl() {
        return Config.getBaseUrl();
    }

    public WebDriver getDriver() {
        return driver;
    }

    private WebDriver createDriver() {
        String browser = Config.getBrowser().toLowerCase().trim();
        switch (browser) {
            case "firefox" -> WebDriverManager.firefoxdriver().setup();
            case "edge", "msedge" -> WebDriverManager.edgedriver().setup();
            default -> WebDriverManager.chromedriver().setup();
        }
        return switch (browser) {
            case "firefox" -> new FirefoxDriver(new FirefoxOptions());
            case "edge", "msedge" -> new EdgeDriver(edgeOptions());
            default -> new ChromeDriver(new ChromeOptions());
        };
    }

    /** Edge: dùng edgedriver().setup() thay vì getInstance("edge") — tránh lệch phiên bản / không tải driver. */
    private static EdgeOptions edgeOptions() {
        EdgeOptions o = new EdgeOptions();
        o.addArguments("--disable-search-engine-choice-screen");
        return o;
    }
}
