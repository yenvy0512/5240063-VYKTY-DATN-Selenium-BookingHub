package tests.admin;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import config.Config;

public class AdminUsersPageTest {

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

	@Test(description = "US-01 Trang Quản lý Người dùng hiển thị")
	public void case_US_001() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý người dùng"))).click();
		Assert.assertEquals(driver.getTitle(), "Quản lý Người dùng - BookingHub");

	}

	@Test(description = "US-02 Tiêu đề trang người dùng đúng")
	public void case_US_002() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý người dùng"))).click();
		Assert.assertEquals(driver.getTitle(), "Quản lý Người dùng - BookingHub");

	}

	@Test(description = "US-03 Heading Quản lý Người dùng hiển thị")
	public void case_US_003() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý người dùng"))).click();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".text-2xl"))).getText()
				.contains("Quản lý Người dùng"));

	}

	@Test(description = "US-04 Nút Thêm người dùng hiển thị")
	public void case_US_004() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý người dùng"))).click();
		Assert.assertFalse(driver.findElements(By.cssSelector("[data-testid='admin-users-btn-add']")).isEmpty(),
				"Missing element");
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("[data-testid='admin-users-btn-add']")))
				.getText().contains("Thêm người dùng"));

	}

	@Test(description = "US-05 Bảng người dùng hiển thị")
	public void case_US_005() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý người dùng"))).click();
		Assert.assertFalse(driver.findElements(By.cssSelector(".text-left > .p-3:nth-child(1)")).isEmpty(),
				"Missing element");
		Assert.assertFalse(driver.findElements(By.cssSelector(".text-left > .p-3:nth-child(2)")).isEmpty(),
				"Missing element");

	}

	@Test(description = "US-06 Modal thêm người dùng hiển thị")
	public void case_US_006() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý người dùng"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-users-btn-add']")))
				.click();
		Assert.assertTrue(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".bg-white > .text-xl")))
						.getText().contains("Thêm người dùng"));

	}

	@Test(description = "US-07 Thêm người dùng mới thành công")
	public void case_US_007() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý người dùng"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-users-btn-add']")))
				.click();
		Assert.assertTrue(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".bg-white > .text-xl")))
						.getText().contains("Thêm người dùng"));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(1) > .px-3"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(1) > .px-3"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(1) > .px-3")))
				.sendKeys("test");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(2) > .border"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(2) > .border"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(2) > .border")))
				.sendKeys("test@gmail.com");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(3) > .px-3"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(3) > .px-3"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(3) > .px-3")))
				.sendKeys("test");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(4) > .border"))).click();
		new Select(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div:nth-child(4) > .border"))))
				.selectByVisibleText("company_admin");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(6) > .w-full"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(6) > .w-full"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(6) > .w-full")))
				.sendKeys("123456aA@");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".px-4:nth-child(2)"))).click();
		Assert.assertFalse(driver
				.findElements(By.xpath("//*[contains(normalize-space(.),\"Tạo người dùng thành công!\")]")).isEmpty());

	}

	@Test(description = "US-08 Sửa người dùng")
	public void case_US_008() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý người dùng"))).click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector(".border-t:nth-child(1) .hover\\3A bg-blue-50 > .lucide")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(3) > .px-3"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(3) > .px-3"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(3) > .px-3")))
				.sendKeys("Admin11");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".px-4:nth-child(2)"))).click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Cập nhật người dùng thành công!\")]"))
						.isEmpty());

	}

	@Test(description = "US-09 Xóa người dùng")
	public void case_US_009() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý người dùng"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid^='admin-users-btn-delete-']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']")))
				.click();
		Assert.assertFalse(driver
				.findElements(By.xpath("//*[contains(normalize-space(.),\"Xóa người dùng thành công!\")]")).isEmpty());

	}

	@Test(description = "US-10 Thêm mới người dùng không nhập mật khẩu báo lỗi")
	public void case_US_010() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý người dùng"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-users-btn-add']")))
				.click();
		Assert.assertTrue(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".bg-white > .text-xl")))
						.getText().contains("Thêm người dùng"));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".px-4:nth-child(2)"))).click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Mật khẩu phải có ít nhất 6 ký tự\")]"))
						.isEmpty());

	}

	@Test(description = "US-11 Thêm mới người dùng không nhập username báo lỗi")
	public void case_US_011() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý người dùng"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-users-btn-add']")))
				.click();
		Assert.assertTrue(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".bg-white > .text-xl")))
						.getText().contains("Thêm người dùng"));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(6) > .w-full"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(6) > .w-full"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(6) > .w-full")))
				.sendKeys("123456");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(4) > .border"))).click();
		new Select(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div:nth-child(4) > .border"))))
				.selectByVisibleText("company_admin");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".px-4:nth-child(2)"))).click();
		Assert.assertFalse(driver
				.findElements(By.xpath("//*[contains(normalize-space(.),\"Vui lòng nhập tên đăng nhập\")]")).isEmpty());

	}

	@Test(description = "US-12 Thêm mới người dùng không chọn Role báo lỗi")
	public void case_US_012() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý người dùng"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-users-btn-add']")))
				.click();
		Assert.assertTrue(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".bg-white > .text-xl")))
						.getText().contains("Thêm người dùng"));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(1) > .px-3"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(1) > .px-3"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(1) > .px-3")))
				.sendKeys("abc");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(6) > .w-full"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(6) > .w-full"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(6) > .w-full")))
				.sendKeys("132456");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".px-4:nth-child(2)"))).click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Vui lòng chọn role\")]")).isEmpty());

	}

	@Test(description = "US-13 Thêm mới người dùng nhập trung username báo lỗi")
	public void case_US_013() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý người dùng"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-users-btn-add']")))
				.click();
		Assert.assertTrue(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".bg-white > .text-xl")))
						.getText().contains("Thêm người dùng"));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(1) > .px-3"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(1) > .px-3"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(1) > .px-3")))
				.sendKeys("test");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(2) > .border"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(4) > .border"))).click();
		new Select(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div:nth-child(4) > .border"))))
				.selectByVisibleText("company_admin");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(6) > .w-full"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(6) > .w-full"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(6) > .w-full")))
				.sendKeys("123456");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".px-4:nth-child(2)"))).click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Tài khoản đã tồn tại\")]")).isEmpty());

	}

}