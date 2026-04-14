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

public class RegisterPageTest {

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

	private void goToRegisterPage() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		logoutBefore();

		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[contains(@href,'/register') or contains(.,'Đăng ký')]"))).click();

		wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".text-3xl"), "Đăng ký"));
	}

	private void waitForToast(String message) {
		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(d -> d.findElement(By.tagName("body")).getText().contains(message));
	}

	@Test(description = "RG-01 Trang đăng ký hiển thị form")
	public void case_RG_001() {
		goToRegisterPage();

		WebElement form = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-8")));

		Assert.assertTrue(form.isDisplayed());

	}

	@Test(description = "RG-02 Tiêu đề trang đăng ký đúng")
	public void case_RG_002() {
		goToRegisterPage();

		wait.until(ExpectedConditions.titleIs("Đăng ký - BookingHub"));
		Assert.assertEquals(driver.getTitle(), "Đăng ký - BookingHub");

	}

	@Test(description = "RG-04 Trang đăng ký hiển thị đủ thông tin")
	public void case_RG_004() {
		goToRegisterPage();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone")));

		Assert.assertTrue(true);

	}

	@Test(description = "RG-05 Ô mật khẩu có type password")
	public void case_RG_005() {
		goToRegisterPage();

		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));

		Assert.assertEquals(password.getAttribute("type"), "password");

	}

	@Test(description = "RG-06 Nút Đăng ký hiển thị")
	public void case_RG_006() {
		goToRegisterPage();

		WebElement btn = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='register-submit']")));

		Assert.assertTrue(btn.getText().contains("Đăng ký"));

	}

	@Test(description = "RG-07 Gửi thông tin trống form vẫn hiển thị và thông báo nhập các trường bắt buộc")
	public void case_RG_007() {
		goToRegisterPage();

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='register-submit']"))).click();

		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));

		Boolean isValid = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].checkValidity();",
				username);

		Assert.assertFalse(isValid);

	}

	@Test(description = "RG-08 Nhập username rồi gửi thông tin vẫn ở form thông báo nhập các trường bắt buộc")
	public void case_RG_008() {
		goToRegisterPage();

		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
		username.sendKeys("test");

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='register-submit']"))).click();

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));

		Boolean isValid = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].checkValidity();",
				email);

		Assert.assertFalse(isValid);

	}

	@Test(description = "RG-09 Điền email không hợp lệ báo lỗi")
	public void case_RG_009() {
		goToRegisterPage();

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		email.clear();
		email.sendKeys("a");

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='register-submit']"))).click();

		Boolean isValid = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].checkValidity();",
				email);

		Assert.assertFalse(isValid);
	}

	@Test(description = "RG-10 Kiểm tra trùng username nếu đã tồn tại")
	public void case_RG_010() {
		goToRegisterPage();

		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
		username.clear();
		username.sendKeys("teest");

		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
		password.clear();
		password.sendKeys("123456");

		WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
		name.clear();
		name.sendKeys("Nguyễn Văn B");

		WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone")));
		phone.clear();
		phone.sendKeys("0333333333");

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		email.clear();
		email.sendKeys("a@gmail.com");

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='register-submit']"))).click();

		waitForToast("Username đã tồn tại");
	}

	@Test(description = "RG-11 Kiểm tra trùng Email nếu đã tồn tại")
	public void case_RG_011() {
		goToRegisterPage();

		WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
		username.clear();
		username.sendKeys("teest1");

		WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
		password.clear();
		password.sendKeys("123456");

		WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
		name.clear();
		name.sendKeys("Nguyễn Văn B");

		WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone")));
		phone.clear();
		phone.sendKeys("0333333333");

		WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		email.clear();
		email.sendKeys("a@gmail.com");

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='register-submit']"))).click();

		waitForToast("Email đã tồn tại");

	}

	@Test(description = "RG-12 Kiểm tra trùng SĐT nếu đã tồn tại")
	public void case_RG_012() {
		goToRegisterPage();

	    WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
	    username.clear();
	    username.sendKeys("teest1111");

	    WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
	    password.clear();
	    password.sendKeys("123456");

	    WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
	    name.clear();
	    name.sendKeys("Nguyễn Văn B");

	    WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone")));
	    phone.clear();
	    phone.sendKeys("0333333333");

	    WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
	    email.clear();
	    email.sendKeys("abdc@gmail.com");

	    wait.until(ExpectedConditions.elementToBeClickable(
	        By.cssSelector("[data-testid='register-submit']")
	    )).click();

	    waitForToast("Số điện thoại này đã được liên kết với tài khoản khác");

	}

	@Test(description = "RG-13 Nhập đúng thông tin đăng ký thành công")
	public void case_RG_013() {
		goToRegisterPage();

	    WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
	    username.clear();
	    username.sendKeys("teest11111");

	    WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
	    password.clear();
	    password.sendKeys("123456");

	    WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
	    name.clear();
	    name.sendKeys("Nguyễn Văn B");

	    WebElement phone = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone")));
	    phone.clear();
	    phone.sendKeys("0333333334");

	    WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
	    email.clear();
	    email.sendKeys("abdcd@gmail.com");

	    wait.until(ExpectedConditions.elementToBeClickable(
	        By.cssSelector("[data-testid='register-submit']")
	    )).click();

	    waitForToast("Đăng ký thành công!");

	}

}