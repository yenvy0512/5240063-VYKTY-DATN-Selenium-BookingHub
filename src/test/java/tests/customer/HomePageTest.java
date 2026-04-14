package tests.customer;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import config.Config;

public class HomePageTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(description = "HM-01 Trang chủ hiển thị thành công")
    public void case_HM_001() {
        driver.get(Config.getBaseUrl() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='customer-home-heading']"))).getText().contains("Đặt vé xe khách trực tuyến"));
        
    }

    @Test(description = "HM-03 Tiêu đề trang chủ đúng")
    public void case_HM_003() {
        driver.get(Config.getBaseUrl() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        Assert.assertEquals(driver.getTitle(), "BookingHub - Đặt vé xe khách trực tuyến");
        
    }

    @Test(description = "HM-04 Heading chính hiển thị")
    public void case_HM_004() {
        driver.get(Config.getBaseUrl() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='customer-home-heading']"))).getText().contains("Đặt vé xe khách trực tuyến"));
        
    }

    @Test(description = "HM-05 Nút tìm kiếm hiển thị và bấm được")
    public void case_HM_005() {
        driver.get(Config.getBaseUrl() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='home-search-submit']"))).getText().contains("Tìm chuyến"));
        
    }

    @Test(description = "HM-06 Form có Điểm đi Điểm đến Ngày đi")
    public void case_HM_006() {
        driver.get(Config.getBaseUrl() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        Assert.assertFalse(driver.findElements(By.id("departureLocationId")).isEmpty(), "Missing element");
        Assert.assertFalse(driver.findElements(By.id("arrivalLocationId")).isEmpty(), "Missing element");
        Assert.assertFalse(driver.findElements(By.id("departureDate")).isEmpty(), "Missing element");
        
    }

    @Test(description = "HM-07 Ấn tìm kiếm chuyển sang trang tìm kiếm")
    public void case_HM_007() {
        driver.get(Config.getBaseUrl() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        new Actions(driver).moveToElement(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".px-6")))).perform();
        new Actions(driver).moveByOffset(1, 1).perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("departureLocationId"))).click();
        new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("departureLocationId")))).selectByVisibleText("Hà Nội - Long Biên");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("arrivalLocationId"))).click();
        new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("arrivalLocationId")))).selectByVisibleText("Hải Phòng - Lê Chân");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("departureDate"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".react-datepicker__day--014"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='home-search-submit']"))).click();
        new Actions(driver).moveToElement(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='home-search-submit']")))).perform();
        Assert.assertEquals(driver.getTitle(), "Tìm chuyến xe - BookingHub");
        
    }

    @Test(description = "HM-08 Tiêu đề con hiển thị đúng")
    public void case_HM_008() {
        driver.get(Config.getBaseUrl() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='customer-home-subtitle']"))).getText().contains("Nhanh chóng - An toàn - Tiện lợi"));
        
    }

    @Test(description = "HM-09 Hiển thị đủ 3 khối")
    public void case_HM_009() {
        driver.get(Config.getBaseUrl() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        Assert.assertFalse(driver.findElements(By.cssSelector("[data-testid='customer-home-feature-0']")).isEmpty(), "Missing element");
        Assert.assertFalse(driver.findElements(By.cssSelector("[data-testid='customer-home-feature-1']")).isEmpty(), "Missing element");
        Assert.assertFalse(driver.findElements(By.cssSelector("[data-testid='customer-home-feature-2']")).isEmpty(), "Missing element");
        
    }

    @Test(description = "HM-10 Bấm tìm kiếm khi chưa chọn thông tin")
    public void case_HM_010() {
        driver.get(Config.getBaseUrl() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='home-search-submit']"))).click();
        Assert.assertFalse(driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Vui lòng điền đầy đủ thông tin\")]")).isEmpty());
        
    }

    @Test(description = "HM-11 Thông báo lỗi có thể hiện lần hai nếu bấm lại tìm kiếm")
    public void case_HM_011() {
        driver.get(Config.getBaseUrl() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='home-search-submit']"))).click();
        Assert.assertFalse(driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Vui lòng điền đầy đủ thông tin\")]")).isEmpty());
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='home-search-submit']"))).click();
        Assert.assertFalse(driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Vui lòng điền đầy đủ thông tin\")]")).isEmpty());
        
    }

}