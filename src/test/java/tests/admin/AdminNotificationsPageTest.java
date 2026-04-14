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

public class AdminNotificationsPageTest {

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

	@Test(description = "NT-01 Trang Thông báo hiển thị")
	public void case_NT_001() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Thông báo')]")))
				.click();
		Assert.assertEquals(driver.getTitle(), "Thông báo - BookingHub Admin");

	}

	@Test(description = "NT-02 Tiêu đề trang thông báo đúng")
	public void case_NT_002() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Thông báo')]")))
				.click();
		Assert.assertEquals(driver.getTitle(), "Thông báo - BookingHub Admin");

	}

	@Test(description = "NT-03 Ô tìm kiếm thông báo hiển thị")
	public void case_NT_003() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Thông báo')]")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".pl-10"))).click();
		Assert.assertFalse(driver.findElements(By.cssSelector(".pl-10")).isEmpty(), "Missing element");

	}

	@Test(description = "NT-04 Nút tải lại hiển thị")
	public void case_NT_004() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Thông báo')]")))
				.click();
		Assert.assertFalse(driver.findElements(By.cssSelector(".disabled\\3Aopacity-50")).isEmpty(), "Missing element");

	}

	@Test(description = "NT-05 Nút đánh dấu đã đọc hiển thị")
	public void case_NT_005() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Thông báo')]")))
				.click();
		Assert.assertFalse(driver.findElements(By.cssSelector(".bg-green-600")).isEmpty(), "Missing element");

	}

	@Test(description = "NT-06 Đánh dấu tất cả là đã đọc thông báo")
	public void case_NT_006() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Thông báo')]")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".bg-green-600"))).click();
		Assert.assertFalse(driver
				.findElements(By.xpath("//*[contains(normalize-space(.),\"Đã đánh dấu tất cả thông báo là đã đọc\")]"))
				.isEmpty());

	}

	@Test(description = "NT-07 Tải lại trang thông báo")
	public void case_NT_007() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Thông báo')]")))
				.click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[contains(.,'Tạo') or contains(.,'Thêm thông báo')]"))).click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Đã tải lại thông báo\")]")).isEmpty());

	}

}