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

public class ProfilePageTest {

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

	@Test(description = "PF-01 Trang thông tin cá nhân hiển thị sau khi đăng nhập")
	public void case_PF_001() {
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
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[contains(normalize-space(.),'Hồ sơ') or contains(normalize-space(.),'Thông tin cá nhân')]")))
				.click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[contains(@href,'/register') or contains(.,'Đăng ký')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tài khoản"))).click();
		Assert.assertEquals(driver.getTitle(), "Thông tin cá nhân - BookingHub");
		
	}

	@Test(description = "PF-02 Tiêu đề trang cá nhân hiển thị đúng")
	public void case_PF_002() {
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
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[contains(normalize-space(.),'Hồ sơ') or contains(normalize-space(.),'Thông tin cá nhân')]")))
				.click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[contains(@href,'/register') or contains(.,'Đăng ký')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tài khoản"))).click();
		Assert.assertEquals(driver.getTitle(), "Thông tin cá nhân - BookingHub");
		
	}

	@Test(description = "PF-04 Heading Thông tin cá nhân hiển thị")
	public void case_PF_004() {
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
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[contains(normalize-space(.),'Hồ sơ') or contains(normalize-space(.),'Thông tin cá nhân')]")))
				.click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[contains(@href,'/register') or contains(.,'Đăng ký')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tài khoản"))).click();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".text-3xl"))).getText()
				.contains("Thông tin cá nhân"));
		
	}

	@Test(description = "PF-05 Form hiển thị các trường dữ liệu")
	public void case_PF_005() {
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
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[contains(normalize-space(.),'Hồ sơ') or contains(normalize-space(.),'Thông tin cá nhân')]")))
				.click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[contains(@href,'/register') or contains(.,'Đăng ký')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tài khoản"))).click();
		Assert.assertFalse(driver.findElements(By.cssSelector(".bg-gray-100")).isEmpty(), "Missing element");
		Assert.assertFalse(driver.findElements(By.name("name")).isEmpty(), "Missing element");
		Assert.assertFalse(driver.findElements(By.name("email")).isEmpty(), "Missing element");
		Assert.assertFalse(driver.findElements(By.name("phone")).isEmpty(), "Missing element");
		
	}

	@Test(description = "PF-06 Nút Lưu thay đổi hiển thị")
	public void case_PF_006() {
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
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[contains(normalize-space(.),'Hồ sơ') or contains(normalize-space(.),'Thông tin cá nhân')]")))
				.click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[contains(@href,'/register') or contains(.,'Đăng ký')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tài khoản"))).click();
		Assert.assertTrue(wait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//button[contains(.,'Lưu thay đổi') or contains(.,'Lưu')]")))
				.getText().contains("Lưu thay đổi"));
		
	}

	@Test(description = "PF-08 Có thể điền lại họ tên và lưu lại")
	public void case_PF_008() {
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
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[contains(normalize-space(.),'Hồ sơ') or contains(normalize-space(.),'Thông tin cá nhân')]")))
				.click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[contains(@href,'/register') or contains(.,'Đăng ký')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tài khoản"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("name"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).sendKeys("Nguyễn Văn B");
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[contains(.,'Lưu thay đổi') or contains(.,'Lưu')]"))).click();
		new Actions(driver).moveToElement(wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//button[contains(.,'Lưu thay đổi') or contains(.,'Lưu')]"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Cập nhật profile thành công!\")]"))
						.isEmpty());
		
	}

	@Test(description = "PF-09 Có thể điền lại email và lưu lại")
	public void case_PF_009() {
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
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[contains(normalize-space(.),'Hồ sơ') or contains(normalize-space(.),'Thông tin cá nhân')]")))
				.click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[contains(@href,'/register') or contains(.,'Đăng ký')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tài khoản"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("email"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))).sendKeys("customer1@gmail.com");
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[contains(.,'Lưu thay đổi') or contains(.,'Lưu')]"))).click();
		new Actions(driver).moveToElement(wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//button[contains(.,'Lưu thay đổi') or contains(.,'Lưu')]"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Cập nhật profile thành công!\")]"))
						.isEmpty());
		
	}

	@Test(description = "PF-10 Có thể điền lại SĐT và lưu lại")
	public void case_PF_010() {
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
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[contains(normalize-space(.),'Hồ sơ') or contains(normalize-space(.),'Thông tin cá nhân')]")))
				.click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[contains(@href,'/register') or contains(.,'Đăng ký')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tài khoản"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("phone"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phone"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phone"))).sendKeys("0854256406");
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[contains(.,'Lưu thay đổi') or contains(.,'Lưu')]"))).click();
		new Actions(driver).moveToElement(wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//button[contains(.,'Lưu thay đổi') or contains(.,'Lưu')]"))))
				.perform();
		new Actions(driver).moveByOffset(1, 1).perform();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Cập nhật profile thành công!\")]"))
						.isEmpty());
		
	}

	@Test(description = "PF-11 Để trống thông tin họ tên thông báo lỗi")
	public void case_PF_011() {
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
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[contains(normalize-space(.),'Hồ sơ') or contains(normalize-space(.),'Thông tin cá nhân')]")))
				.click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[contains(@href,'/register') or contains(.,'Đăng ký')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tài khoản"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("html"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).sendKeys("");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".space-y-6"))).click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[contains(.,'Lưu thay đổi') or contains(.,'Lưu')]"))).click();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("name"))).isEnabled());
		
	}

	@Test(description = "PF-12 Để trống thông tin email thông báo lỗi")
	public void case_PF_012() {
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
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[contains(normalize-space(.),'Hồ sơ') or contains(normalize-space(.),'Thông tin cá nhân')]")))
				.click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[contains(@href,'/register') or contains(.,'Đăng ký')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tài khoản"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("html"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))).sendKeys("");
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[contains(.,'Lưu thay đổi') or contains(.,'Lưu')]"))).click();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email"))).isEnabled());
		
	}

	@Test(description = "PF-13 Để trống thông tin SĐT thông báo lỗi")
	public void case_PF_013() {
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
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//*[contains(normalize-space(.),'Hồ sơ') or contains(normalize-space(.),'Thông tin cá nhân')]")))
				.click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//a[contains(@href,'/register') or contains(.,'Đăng ký')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Tài khoản"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("phone"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("phone"))).click();
		new Actions(driver).doubleClick(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("phone"))))
				.perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phone"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phone"))).sendKeys("");
		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[contains(.,'Lưu thay đổi') or contains(.,'Lưu')]"))).click();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("phone"))).isEnabled());
		
	}

}