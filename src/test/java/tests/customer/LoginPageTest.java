package tests.customer;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
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

public class LoginPageTest {

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

	private void logoutBefore() {
		boolean isLoggedIn = !driver.findElements(By.cssSelector(".user-avatar")).isEmpty();

		if (isLoggedIn) {
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".user-avatar"))).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(.,'Đăng xuất')]"))).click();

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='confirm-modal']")));

			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']")))
					.click();

			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn-login")));
		}
	}

	@Test(description = "LG-01 Trang login hiển thị form đăng nhập")
	public void case_LG_001() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));

		logoutBefore();

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-login"))).click();
		Assert.assertFalse(driver.findElements(By.cssSelector(".text-3xl")).isEmpty(), "Missing element");

	}

	@Test(description = "LG-02 Tiêu đề trang login đúng")
	public void case_LG_002() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		logoutBefore();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-login"))).click();
		WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".text-3xl")));

		wait.until(ExpectedConditions.textToBePresentInElement(title, "Đăng nhập"));

		Assert.assertTrue(title.getText().contains("Đăng nhập"));

	}

	@Test(description = "LG-04 Ô mật khẩu có type password")
	public void case_LG_004() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		logoutBefore();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-login"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
		Assert.assertFalse(driver.findElements(By.cssSelector("#password[type='password']")).isEmpty(),
				"Missing element");

	}

	@Test(description = "LG-06 Đăng nhập sai vẫn thông báo lỗi")
	public void case_LG_006() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		logoutBefore();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-login"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("usernameOrEmail"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).sendKeys("customer");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("avcs");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='login-submit']"))).click();
		Assert.assertFalse(driver
				.findElements(By.xpath("//*[contains(normalize-space(.),\"Sai tài khoản hoặc mật khẩu\")]")).isEmpty());

	}

	@Test(description = "LG-07 Form có ô tài khoàn và Mật khẩu")
	public void case_LG_007() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		logoutBefore();

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-login"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
		Assert.assertFalse(driver.findElements(By.id("usernameOrEmail")).isEmpty(), "Missing element");
		Assert.assertFalse(driver.findElements(By.id("password")).isEmpty(), "Missing element");

	}

	@Test(description = "LG-08 Có nút Đăng ký")
	public void case_LG_008() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		logoutBefore();

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-login"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Đăng ký ngay")));
		Assert.assertFalse(driver.findElements(By.linkText("Đăng ký ngay")).isEmpty(), "Missing element");

	}

	@Test(description = "LG-09 Ấn Đăng ký chuyển sang trang đăng ký")
	public void case_LG_009() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		logoutBefore();

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-login"))).click();

		WebElement registerBtn = wait.until(ExpectedConditions.elementToBeClickable(
		    By.linkText("Đăng ký ngay")
		));

		Assert.assertTrue(registerBtn.isDisplayed(), "Missing element");

		registerBtn.click();

		wait.until(ExpectedConditions.textToBePresentInElementLocated(
		    By.cssSelector(".text-3xl"),
		    "Đăng ký"
		));

		String title = driver.findElement(By.cssSelector(".text-3xl")).getText();
		Assert.assertTrue(title.contains("Đăng ký"), "Không vào trang đăng ký");

	}

	@Test(description = "LG-10 Gửi thông tin form trống vẫn ở trang login và báo bắt buộc nhập")
	public void case_LG_010() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		logoutBefore();

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-login"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
		Assert.assertFalse(driver.findElements(By.id("usernameOrEmail")).isEmpty(), "Missing element");
		Assert.assertFalse(driver.findElements(By.id("password")).isEmpty(), "Missing element");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='login-submit']"))).click();
		WebElement username = driver.findElement(By.id("usernameOrEmail"));

		Boolean isValid = (Boolean) ((JavascriptExecutor) driver)
		    .executeScript("return arguments[0].checkValidity();", username);

		Assert.assertFalse(isValid);

	}

	@Test(description = "LG-11 Đăng nhập đúng tài khoản rời khỏi trang login")
	public void case_LG_011() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		logoutBefore();

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-login"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).sendKeys("customer");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456aA@");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='login-submit']"))).click();
		WebElement toast = wait.until(ExpectedConditions.presenceOfElementLocated(
			    By.xpath("//*[contains(normalize-space(.),'Đăng nhập thành công!')]")
			));

		Assert.assertTrue(toast.getText().contains("Đăng nhập thành công!"));
	}

}