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

public class PaymentPageTest {

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

	@Test(description = "PM-01 Trang thanh toán hiển thị")
	public void case_PM_001() {
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
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".text-3xl"))).getText()
				.contains("Thanh toán"));
		
	}

	@Test(description = "PM-02 Tiêu đề trang chứa Thanh toán")
	public void case_PM_002() {
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
		Assert.assertEquals(driver.getTitle(), "Thanh toán - BookingHub");
		
	}

	@Test(description = "PM-03 Hiển thị heading Thanh toán")
	public void case_PM_003() {
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
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".text-3xl"))).getText()
				.contains("Thanh toán"));
		
	}

	@Test(description = "PM-07 Hiển thị phương thức thanh toán")
	public void case_PM_007() {
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
		Assert.assertFalse(driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Phương thức thanh toán\")]"))
				.isEmpty());
		
	}

	@Test(description = "PM-08 Hiển thị thông tin vé")
	public void case_PM_008() {
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
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".mb-6 > .text-xl")))
				.getText().contains("Thông tin vé"));
		
	}

	@Test(description = "PM-09 Hiển thị nút Xác nhận thanh toán")
	public void case_PM_009() {
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
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".py-3"))).getText()
				.contains("Xác nhận thanh toán"));
		
	}

	@Test(description = "PM-10 Thanh toán bằng phương thức tiền mặt và Xác nhận thanh toán thành công")
	public void case_PM_010() {
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
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//button[contains(normalize-space(.),'Xác nhận thanh toán')]")));
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[contains(normalize-space(.),'Xác nhận thanh toán')]")))
				.click();
		Assert.assertFalse(driver
				.findElements(By.xpath(
						"//*[contains(normalize-space(.),\"Đặt vé thành công! Vui lòng thanh toán khi lên xe\")]"))
				.isEmpty());
		
	}

	@Test(description = "PM-11 Thanh toán bằng momo chuyển sang trang thanh toán của momo")
	public void case_PM_011() {
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
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".p-4:nth-child(2) .text-sm"))).click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[contains(normalize-space(.),'Xác nhận thanh toán')]")))
				.click();
		new Actions(driver)
				.moveToElement(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".py-3"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
		Assert.assertEquals(driver.getTitle(), "Cổng thanh toán MoMo");
		
	}

	@Test(description = "PM-12 Thanh toán bằng phương thức vnpay chuyển sang trang thanh toán vnpay")
	public void case_PM_012() {
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
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".p-4:nth-child(3)"))).click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[contains(normalize-space(.),'Xác nhận thanh toán')]")))
				.click();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".h3"))).getText()
				.contains("hotrovnpay@vnpay.vn"));
		
	}

}