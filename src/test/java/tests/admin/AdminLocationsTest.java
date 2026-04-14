package tests.admin;

import java.time.Duration;

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

public class AdminLocationsTest {

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

	@Test(description = "LC-01 Kiểm tra hiển thị trang quản lý địa điểm")
	public void case_LC_001() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrl() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail")))
				.sendKeys(Config.getCustomerUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getCustomerPassword());
		driver.findElement(By.cssSelector("[data-testid='login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Địa điểm')]")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý địa điểm"))).click();
		Assert.assertEquals(driver.getTitle(), "Quản lý Địa điểm - BookingHub");

	}

	@Test(description = "LC-02 Kiểm tra hiển thị tiêu đề trang địa điểm")
	public void case_LC_002() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrl() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail")))
				.sendKeys(Config.getCustomerUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getCustomerPassword());
		driver.findElement(By.cssSelector("[data-testid='login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Địa điểm')]")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý địa điểm"))).click();
		Assert.assertEquals(driver.getTitle(), "Quản lý Địa điểm - BookingHub");

	}

	@Test(description = "LC-03 Kiểm tra bảng có cột Thành phố")
	public void case_LC_003() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrl() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail")))
				.sendKeys(Config.getCustomerUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getCustomerPassword());
		driver.findElement(By.cssSelector("[data-testid='login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Địa điểm')]")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý địa điểm"))).click();
		Assert.assertFalse(driver.findElements(By.xpath("//*[contains(normalize-space(.),\"THÀNH PHỐ\")]")).isEmpty());

	}

	@Test(description = "LC-04 Kiểm tra hiển thị nút thêm địa điểm")
	public void case_LC_004() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrl() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail")))
				.sendKeys(Config.getCustomerUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getCustomerPassword());
		driver.findElement(By.cssSelector("[data-testid='login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Địa điểm')]")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý địa điểm"))).click();
		Assert.assertFalse(driver.findElements(By.cssSelector("[data-testid='admin-locations-btn-add']")).isEmpty(),
				"Missing element");
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("[data-testid='admin-locations-btn-add']")))
				.getText().contains("Thêm mới"));

	}

	@Test(description = "LC-05 Không nhập thông tin ấn lưu")
	public void case_LC_005() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrl() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail")))
				.sendKeys(Config.getCustomerUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getCustomerPassword());
		driver.findElement(By.cssSelector("[data-testid='login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Địa điểm')]")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý địa điểm"))).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid=\"admin-locations-btn-add\"]")));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid=\"admin-locations-btn-add\"]")))
				.click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid=\"admin-locations-form-submit\"]"))).click();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("city"))).isEnabled());

	}

	@Test(description = "LC-06 Chỉ nhập thông tin thành phố ấn lưu lại")
	public void case_LC_006() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrl() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail")))
				.sendKeys(Config.getCustomerUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getCustomerPassword());
		driver.findElement(By.cssSelector("[data-testid='login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Địa điểm')]")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý địa điểm"))).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid=\"admin-locations-btn-add\"]")));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid=\"admin-locations-btn-add\"]")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("city"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("city"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("city"))).sendKeys("Thành phố test");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//button[contains(.,'Lưu') or contains(.,'Cập nhật') or contains(.,'Thêm') or contains(.,'Tạo')]")))
				.click();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("district"))).isEnabled());

	}

	@Test(description = "LC-07 Nhập đầy đủ thông tin và lưu địa điểm")
	public void case_LC_007() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrl() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail")))
				.sendKeys(Config.getCustomerUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getCustomerPassword());
		driver.findElement(By.cssSelector("[data-testid='login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Địa điểm')]")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý địa điểm"))).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid=\"admin-locations-btn-add\"]")));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid=\"admin-locations-btn-add\"]")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("city"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("city"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("city"))).sendKeys("Thành phố test");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("district"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("district"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("district"))).sendKeys("Test");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("latitude"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("latitude"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("latitude"))).sendKeys("21.028222");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("longitude"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("longitude"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("longitude"))).sendKeys("105.88888");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//button[contains(.,'Lưu') or contains(.,'Cập nhật') or contains(.,'Thêm') or contains(.,'Tạo')]")))
				.click();
		Assert.assertFalse(driver
				.findElements(By.xpath("//*[contains(normalize-space(.),\"Tạo địa điểm thành công!\")]")).isEmpty());

	}

	@Test(description = "LC-08 Chỉnh sửa địa điểm")
	public void case_LC_008() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrl() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail")))
				.sendKeys(Config.getCustomerUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getCustomerPassword());
		driver.findElement(By.cssSelector("[data-testid='login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Địa điểm')]")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý địa điểm"))).click();
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid^='admin-locations-btn-edit-']")))
				.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("district"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("district"))).sendKeys("Test1");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//button[contains(.,'Lưu') or contains(.,'Cập nhật') or contains(.,'Thêm') or contains(.,'Tạo')]")))
				.click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Cập nhật thành công!\")]")).isEmpty());

	}

	@Test(description = "LC-09 Xóa địa điểm")
	public void case_LC_009() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrl() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail")))
				.sendKeys(Config.getCustomerUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getCustomerPassword());
		driver.findElement(By.cssSelector("[data-testid='login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Địa điểm')]")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý địa điểm"))).click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector(".hover\\3A bg-gray-50:nth-child(1) .hover\\3A bg-red-50")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']")))
				.click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Xóa thành công!\")]")).isEmpty());

	}

	@Test(description = "LC-10 Heading Quản lý Địa điểm hiển thị")
	public void case_LC_010() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrl() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail")))
				.sendKeys(Config.getCustomerUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getCustomerPassword());
		driver.findElement(By.cssSelector("[data-testid='login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Địa điểm')]")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý địa điểm"))).click();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".text-2xl"))).getText()
				.contains("Quản lý Địa điểm"));

	}

	@Test(description = "LC-11 Ô tìm kiếm địa điểm hiển thị")
	public void case_LC_011() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrl() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail")))
				.sendKeys(Config.getCustomerUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getCustomerPassword());
		driver.findElement(By.cssSelector("[data-testid='login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Địa điểm')]")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý địa điểm"))).click();
		Assert.assertFalse(
				driver.findElements(By.cssSelector("[data-testid='admin-locations-search-input']")).isEmpty(),
				"Missing element");

	}

	@Test(description = "LC-12 Tìm kiếm địa điểm theo từ khóa")
	public void case_LC_012() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrl() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail")))
				.sendKeys(Config.getCustomerUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getCustomerPassword());
		driver.findElement(By.cssSelector("[data-testid='login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(normalize-space(.),'Địa điểm')]")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý địa điểm"))).click();
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-locations-search-input']")))
				.click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-locations-search-input']"))).clear();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-locations-search-input']")))
				.sendKeys("Test");
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid='admin-locations-search-submit']"))).click();

	}

}