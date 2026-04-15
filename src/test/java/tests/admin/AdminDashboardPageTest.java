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

public class AdminDashboardPageTest {

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

	public void loginAdmin() {
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

	@Test(description = "AD-01 Dashboard hiển thị sau khi đăng nhập")
	public void case_AD_001() {
		loginAdmin();

		wait.until(ExpectedConditions.titleIs("Dashboard - BookingHub Admin"));
		Assert.assertEquals(driver.getTitle(), "Dashboard - BookingHub Admin");

	}

	@Test(description = "AD-02 Tiêu đề Dashboard đúng")
	public void case_AD_002() {
		loginAdmin();

		Assert.assertEquals(driver.getTitle(), "Dashboard - BookingHub Admin");

		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Dashboard')]")))
				.isDisplayed());

	}

	@Test(description = "AD-03 Kiểm tra hiển thị thông tin Tổng quan")
	public void case_AD_003() {
		loginAdmin();

		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Tổng quan hệ thống')]")))
				.isDisplayed());

	}

	@Test(description = "AD-04 Kiểm tra hiển thị thông tin Thống kê đặt vé")
	public void case_AD_004() {
		loginAdmin();

		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thống kê đặt vé')]")))
				.isDisplayed());

	}

}