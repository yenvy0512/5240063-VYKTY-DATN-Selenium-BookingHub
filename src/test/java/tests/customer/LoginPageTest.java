package tests.customer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import config.Config;

public class LoginPageTest {

	private WebDriver driver;
	private WebDriverWait wait;
	private final Map<String, String> vars = new HashMap<>();

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

	@Test(description = "LG-01 Trang login hiển thị form đăng nhập")
	public void case_LG_001() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("isLoginNeeded", String
				.valueOf(((JavascriptExecutor) driver).executeScript("return !!document.querySelector('.btn-login')")));

		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[contains(@href,'/register') or contains(.,'Đăng ký')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-cancel']")))
				.click();
		new Actions(driver).moveToElement(wait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='confirm-modal-cancel']"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".bg-red-600"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
		Assert.assertFalse(driver.findElements(By.cssSelector(".text-3xl")).isEmpty(), "Missing element");

	}

	@Test(description = "LG-02 Tiêu đề trang login đúng")
	public void case_LG_002() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("isLoginNeeded", String
				.valueOf(((JavascriptExecutor) driver).executeScript("return !!document.querySelector('.btn-login')")));

		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[contains(@href,'/register') or contains(.,'Đăng ký')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-cancel']")))
				.click();
		new Actions(driver).moveToElement(wait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='confirm-modal-cancel']"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".bg-red-600"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".text-3xl"))).getText()
				.contains("Đăng nhập"));

	}

	@Test(description = "LG-04 Ô mật khẩu có type password")
	public void case_LG_004() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("isLoginNeeded", String
				.valueOf(((JavascriptExecutor) driver).executeScript("return !!document.querySelector('.btn-login')")));

		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[contains(@href,'/register') or contains(.,'Đăng ký')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-cancel']")))
				.click();
		new Actions(driver).moveToElement(wait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='confirm-modal-cancel']"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".bg-red-600"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
		Assert.assertFalse(driver.findElements(By.cssSelector("#password[type='password']")).isEmpty(),
				"Missing element");

	}

	@Test(description = "LG-06 Đăng nhập sai vẫn thông báo lỗi")
	public void case_LG_006() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("isLoginNeeded", String
				.valueOf(((JavascriptExecutor) driver).executeScript("return !!document.querySelector('.btn-login')")));

		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[contains(@href,'/register') or contains(.,'Đăng ký')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-cancel']")))
				.click();
		new Actions(driver).moveToElement(wait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='confirm-modal-cancel']"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".bg-red-600"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
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
		vars.put("isLoginNeeded", String
				.valueOf(((JavascriptExecutor) driver).executeScript("return !!document.querySelector('.btn-login')")));

		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[contains(@href,'/register') or contains(.,'Đăng ký')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-cancel']")))
				.click();
		new Actions(driver).moveToElement(wait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='confirm-modal-cancel']"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".bg-red-600"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".text-3xl"))).getText()
				.contains("Đăng nhập"));
		Assert.assertFalse(driver.findElements(By.id("usernameOrEmail")).isEmpty(), "Missing element");
		Assert.assertFalse(driver.findElements(By.id("password")).isEmpty(), "Missing element");

	}

	@Test(description = "LG-08 Có nút Đăng ký")
	public void case_LG_008() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("isLoginNeeded", String
				.valueOf(((JavascriptExecutor) driver).executeScript("return !!document.querySelector('.btn-login')")));

		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[contains(@href,'/register') or contains(.,'Đăng ký')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-cancel']")))
				.click();
		new Actions(driver).moveToElement(wait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='confirm-modal-cancel']"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".bg-red-600"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".text-3xl"))).getText()
				.contains("Đăng nhập"));
		Assert.assertFalse(driver.findElements(By.linkText("Đăng ký ngay")).isEmpty(), "Missing element");

	}

	@Test(description = "LG-09 Ấn Đăng ký chuyển sang trang đăng ký")
	public void case_LG_009() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("isLoginNeeded", String
				.valueOf(((JavascriptExecutor) driver).executeScript("return !!document.querySelector('.btn-login')")));

		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[contains(@href,'/register') or contains(.,'Đăng ký')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-cancel']")))
				.click();
		new Actions(driver).moveToElement(wait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='confirm-modal-cancel']"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".bg-red-600"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".text-3xl"))).getText()
				.contains("Đăng nhập"));
		Assert.assertFalse(driver.findElements(By.linkText("Đăng ký ngay")).isEmpty(), "Missing element");
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng ký ngay"))).click();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".text-3xl"))).getText()
				.contains("Đăng ký"));

	}

	@Test(description = "LG-10 Gửi thông tin form trống vẫn ở trang login và báo bắt buộc nhập")
	public void case_LG_010() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("isLoginNeeded", String
				.valueOf(((JavascriptExecutor) driver).executeScript("return !!document.querySelector('.btn-login')")));

		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[contains(@href,'/register') or contains(.,'Đăng ký')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-cancel']")))
				.click();
		new Actions(driver).moveToElement(wait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='confirm-modal-cancel']"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".bg-red-600"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".text-3xl"))).getText()
				.contains("Đăng nhập"));
		Assert.assertFalse(driver.findElements(By.id("usernameOrEmail")).isEmpty(), "Missing element");
		Assert.assertFalse(driver.findElements(By.id("password")).isEmpty(), "Missing element");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='login-submit']"))).click();
		Assert.assertTrue(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id("usernameOrEmail"))).isEnabled());

	}

	@Test(description = "LG-11 Đăng nhập đúng tài khoản rời khỏi trang login")
	public void case_LG_011() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("isLoginNeeded", String
				.valueOf(((JavascriptExecutor) driver).executeScript("return !!document.querySelector('.btn-login')")));

		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[contains(@href,'/register') or contains(.,'Đăng ký')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-cancel']")))
				.click();
		new Actions(driver).moveToElement(wait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='confirm-modal-cancel']"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".bg-red-600"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".text-3xl"))).getText()
				.contains("Đăng nhập"));
		Assert.assertFalse(driver.findElements(By.id("usernameOrEmail")).isEmpty(), "Missing element");
		Assert.assertFalse(driver.findElements(By.id("password")).isEmpty(), "Missing element");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("usernameOrEmail"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).sendKeys("customer");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456aA@");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='login-submit']"))).click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Đăng nhập thành công!\")]")).isEmpty());

	}

}