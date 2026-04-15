package tests.customer;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
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

public class MyBookingsPageTest {

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

	private void loginCustomer() {
		driver.get(Config.getBaseUrl() + "/");

		List<WebElement> loginBtn = driver.findElements(By.cssSelector(".btn-login"));

		if (!loginBtn.isEmpty()) {

			loginBtn.get(0).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();

			WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.id("usernameOrEmail")));
			username.clear();
			username.sendKeys(Config.getCustomerUsername());

			WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
			password.clear();
			password.sendKeys(Config.getCustomerPassword());

			WebElement submitBtn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Đăng nhập')]")));
			submitBtn.click();

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".btn-login")));

		}
	}

	private void openMyBookingsPage() {
		WebElement myBooking = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/my-bookings']")));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", myBooking);

		wait.until(ExpectedConditions.urlContains("/my-bookings"));
	}

	private void waitForToast(String message) {
		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(d -> d.findElement(By.tagName("body")).getText().contains(message));
	}

	@Test(description = "MB-01 Trang Vé của tôi hiển thị đúng")
	public void case_MB_001() {
		loginCustomer();
		openMyBookingsPage();

		WebElement heading = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='customer-my-bookings-heading']")));

		Assert.assertTrue(heading.getText().contains("Vé của tôi"));
	}

	@Test(description = "MB-02 Tiêu đề trang vé của tôi hiển thị đúng")
	public void case_MB_002() {
		loginCustomer();
		openMyBookingsPage();

		wait.until(ExpectedConditions.titleIs("Vé của tôi - BookingHub"));
		Assert.assertEquals(driver.getTitle(), "Vé của tôi - BookingHub");
	}

	@Test(description = "MB-04 Heading Vé của tôi hiển thị")
	public void case_MB_004() {
		loginCustomer();
		openMyBookingsPage();

		WebElement heading = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".flex > .text-3xl")));
		Assert.assertTrue(heading.getText().contains("Vé của tôi"));
	}

	@Test(description = "MB-05 Hiển thị trạng thái loading hoặc danh sách sau khi vào trang")
	public void case_MB_005() {
		loginCustomer();
		openMyBookingsPage();

		Assert.assertFalse(
				driver.findElements(By.cssSelector(".bg-white:nth-child(1) > .p-6 > .flex > .flex-1")).isEmpty(),
				"Missing element");
	}

	@Test(description = "MB-06 Khi không có vé có nút Tìm chuyến xe")
	public void case_MB_006() {
		loginCustomer();
		openMyBookingsPage();

		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".px-6"))).getText()
				.contains("Tìm chuyến xe"));
	}

	@Test(description = "MB-07 Hủy vé hiển thị trong vé nếu được phép hủy")
	public void case_MB_007() {
		loginCustomer();
		openMyBookingsPage();

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("(//div[.//span[contains(.,'Chờ thanh toán')]]//button[contains(.,'Hủy')])[1]")));
		Assert.assertFalse(driver
				.findElements(By.xpath("(//div[.//span[contains(.,'Chờ thanh toán')]]//button[contains(.,'Hủy')])[1]"))
				.isEmpty(), "Missing element");
	}

	@Test(description = "MB-08 Hủy vé thành công nếu có nút hủy vé")
	public void case_MB_008() {
		loginCustomer();
		openMyBookingsPage();

		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("(//div[.//span[contains(.,'Chờ thanh toán')]]//button[contains(.,'Hủy')])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Xác nhận hủy')]"))).click();
		waitForToast("Hủy vé thành công!");
	}

	@Test(description = "MB-09 Nút xem chi tiết hiển thị")
	public void case_MB_009() {
		loginCustomer();
		openMyBookingsPage();

		Assert.assertFalse(driver.findElements(By.cssSelector(".bg-white:nth-child(1) .w-full:nth-child(1)")).isEmpty(),
				"Missing element");
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Xem chi tiết\")]")).isEmpty());
	}

	@Test(description = "MB-10 Xem chi tiết của vé")
	public void case_MB_010() {
		loginCustomer();
		openMyBookingsPage();

		Assert.assertFalse(driver.findElements(By.cssSelector(".bg-white:nth-child(1) .w-full:nth-child(1)")).isEmpty(),
				"Missing element");
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Xem chi tiết\")]")).isEmpty());
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".sticky > .text-2xl")))
				.getText().contains("Chi tiết vé"));
	}

	@Test(description = "MB-11 Hiển thị nút Xem QR Code")
	public void case_MB_011() {
		loginCustomer();
		openMyBookingsPage();

		Assert.assertFalse(driver
				.findElements(By.cssSelector(".bg-white:nth-child(1) .flex > .flex > .flex > .w-full:nth-child(2)"))
				.isEmpty(), "Missing element");
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Xem QR code\")]")).isEmpty());
	}

	@Test(description = "MB-12 Xem QR của vé")
	public void case_MB_012() {
		loginCustomer();
		openMyBookingsPage();

		Assert.assertFalse(driver
				.findElements(By.cssSelector(".bg-white:nth-child(1) .flex > .flex > .flex > .w-full:nth-child(2)"))
				.isEmpty(), "Missing element");
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Xem QR code\")]")).isEmpty());
		wait.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector(".bg-white:nth-child(1) .flex > .flex > .flex > .w-full:nth-child(2)"))).click();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".text-xl"))).getText()
				.contains("Mã QR check-in"));
	}

	@Test(description = "MB-13 Nút thanh toán hiển thị nếu trạng thái vé là chờ thanh toán")
	public void case_MB_013() {
		loginCustomer();
		openMyBookingsPage();

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("(//div[.//span[contains(.,'Chờ thanh toán')]]//button[contains(.,'Thanh toán')])[1]")));
		Assert.assertFalse(driver
				.findElements(
						By.xpath("(//div[.//span[contains(.,'Chờ thanh toán')]]//button[contains(.,'Thanh toán')])[1]"))
				.isEmpty(), "Missing element");
	}

	@Test(description = "MB-14 Ấn nút thanh toán hiển thị trang thanh toán")
	public void case_MB_014() {
		loginCustomer();
		openMyBookingsPage();

		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("(//div[.//span[contains(.,'Chờ thanh toán')]]//button[contains(.,'Thanh toán')])[1]")));
		Assert.assertFalse(driver
				.findElements(
						By.xpath("(//div[.//span[contains(.,'Chờ thanh toán')]]//button[contains(.,'Thanh toán')])[1]"))
				.isEmpty(), "Missing element");
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("(//div[.//span[contains(.,'Chờ thanh toán')]]//button[contains(.,'Thanh toán')])[1]")))
				.click();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='customer-payment-heading']")));
	}

}
