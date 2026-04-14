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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import config.Config;

public class BookingPageTest {

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

	@Test(description = "BK-01 Trang đặt vé hiển thị và có tiêu đề")
	public void case_BK_001() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("isLoginNeeded", String
				.valueOf(((JavascriptExecutor) driver).executeScript("return !!document.querySelector('.btn-login')")));

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("usernameOrEmail"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).sendKeys("customer");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456aA@");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50"))).click();
		new Actions(driver)
				.moveToElement(wait
						.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".disabled\\3Aopacity-50"))))
				.perform();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("departureLocationId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("departureLocationId"))))
				.selectByVisibleText("Hà Nội - Long Biên");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("arrivalLocationId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("arrivalLocationId"))))
				.selectByVisibleText("Hải Phòng - Lê Chân");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("departureDate"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".react-datepicker__day--014"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[contains(.,'Đặt vé') or contains(.,'Chọn ghế') or contains(.,'Tiếp tục')]")))
				.click();
		Assert.assertTrue(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(.,'Đặt vé xe khách')]")))
						.getText().contains("Đặt vé xe khách"));

	}

	@Test(description = "BK-03 Khi ở trang booking có form đặt vé")
	public void case_BK_003() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("isLoginNeeded", String
				.valueOf(((JavascriptExecutor) driver).executeScript("return !!document.querySelector('.btn-login')")));

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("usernameOrEmail"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).sendKeys("customer");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456aA@");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50"))).click();
		new Actions(driver)
				.moveToElement(wait
						.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".disabled\\3Aopacity-50"))))
				.perform();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("departureLocationId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("departureLocationId"))))
				.selectByVisibleText("Hà Nội - Long Biên");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("arrivalLocationId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("arrivalLocationId"))))
				.selectByVisibleText("Hải Phòng - Lê Chân");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("departureDate"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".react-datepicker__day--014"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[contains(.,'Đặt vé') or contains(.,'Chọn ghế') or contains(.,'Tiếp tục')]")))
				.click();
		Assert.assertTrue(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(.,'Đặt vé xe khách')]")))
						.getText().contains("Đặt vé xe khách"));
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".mb-4 > .font-bold")))
				.getText().contains("Thông tin liên hệ"));

	}

	@Test(description = "BK-04 Form có hiển thị các trường thông tin")
	public void case_BK_004() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("isLoginNeeded", String
				.valueOf(((JavascriptExecutor) driver).executeScript("return !!document.querySelector('.btn-login')")));

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("usernameOrEmail"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).sendKeys("customer");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456aA@");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50"))).click();
		new Actions(driver)
				.moveToElement(wait
						.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".disabled\\3Aopacity-50"))))
				.perform();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("departureLocationId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("departureLocationId"))))
				.selectByVisibleText("Hà Nội - Long Biên");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("arrivalLocationId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("arrivalLocationId"))))
				.selectByVisibleText("Hải Phòng - Lê Chân");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("departureDate"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".react-datepicker__day--014"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[contains(.,'Đặt vé') or contains(.,'Chọn ghế') or contains(.,'Tiếp tục')]")))
				.click();
		Assert.assertTrue(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(.,'Đặt vé xe khách')]")))
						.getText().contains("Đặt vé xe khách"));
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".mb-4 > .font-bold")))
				.getText().contains("Thông tin liên hệ"));
		Assert.assertFalse(driver.findElements(By.name("customerName")).isEmpty(), "Missing element");
		Assert.assertFalse(driver.findElements(By.name("customerPhone")).isEmpty(), "Missing element");
		Assert.assertFalse(driver.findElements(By.name("customerEmail")).isEmpty(), "Missing element");
		Assert.assertFalse(driver.findElements(By.name("paymentMethod")).isEmpty(), "Missing element");
		Assert.assertFalse(driver.findElements(By.cssSelector(".px-6")).isEmpty(), "Missing element");

	}

	@Test(description = "BK-06 Gửi thông tin trống có thể hiện thông báo lỗi")
	public void case_BK_006() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("isLoginNeeded", String
				.valueOf(((JavascriptExecutor) driver).executeScript("return !!document.querySelector('.btn-login')")));

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("usernameOrEmail"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).sendKeys("customer");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456aA@");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50"))).click();
		new Actions(driver)
				.moveToElement(wait
						.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".disabled\\3Aopacity-50"))))
				.perform();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("departureLocationId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("departureLocationId"))))
				.selectByVisibleText("Hà Nội - Long Biên");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("arrivalLocationId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("arrivalLocationId"))))
				.selectByVisibleText("Hải Phòng - Lê Chân");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("departureDate"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".react-datepicker__day--014"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[contains(.,'Đặt vé') or contains(.,'Chọn ghế') or contains(.,'Tiếp tục')]")))
				.click();
		Assert.assertTrue(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(.,'Đặt vé xe khách')]")))
						.getText().contains("Đặt vé xe khách"));
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".mb-4 > .font-bold")))
				.getText().contains("Thông tin liên hệ"));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".py-6"))).click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector(".grid:nth-child(3) > .p-2:nth-child(3) > .text-lg"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("customerName"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("customerName"))).sendKeys("");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".px-6"))).click();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("customerName"))).isEnabled());

	}

	@Test(description = "BK-07 Điền họ tên và gửi thông tin vẫn báo bắt buộc nhập trường khác")
	public void case_BK_007() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("isLoginNeeded", String
				.valueOf(((JavascriptExecutor) driver).executeScript("return !!document.querySelector('.btn-login')")));

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("usernameOrEmail"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).sendKeys("customer");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456aA@");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50"))).click();
		new Actions(driver)
				.moveToElement(wait
						.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".disabled\\3Aopacity-50"))))
				.perform();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("departureLocationId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("departureLocationId"))))
				.selectByVisibleText("Hà Nội - Long Biên");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("arrivalLocationId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("arrivalLocationId"))))
				.selectByVisibleText("Hải Phòng - Lê Chân");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("departureDate"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".react-datepicker__day--014"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[contains(.,'Đặt vé') or contains(.,'Chọn ghế') or contains(.,'Tiếp tục')]")))
				.click();
		Assert.assertTrue(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(.,'Đặt vé xe khách')]")))
						.getText().contains("Đặt vé xe khách"));
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".mb-4 > .font-bold")))
				.getText().contains("Thông tin liên hệ"));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".grid:nth-child(2) > .p-2:nth-child(3)")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("html"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("customerPhone"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("customerPhone"))).sendKeys("");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".px-6"))).click();
		Assert.assertTrue(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.name("customerPhone"))).isEnabled());

	}

	@Test(description = "BK-08 Nút đặt vé hiển thị")
	public void case_BK_008() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("departureLocationId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("departureLocationId"))))
				.selectByVisibleText("Hà Nội - Long Biên");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("arrivalLocationId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("arrivalLocationId"))))
				.selectByVisibleText("Hải Phòng - Lê Chân");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("departureDate"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".react-datepicker__day--014"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50"))).click();
		new Actions(driver)
				.moveToElement(wait
						.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".disabled\\3Aopacity-50"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[contains(.,'Đặt vé') or contains(.,'Chọn ghế') or contains(.,'Tiếp tục')]")))
				.click();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".px-6"))).getText()
				.contains("Vui lòng chọn ghế"));

	}

	@Test(description = "BK-09 Đặt vé thành công")
	public void case_BK_009() {
		driver.get(Config.getBaseUrl() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("departureLocationId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("departureLocationId"))))
				.selectByVisibleText("Hà Nội - Long Biên");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("arrivalLocationId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("arrivalLocationId"))))
				.selectByVisibleText("Hải Phòng - Lê Chân");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("departureDate"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".react-datepicker__day--014"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50"))).click();
		new Actions(driver)
				.moveToElement(wait
						.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".disabled\\3Aopacity-50"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[contains(.,'Đặt vé') or contains(.,'Chọn ghế') or contains(.,'Tiếp tục')]")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".grid:nth-child(3) > .p-2:nth-child(3)")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".px-6"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".mb-4 > .text-2xl"))).click();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".mb-4 > .text-2xl")))
				.getText().contains("✅ Đặt vé thành công!"));

	}

}