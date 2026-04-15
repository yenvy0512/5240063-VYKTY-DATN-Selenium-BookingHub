package tests.admin;

import java.time.Duration;
import java.util.List;

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

public class AdminLoginPageTest {

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

	private void logoutAdmin() {
		List<WebElement> logoutBtn = driver.findElements(By.cssSelector("[data-testid='admin-header-logout']"));

		if (!logoutBtn.isEmpty()) {
			wait.until(ExpectedConditions.elementToBeClickable(logoutBtn.get(0))).click();

			WebElement confirm = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//button[contains(normalize-space(.),'Đăng xuất')]")));
			confirm.click();
		}
	}

	private void openAdmin() {
		driver.get(Config.getBaseUrlAdmin());
	}

	@Test(description = "AL-01 Trang admin login hiển thị form đăng nhập")
	public void case_AL_001() {
		openAdmin();
		logoutAdmin();

		Assert.assertTrue(driver.findElement(By.id("admin-usernameOrEmail")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("admin-password")).isDisplayed());
	}

	@Test(description = "AL-02 Tiêu đề trang đăng nhập hiển thỉ đúng")
	public void case_AL_002() {
		openAdmin();
		logoutAdmin();

		Assert.assertTrue(wait.until(ExpectedConditions.titleIs("Đăng nhập - Admin Portal")));
	}

	@Test(description = "AL-03 Đăng nhập sai báo lỗi sai thông tin")
	public void case_AL_003() {
		openAdmin();
		logoutAdmin();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail"))).sendKeys("wrong");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-password"))).sendKeys("wrong");

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-login-submit']")))
				.click();

		Assert.assertFalse(driver
				.findElements(By.xpath("//*[contains(normalize-space(.),'Sai tài khoản hoặc mật khẩu')]")).isEmpty());

	}

	@Test(description = "AL-04 Đăng nhập đúng thông báo đăng nhập thành công")
	public void case_AL_004() {
		openAdmin();
		logoutAdmin();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail"))).sendKeys("admin");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-password"))).sendKeys("123456aA@");

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-login-submit']")))
				.click();

		Assert.assertTrue(wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Đăng nhập thành công')]")))
				.isDisplayed());
	}

}