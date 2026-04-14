package tests.admin;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import config.Config;

public class AdminLoginPageTest {

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

	@Test(description = "AL-01 Trang admin login hiển thị form đăng nhập")
	public void case_AL_001() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("logoutCount", String
				.valueOf(driver.findElements(By.xpath("xpath=//button[contains(@data-testid,'logout')]")).size()));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid=\"admin-header-logout\"]")))
				.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Đăng xuất')]")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Đăng xuất')]"))).click();
		Assert.assertFalse(driver.findElements(By.id("admin-usernameOrEmail")).isEmpty(), "Missing element");
		Assert.assertFalse(driver.findElements(By.id("admin-password")).isEmpty(), "Missing element");
	}

	@Test(description = "AL-02 Tiêu đề trang đăng nhập hiển thỉ đúng")
	public void case_AL_002() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("logoutCount", String
				.valueOf(driver.findElements(By.xpath("xpath=//button[contains(@data-testid,'logout')]")).size()));
		
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid=\"admin-header-logout\"]")))
				.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Đăng xuất')]")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Đăng xuất')]"))).click();
		
		Assert.assertEquals(driver.getTitle(), "Đăng nhập - Admin Portal");
		
	}

	@Test(description = "AL-03 Đăng nhập sai báo lỗi sai thông tin")
	public void case_AL_003() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("logoutCount", String
				.valueOf(driver.findElements(By.xpath("xpath=//button[contains(@data-testid,'logout')]")).size()));
		
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid=\"admin-header-logout\"]")))
				.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Đăng xuất')]")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Đăng xuất')]"))).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.id("admin-usernameOrEmail"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail"))).sendKeys("anccnncnc");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-password"))).sendKeys("12ss");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".hover\\3A bg-blue-700"))).click();
		Assert.assertFalse(driver
				.findElements(By.xpath("//*[contains(normalize-space(.),\"Sài tài khoản hoặc mật khẩu\")]")).isEmpty());
		
	}

	@Test(description = "AL-04 Đăng nhập đúng thông báo đăng nhập thành công")
	public void case_AL_004() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		vars.put("logoutCount", String
				.valueOf(driver.findElements(By.xpath("xpath=//button[contains(@data-testid,'logout')]")).size()));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid=\"admin-header-logout\"]")))
				.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Đăng xuất')]")));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Đăng xuất')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.id("admin-usernameOrEmail"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail"))).sendKeys("admin");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-password"))).sendKeys("123456aA@");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".hover\\3A bg-blue-700"))).click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Đăng nhập thành công!\")]")).isEmpty());
	}

}