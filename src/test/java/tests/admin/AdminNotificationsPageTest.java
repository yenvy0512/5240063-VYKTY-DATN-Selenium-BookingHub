package tests.admin;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AdminNotificationsPageTest {

	private WebDriver driver;
	private WebDriverWait wait;

	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
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

	private void loginAdmin() {
		driver.get(Config.getBaseUrlAdmin() + "/");

		WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.id("admin-usernameOrEmail")));
		username.clear();
		username.sendKeys(Config.getAdminUsername());

		WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
		password.clear();
		password.sendKeys(Config.getAdminPassword());

		WebElement btn = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-login-submit']")));
		btn.click();

		wait.until(ExpectedConditions.titleContains("Admin"));
	}

	private void openNotificationsPage() {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Thông báo')]")))
				.click();
		wait.until(ExpectedConditions.titleContains("Thông báo"));
	}

	@Test(description = "NT-01 Trang Thông báo hiển thị")
	public void case_NT_001() {
		loginAdmin();
		openNotificationsPage();
		Assert.assertEquals(driver.getTitle(), "Thông báo - BookingHub Admin");

	}

	@Test(description = "NT-02 Tiêu đề trang thông báo đúng")
	public void case_NT_002() {
		loginAdmin();
		openNotificationsPage();
		Assert.assertEquals(driver.getTitle(), "Thông báo - BookingHub Admin");

	}

	@Test(description = "NT-03 Ô tìm kiếm thông báo hiển thị")
	public void case_NT_003() {
		loginAdmin();
		openNotificationsPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".pl-10"))).click();
		Assert.assertFalse(driver.findElements(By.cssSelector(".pl-10")).isEmpty(), "Missing element");

	}

	@Test(description = "NT-04 Nút tải lại hiển thị")
	public void case_NT_004() {
		loginAdmin();
		openNotificationsPage();
		Assert.assertFalse(driver.findElements(By.cssSelector(".disabled\\3Aopacity-50")).isEmpty(), "Missing element");

	}

	@Test(description = "NT-05 Nút đánh dấu đã đọc hiển thị")
	public void case_NT_005() {
		loginAdmin();
		openNotificationsPage();
		Assert.assertFalse(driver.findElements(By.cssSelector(".bg-green-600")).isEmpty(), "Missing element");

	}

	@Test(description = "NT-06 Đánh dấu tất cả là đã đọc thông báo")
	public void case_NT_006() {
		loginAdmin();
		openNotificationsPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".bg-green-600"))).click();
		Assert.assertFalse(driver
				.findElements(By.xpath("//*[contains(normalize-space(.),\"Đã đánh dấu tất cả thông báo là đã đọc\")]"))
				.isEmpty());

	}

	@Test(description = "NT-07 Tải lại trang thông báo")
	public void case_NT_007() {
		loginAdmin();
		openNotificationsPage();
		By refreshBtn = By.cssSelector("[data-testid='admin-notifications-btn-refresh']");

		wait.until(ExpectedConditions.elementToBeClickable(refreshBtn)).click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Đã tải lại thông báo\")]")).isEmpty());

	}

}