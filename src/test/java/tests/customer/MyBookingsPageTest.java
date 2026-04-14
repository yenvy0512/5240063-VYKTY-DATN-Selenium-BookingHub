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

public class MyBookingsPageTest {

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

	@Test(description = "MB-01 Trang Vé của tôi hiển thị đúng")
	public void case_MB_001() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("isLoginNeeded", String
				.valueOf(((JavascriptExecutor) driver).executeScript("return !!document.querySelector('.btn-login')")));

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("usernameOrEmail"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).sendKeys("customer");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456aA@");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]")))
				.click();
		new Actions(driver)
				.moveToElement(wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]"))))
				.perform();
		new Actions(driver)
				.moveToElement(wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".flex > .text-3xl")))
				.getText().contains("Vé của tôi"));

	}

	@Test(description = "MB-02 Tiêu đề trang vé của tôi hiển thị đúng")
	public void case_MB_002() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("isLoginNeeded", String
				.valueOf(((JavascriptExecutor) driver).executeScript("return !!document.querySelector('.btn-login')")));

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("usernameOrEmail"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).sendKeys("customer");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456aA@");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]")))
				.click();
		new Actions(driver)
				.moveToElement(wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]"))))
				.perform();
		new Actions(driver)
				.moveToElement(wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
		Assert.assertEquals(driver.getTitle(), "Vé của tôi - BookingHub");

	}

	@Test(description = "MB-04 Heading Vé của tôi hiển thị")
	public void case_MB_004() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("isLoginNeeded", String
				.valueOf(((JavascriptExecutor) driver).executeScript("return !!document.querySelector('.btn-login')")));

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("usernameOrEmail"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).sendKeys("customer");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456aA@");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]")))
				.click();
		new Actions(driver)
				.moveToElement(wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]"))))
				.perform();
		new Actions(driver)
				.moveToElement(wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".flex > .text-3xl")))
				.getText().contains("Vé của tôi"));

	}

	@Test(description = "MB-05 Hiển thị trạng thái loading hoặc danh sách sau khi vào trang")
	public void case_MB_005() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("isLoginNeeded", String
				.valueOf(((JavascriptExecutor) driver).executeScript("return !!document.querySelector('.btn-login')")));

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("usernameOrEmail"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).sendKeys("customer");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456aA@");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]")))
				.click();
		new Actions(driver)
				.moveToElement(wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]"))))
				.perform();
		new Actions(driver)
				.moveToElement(wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
		Assert.assertFalse(
				driver.findElements(By.cssSelector(".bg-white:nth-child(1) > .p-6 > .flex > .flex-1")).isEmpty(),
				"Missing element");

	}

	@Test(description = "MB-06 Khi không có vé có nút Tìm chuyến xe")
	public void case_MB_006() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("isLoginNeeded", String
				.valueOf(((JavascriptExecutor) driver).executeScript("return !!document.querySelector('.btn-login')")));

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("usernameOrEmail"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).sendKeys("test");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]")))
				.click();
		new Actions(driver)
				.moveToElement(wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]"))))
				.perform();
		new Actions(driver)
				.moveToElement(wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".px-6"))).getText()
				.contains("Tìm chuyến xe"));

	}

	@Test(description = "MB-07 Hủy vé hiển thị trong vé nếu được phép hủy")
	public void case_MB_007() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("isLoginNeeded", String
				.valueOf(((JavascriptExecutor) driver).executeScript("return !!document.querySelector('.btn-login')")));

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("usernameOrEmail"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).sendKeys("customer");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456aA@");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]")))
				.click();
		new Actions(driver)
				.moveToElement(wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]"))))
				.perform();
		new Actions(driver)
				.moveToElement(wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("(//div[.//span[contains(.,'Chờ thanh toán')]]//button[contains(.,'Hủy')])[1]")));
		Assert.assertFalse(driver
				.findElements(By.xpath("(//div[.//span[contains(.,'Chờ thanh toán')]]//button[contains(.,'Hủy')])[1]"))
				.isEmpty(), "Missing element");

	}

	@Test(description = "MB-08 Hủy vé thành công nếu có nút hủy vé")
	public void case_MB_008() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("isLoginNeeded", String
				.valueOf(((JavascriptExecutor) driver).executeScript("return !!document.querySelector('.btn-login')")));

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("usernameOrEmail"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).sendKeys("customer");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456aA@");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]")))
				.click();
		new Actions(driver)
				.moveToElement(wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]"))))
				.perform();
		new Actions(driver)
				.moveToElement(wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("(//div[.//span[contains(.,'Chờ thanh toán')]]//button[contains(.,'Hủy')])[1]")));
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("(//div[.//span[contains(.,'Chờ thanh toán')]]//button[contains(.,'Hủy')])[1]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Xác nhận hủy')]"))).click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Hủy vé thành công!\")]")).isEmpty());

	}

	@Test(description = "MB-09 Nút xem chi tiết hiển thị")
	public void case_MB_009() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("isLoginNeeded", String
				.valueOf(((JavascriptExecutor) driver).executeScript("return !!document.querySelector('.btn-login')")));

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("usernameOrEmail"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).sendKeys("customer");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456aA@");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]")))
				.click();
		new Actions(driver)
				.moveToElement(wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]"))))
				.perform();
		new Actions(driver)
				.moveToElement(wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
		Assert.assertFalse(driver.findElements(By.cssSelector(".bg-white:nth-child(1) .w-full:nth-child(1)")).isEmpty(),
				"Missing element");
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Xem chi tiết\")]")).isEmpty());

	}

	@Test(description = "MB-10 Xem chi tiết của vé")
	public void case_MB_010() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("isLoginNeeded", String
				.valueOf(((JavascriptExecutor) driver).executeScript("return !!document.querySelector('.btn-login')")));

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("usernameOrEmail"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).sendKeys("customer");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456aA@");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]")))
				.click();
		new Actions(driver)
				.moveToElement(wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]"))))
				.perform();
		new Actions(driver)
				.moveToElement(wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
		Assert.assertFalse(driver.findElements(By.cssSelector(".bg-white:nth-child(1) .w-full:nth-child(1)")).isEmpty(),
				"Missing element");
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Xem chi tiết\")]")).isEmpty());
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector(".bg-white:nth-child(1) .w-full:nth-child(1)")))
				.click();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".sticky > .text-2xl")))
				.getText().contains("Chi tiết vé"));

	}

	@Test(description = "MB-11 Hiển thị nút Xem QR Code")
	public void case_MB_011() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("isLoginNeeded", String
				.valueOf(((JavascriptExecutor) driver).executeScript("return !!document.querySelector('.btn-login')")));

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("usernameOrEmail"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).sendKeys("customer");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456aA@");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]")))
				.click();
		new Actions(driver)
				.moveToElement(wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]"))))
				.perform();
		new Actions(driver)
				.moveToElement(wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
		Assert.assertFalse(driver
				.findElements(By.cssSelector(".bg-white:nth-child(1) .flex > .flex > .flex > .w-full:nth-child(2)"))
				.isEmpty(), "Missing element");
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Xem QR code\")]")).isEmpty());

	}

	@Test(description = "MB-12 Xem QR của vé")
	public void case_MB_012() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("isLoginNeeded", String
				.valueOf(((JavascriptExecutor) driver).executeScript("return !!document.querySelector('.btn-login')")));

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("usernameOrEmail"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).sendKeys("customer");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456aA@");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]")))
				.click();
		new Actions(driver)
				.moveToElement(wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]"))))
				.perform();
		new Actions(driver)
				.moveToElement(wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
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
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("isLoginNeeded", String
				.valueOf(((JavascriptExecutor) driver).executeScript("return !!document.querySelector('.btn-login')")));

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("usernameOrEmail"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).sendKeys("customer");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456aA@");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]")))
				.click();
		new Actions(driver)
				.moveToElement(wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]"))))
				.perform();
		new Actions(driver)
				.moveToElement(wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("(//div[.//span[contains(.,'Chờ thanh toán')]]//button[contains(.,'Thanh toán')])[1]")));
		Assert.assertFalse(driver
				.findElements(
						By.xpath("(//div[.//span[contains(.,'Chờ thanh toán')]]//button[contains(.,'Thanh toán')])[1]"))
				.isEmpty(), "Missing element");

	}

	@Test(description = "MB-14 Ấn nút thanh toán hiển thị trang thanh toán")
	public void case_MB_014() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("isLoginNeeded", String
				.valueOf(((JavascriptExecutor) driver).executeScript("return !!document.querySelector('.btn-login')")));

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("usernameOrEmail"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).sendKeys("customer");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456aA@");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]")))
				.click();
		new Actions(driver)
				.moveToElement(wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]"))))
				.perform();
		new Actions(driver)
				.moveToElement(wait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thanh toán')]"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("(//div[.//span[contains(.,'Chờ thanh toán')]]//button[contains(.,'Thanh toán')])[1]")));
		Assert.assertFalse(driver
				.findElements(
						By.xpath("(//div[.//span[contains(.,'Chờ thanh toán')]]//button[contains(.,'Thanh toán')])[1]"))
				.isEmpty(), "Missing element");
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("(//div[.//span[contains(.,'Chờ thanh toán')]]//button[contains(.,'Thanh toán')])[1]")))
				.click();

	}

}