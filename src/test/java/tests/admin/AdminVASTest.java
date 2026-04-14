package tests.admin;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import config.Config;

public class AdminVASTest {

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

    @Test(description = "VS-01 Trang Quản lý Dịch vụ hiển thị")
    public void case_VS_001() {
        driver.get(Config.getBaseUrlAdmin() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        driver.get(Config.getBaseUrlAdmin() + "/login"); wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail"))).sendKeys(Config.getAdminUsername()); driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword()); driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'VAS') or contains(normalize-space(.),'Dịch vụ')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Dịch vụ bổ sung"))).click();
        Assert.assertEquals(driver.getTitle(), "Quản lý Dịch vụ - BookingHub");
         
    }

    @Test(description = "VS-02 Tiêu đề trang quản lý dịch vụ hiển thị đúng")
    public void case_VS_002() {
        driver.get(Config.getBaseUrlAdmin() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        driver.get(Config.getBaseUrlAdmin() + "/login"); wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail"))).sendKeys(Config.getAdminUsername()); driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword()); driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'VAS') or contains(normalize-space(.),'Dịch vụ')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Dịch vụ bổ sung"))).click();
        Assert.assertEquals(driver.getTitle(), "Quản lý Dịch vụ - BookingHub");
         
    }

    @Test(description = "VS-03 Heading Quản lý dịch vụ hiển thị đúng")
    public void case_VS_003() {
        driver.get(Config.getBaseUrlAdmin() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        driver.get(Config.getBaseUrlAdmin() + "/login"); wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail"))).sendKeys(Config.getAdminUsername()); driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword()); driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'VAS') or contains(normalize-space(.),'Dịch vụ')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Dịch vụ bổ sung"))).click();
        Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".text-2xl"))).getText().contains("Quản lý dịch vụ bổ sung"));
         
    }

    @Test(description = "VS-04 Nút thêm dịch vụ hiển thị")
    public void case_VS_004() {
        driver.get(Config.getBaseUrlAdmin() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        driver.get(Config.getBaseUrlAdmin() + "/login"); wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail"))).sendKeys(Config.getAdminUsername()); driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword()); driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'VAS') or contains(normalize-space(.),'Dịch vụ')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Dịch vụ bổ sung"))).click();
        Assert.assertFalse(driver.findElements(By.cssSelector("[data-testid='admin-vas-btn-add']")).isEmpty(), "Missing element");
        Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='admin-vas-btn-add']"))).getText().contains("+ Thêm dịch vụ mới"));
         
    }

    @Test(description = "VS-05 Tìm kiếm dịch vụ theo từ khóa")
    public void case_VS_005() {
        driver.get(Config.getBaseUrlAdmin() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        driver.get(Config.getBaseUrlAdmin() + "/login"); wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail"))).sendKeys(Config.getAdminUsername()); driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword()); driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'VAS') or contains(normalize-space(.),'Dịch vụ')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Dịch vụ bổ sung"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-vas-search-input']"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vas-search-input']"))).clear(); wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vas-search-input']"))).sendKeys("Aqua");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-vas-search-submit']"))).click();
        Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".border-t > .font-medium"))).getText().contains("Aqua"));
         
    }

    @Test(description = "VS-07 Thêm mới dịch vụ không nhập thông tin")
    public void case_VS_007() {
        driver.get(Config.getBaseUrlAdmin() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        driver.get(Config.getBaseUrlAdmin() + "/login"); wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail"))).sendKeys(Config.getAdminUsername()); driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword()); driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'VAS') or contains(normalize-space(.),'Dịch vụ')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Dịch vụ bổ sung"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-vas-btn-add']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50:nth-child(2)"))).click();
        Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div:nth-child(1) > .px-3"))).isEnabled());
         
    }

    @Test(description = "VS-08 Thêm mới dịch vụ thành công")
    public void case_VS_008() {
        driver.get(Config.getBaseUrlAdmin() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        driver.get(Config.getBaseUrlAdmin() + "/login"); wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail"))).sendKeys(Config.getAdminUsername()); driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword()); driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'VAS') or contains(normalize-space(.),'Dịch vụ')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Dịch vụ bổ sung"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-vas-btn-add']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(1) > .px-3"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(1) > .px-3"))).clear(); wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(1) > .px-3"))).sendKeys("Aqua");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(2) > .px-3"))).click();
        new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div:nth-child(2) > .px-3")))).selectByVisibleText("Nước uống");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(3) > .px-3"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(3) > .px-3"))).clear(); wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(3) > .px-3"))).sendKeys("1000");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50:nth-child(2)"))).click();
        Assert.assertFalse(driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Tạo dịch vụ thành công!\")]")).isEmpty());
         
    }

    @Test(description = "VS-09 Chỉnh sửa dịch vụ")
    public void case_VS_009() {
        driver.get(Config.getBaseUrlAdmin() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        driver.get(Config.getBaseUrlAdmin() + "/login"); wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail"))).sendKeys(Config.getAdminUsername()); driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword()); driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'VAS') or contains(normalize-space(.),'Dịch vụ')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Dịch vụ bổ sung"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".border-t:nth-child(1) .text-blue-600"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(3) > .px-3"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(3) > .px-3"))).clear(); wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(3) > .px-3"))).sendKeys("10000");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50:nth-child(2)"))).click();
        Assert.assertFalse(driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Cập nhật dịch vụ thành công!\")]")).isEmpty());
         
    }

    @Test(description = "VS-10 Xóa dịch vụ thành công")
    public void case_VS_010() {
        driver.get(Config.getBaseUrlAdmin() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        driver.get(Config.getBaseUrlAdmin() + "/login"); wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail"))).sendKeys(Config.getAdminUsername()); driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword()); driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'VAS') or contains(normalize-space(.),'Dịch vụ')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Dịch vụ bổ sung"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".border-t:nth-child(1) .text-red-600"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']"))).click();
        Assert.assertFalse(driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Xóa dịch vụ thành công!\")]")).isEmpty());
         
    }

    @Test(description = "VS-11 Chỉnh sửa dịch vụ xóa thông tin bắt buộc nhập báo lỗi")
    public void case_VS_011() {
        driver.get(Config.getBaseUrlAdmin() + "/");
        driver.manage().window().setSize(new Dimension(945, 1012));
        driver.get(Config.getBaseUrlAdmin() + "/login"); wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail"))).sendKeys(Config.getAdminUsername()); driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword()); driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'VAS') or contains(normalize-space(.),'Dịch vụ')]"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Dịch vụ bổ sung"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".border-t:nth-child(1) .text-blue-600"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".fixed"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(1) > .px-3"))).clear(); wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(1) > .px-3"))).sendKeys("");
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50:nth-child(2)"))).click();
        Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div:nth-child(1) > .px-3"))).isEnabled());
         
    }

}