package tests.admin;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import config.Config;

public class AdminDashboardPageTest {

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

    @Test(description = "AD-01 Dashboard hiển thị sau khi đăng nhập")
    public void case_AD_001() {
        driver.get(Config.getBaseUrlAdmin() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        driver.get(Config.getBaseUrlAdmin() + "/login"); wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail"))).sendKeys(Config.getAdminUsername()); driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword()); driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
        Assert.assertEquals(driver.getTitle(), "Dashboard - BookingHub Admin");
         
    }

    @Test(description = "AD-02 Tiêu đề Dashboard đúng")
    public void case_AD_002() {
        driver.get(Config.getBaseUrlAdmin() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        driver.get(Config.getBaseUrlAdmin() + "/login"); wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail"))).sendKeys(Config.getAdminUsername()); driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword()); driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
        Assert.assertEquals(driver.getTitle(), "Dashboard - BookingHub Admin");
        Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".text-3xl"))).getText().contains("Dashboard"));
         
    }

    @Test(description = "AD-03 Kiểm tra hiển thị thông tin Tổng quan")
    public void case_AD_003() {
        driver.get(Config.getBaseUrlAdmin() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        driver.get(Config.getBaseUrlAdmin() + "/login"); wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail"))).sendKeys(Config.getAdminUsername()); driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword()); driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
        Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".mb-6 > .text-xl"))).getText().contains("Tổng quan hệ thống"));
         
    }

    @Test(description = "AD-04 Kiểm tra hiển thị thông tin Thống kê đặt vé")
    public void case_AD_004() {
        driver.get(Config.getBaseUrlAdmin() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        driver.get(Config.getBaseUrlAdmin() + "/login"); wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail"))).sendKeys(Config.getAdminUsername()); driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword()); driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
        Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".mb-8 > .text-xl"))).getText().contains("Thống kê đặt vé"));
         
    }

}