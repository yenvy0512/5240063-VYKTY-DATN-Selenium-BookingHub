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

public class RegisterPageTest {

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

	@Test(description = "RG-01 Trang đăng ký hiển thị form")
	public void case_RG_001() {
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
		
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng ký"))).click();
		Assert.assertFalse(driver.findElements(By.cssSelector(".mb-8")).isEmpty(), "Missing element");
		
	}

	@Test(description = "RG-02 Tiêu đề trang đăng ký đúng")
	public void case_RG_002() {
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
		
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng ký"))).click();
		Assert.assertEquals(driver.getTitle(), "Đăng ký - BookingHub");
		
	}

	@Test(description = "RG-04 Trang đăng ký hiển thị đủ thông tin")
	public void case_RG_004() {
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
		
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng ký"))).click();
		Assert.assertFalse(driver.findElements(By.id("username")).isEmpty(), "Missing element");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("email"))).click();
		Assert.assertFalse(driver.findElements(By.id("email")).isEmpty(), "Missing element");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).click();
		Assert.assertFalse(driver.findElements(By.id("password")).isEmpty(), "Missing element");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).click();
		Assert.assertFalse(driver.findElements(By.id("name")).isEmpty(), "Missing element");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("phone"))).click();
		Assert.assertFalse(driver.findElements(By.id("phone")).isEmpty(), "Missing element");
		
	}

	@Test(description = "RG-05 Ô mật khẩu có type password")
	public void case_RG_005() {
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
		
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng ký"))).click();
		Assert.assertFalse(driver.findElements(By.cssSelector("#password[type='password']")).isEmpty(),
				"Missing element");
		
	}

	@Test(description = "RG-06 Nút Đăng ký hiển thị")
	public void case_RG_006() {
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
		
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng ký"))).click();
		Assert.assertTrue(wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='register-submit']")))
				.getText().contains("Đăng ký"));
		
	}

	@Test(description = "RG-07 Gửi thông tin trống form vẫn hiển thị và thông báo nhập các trường bắt buộc")
	public void case_RG_007() {
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
		
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng ký"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='register-submit']"))).click();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username"))).isEnabled());
		
	}

	@Test(description = "RG-08 Nhập username rồi gửi thông tin vẫn ở form thông báo nhập các trường bắt buộc")
	public void case_RG_008() {
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
		
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng ký"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("username"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("test");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='register-submit']"))).click();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email"))).isEnabled());
		
	}

	@Test(description = "RG-09 Điền email không hợp lệ báo lỗi")
	public void case_RG_009() {
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
		
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng ký"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("username"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("teest");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys("Nguyễn Văn B");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("phone"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone"))).sendKeys("0333333333");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("email"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys("a");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".container"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='register-submit']"))).click();
		Assert.assertFalse(driver.findElements(By.cssSelector("#email:invalid")).isEmpty(), "Missing element");
		
	}

	@Test(description = "RG-10 Kiểm tra trùng username nếu đã tồn tại")
	public void case_RG_010() {
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
		
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng ký"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("username"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("teest");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys("Nguyễn Văn B");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("phone"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone"))).sendKeys("0333333333");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("email"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys("a@gmail.com");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='register-submit']"))).click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Username đã tồn tại\")]")).isEmpty());
		
	}

	@Test(description = "RG-11 Kiểm tra trùng Email nếu đã tồn tại")
	public void case_RG_011() {
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
		
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng ký"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("username"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("teest1");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys("Nguyễn Văn B");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("phone"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone"))).sendKeys("0333333333");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("email"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys("a@gmail.com");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='register-submit']"))).click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Email đã tồn tại\")]")).isEmpty());
		
	}

	@Test(description = "RG-12 Kiểm tra trùng SĐT nếu đã tồn tại")
	public void case_RG_012() {
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
		
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng ký"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("username"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("teest1111");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys("Nguyễn Văn B");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("phone"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone"))).sendKeys("0333333333");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("email"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys("abdc@gmail.com");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='register-submit']"))).click();
		Assert.assertFalse(driver
				.findElements(By.xpath(
						"//*[contains(normalize-space(.),\"Số điện thoại này đã được liên kết với tài khoản khác\")]"))
				.isEmpty());
		
	}

	@Test(description = "RG-13 Nhập đúng thông tin đăng ký thành công")
	public void case_RG_013() {
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
		
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng ký"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("username"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys("teest11111");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("name"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name"))).sendKeys("Nguyễn Văn B");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("phone"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("phone"))).sendKeys("0333333334");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("email"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys("abdcd@gmail.com");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='register-submit']"))).click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Đăng ký thành công!\")]")).isEmpty());
		
	}

}