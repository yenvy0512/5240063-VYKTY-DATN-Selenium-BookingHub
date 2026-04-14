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

public class SearchPageTest {

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

    @Test(description = "SR-01 Trang tìm chuyến hiển thị form tìm kiếm")
    public void case_SR_001() {
        driver.get(Config.getBaseUrl() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Tìm chuyến')]"))).click();
        new Actions(driver).moveToElement(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]")))).perform();
        new Actions(driver).moveByOffset(1, 1).perform();
        Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".mb-6"))).getText().contains("Tìm chuyến xe"));
    }

    @Test(description = "SR-03 Tiêu đề trang tìm chuyến đúng")
    public void case_SR_003() {
        driver.get(Config.getBaseUrl() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Tìm chuyến')]"))).click();
        Assert.assertEquals(driver.getTitle(), "Tìm chuyến xe - BookingHub");
        
    }

    @Test(description = "SR-04 Heading Tìm chuyến xe hiển thị")
    public void case_SR_004() {
        driver.get(Config.getBaseUrl() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Tìm chuyến')]"))).click();
        new Actions(driver).moveToElement(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]")))).perform();
        new Actions(driver).moveByOffset(1, 1).perform();
        Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".mb-6"))).getText().contains("Tìm chuyến xe"));
        
    }

    @Test(description = "SR-05 Form tìm kiếm có đủ trường dữ liệu")
    public void case_SR_005() {
        driver.get(Config.getBaseUrl() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Tìm chuyến')]"))).click();
        new Actions(driver).moveToElement(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]")))).perform();
        new Actions(driver).moveByOffset(1, 1).perform();
        Assert.assertFalse(driver.findElements(By.id("search-departureLocationId")).isEmpty(), "Missing element");
        Assert.assertFalse(driver.findElements(By.id("search-arrivalLocationId")).isEmpty(), "Missing element");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("search-departureDate"))).click();
        Assert.assertFalse(driver.findElements(By.id("search-departureDate")).isEmpty(), "Missing element");
        
    }

    @Test(description = "SR-06 Nút tìm kiếm hiển thị")
    public void case_SR_006() {
        driver.get(Config.getBaseUrl() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Tìm chuyến')]"))).click();
        new Actions(driver).moveToElement(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]")))).perform();
        new Actions(driver).moveByOffset(1, 1).perform();
        Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".px-6"))).getText().contains("Tìm kiếm"));
        
    }

    @Test(description = "SR-08 Bấm tìm kiếm khi chưa đủ điều kiện báo lỗi")
    public void case_SR_008() {
        driver.get(Config.getBaseUrl() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Tìm chuyến')]"))).click();
        new Actions(driver).moveToElement(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]")))).perform();
        new Actions(driver).moveByOffset(1, 1).perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".mb-6"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".px-6"))).click();
        new Actions(driver).moveToElement(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".px-6")))).perform();
        new Actions(driver).moveByOffset(1, 1).perform();
        Assert.assertFalse(driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Vui lòng điền đầy đủ thông tin tìm kiếm\")]")).isEmpty());
        
    }

    @Test(description = "SR-09 Chỉ chọn điểm đi rồi tìm kiểm hiển thị lỗi thiếu thông tin")
    public void case_SR_009() {
        driver.get(Config.getBaseUrl() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Tìm chuyến')]"))).click();
        new Actions(driver).moveToElement(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]")))).perform();
        new Actions(driver).moveByOffset(1, 1).perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("search-departureLocationId"))).click();
        new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search-departureLocationId")))).selectByVisibleText("Hà Nội - Hoàng Mai");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".px-6"))).click();
        new Actions(driver).moveToElement(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".px-6")))).perform();
        new Actions(driver).moveByOffset(1, 1).perform();
        Assert.assertFalse(driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Vui lòng điền đầy đủ thông tin tìm kiếm\")]")).isEmpty());
        
    }

    @Test(description = "SR-10 Chỉ chọn điểm đến rồi tìm kiểm hiển thị lỗi thiếu thông tin")
    public void case_SR_010() {
        driver.get(Config.getBaseUrl() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Tìm chuyến')]"))).click();
        new Actions(driver).moveToElement(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]")))).perform();
        new Actions(driver).moveByOffset(1, 1).perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("search-arrivalLocationId"))).click();
        new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search-arrivalLocationId")))).selectByVisibleText("Hồ Chí Minh - Quận 1");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".px-6"))).click();
        new Actions(driver).moveToElement(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".px-6")))).perform();
        new Actions(driver).moveByOffset(1, 1).perform();
        Assert.assertFalse(driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Vui lòng điền đầy đủ thông tin tìm kiếm\")]")).isEmpty());
        
    }

    @Test(description = "SR-11 Chỉ chọn ngày đi hiển thị báo lỗi thiếu thông tin")
    public void case_SR_011() {
        driver.get(Config.getBaseUrl() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Tìm chuyến')]"))).click();
        new Actions(driver).moveToElement(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]")))).perform();
        new Actions(driver).moveByOffset(1, 1).perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("search-departureDate"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".react-datepicker__day--013"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".px-6"))).click();
        new Actions(driver).moveToElement(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".px-6")))).perform();
        new Actions(driver).moveByOffset(1, 1).perform();
        Assert.assertFalse(driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Vui lòng điền đầy đủ thông tin tìm kiếm\")]")).isEmpty());
        
    }

    @Test(description = "SR-12 Nhập đủ thông tin tìm kiếm thành công")
    public void case_SR_012() {
        driver.get(Config.getBaseUrl() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Tìm chuyến')]"))).click();
        new Actions(driver).moveToElement(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]")))).perform();
        new Actions(driver).moveByOffset(1, 1).perform();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("search-departureLocationId"))).click();
        new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search-departureLocationId")))).selectByVisibleText("Hà Nội - Long Biên");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("search-arrivalLocationId"))).click();
        new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("search-arrivalLocationId")))).selectByVisibleText("Hải Phòng - Lê Chân");
        wait.until(ExpectedConditions.elementToBeClickable(By.id("search-departureDate"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".react-datepicker__week--keyboard-selected"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".react-datepicker__day--015"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".px-6"))).click();
        new Actions(driver).moveToElement(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".px-6")))).perform();
        new Actions(driver).moveByOffset(1, 1).perform();
        
    }

}