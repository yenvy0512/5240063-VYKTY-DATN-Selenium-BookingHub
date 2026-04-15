package tests.admin;

import java.time.Duration;

import org.openqa.selenium.By;
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

public class AdminBusCompaniesTest {

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

	private void loginSuperAdmin() {
		driver.get(Config.getBaseUrlAdmin() + "/");

		WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.id("admin-usernameOrEmail")));
		username.clear();
		username.sendKeys(Config.getSuperAdminUsername());

		WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
		password.clear();
		password.sendKeys(Config.getSuperAdminPassword());

		WebElement btn = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-login-submit']")));
		btn.click();

		wait.until(ExpectedConditions.titleContains("Admin"));
	}

	private void openBusCompaniesPage() {
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Nhà xe"))).click();
		wait.until(ExpectedConditions.titleContains("Nhà xe"));
	}

	@Test(description = "BC-01 Trang Quản lý Nhà xe hiển thị")
	public void case_BC_001() {
		loginSuperAdmin();
		openBusCompaniesPage();
		Assert.assertEquals(driver.getTitle(), "Quản lý Nhà xe - BookingHub");

	}

	@Test(description = "BC-02 Tiêu đề trang nhà xe đúng")
	public void case_BC_002() {
		loginSuperAdmin();
		openBusCompaniesPage();
		Assert.assertEquals(driver.getTitle(), "Quản lý Nhà xe - BookingHub");

	}

	@Test(description = "BC-03 Bảng có các cột thông tin nhà xe")
	public void case_BC_003() {
		loginSuperAdmin();
		openBusCompaniesPage();
		Assert.assertFalse(driver.findElements(By.xpath("//*[contains(normalize-space(.),\"TÊN NHÀ XE\")]")).isEmpty());
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"SỐ ĐIỆN THOẠI\")]")).isEmpty());
		Assert.assertFalse(driver.findElements(By.xpath("//*[contains(normalize-space(.),\"ĐỊA CHỈ\")]")).isEmpty());

	}

	@Test(description = "BC-04 Nút Thêm nhà xe hiển thị với super admin")
	public void case_BC_004() {
		loginSuperAdmin();
		openBusCompaniesPage();
		Assert.assertFalse(driver.findElements(By.cssSelector("[data-testid='admin-bus-companies-btn-add']")).isEmpty(),
				"Missing element");
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("[data-testid='admin-bus-companies-btn-add']")))
				.getText().contains("Thêm mới"));

	}

	@Test(description = "BC-05 Thêm nhà xe không nhập thông tin và lưu lại")
	public void case_BC_005() {
		loginSuperAdmin();
		openBusCompaniesPage();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid=\"admin-bus-companies-btn-add\"]")));
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid=\"admin-bus-companies-btn-add\"]"))).click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid=\"admin-bus-companies-form-submit\"]"))).click();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("name"))).isEnabled());

	}

	@Test(description = "BC-06 Thêm nhà xe nhập đầy đủ thông tin và lưu lại")
	public void case_BC_006() {
		loginSuperAdmin();
		openBusCompaniesPage();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid=\"admin-bus-companies-btn-add\"]")));
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid=\"admin-bus-companies-btn-add\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("name"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).sendKeys("Test");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))).sendKeys("ackckkc@test.com");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phone"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phone"))).sendKeys("0333333333");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("address"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("address"))).sendKeys("Test");
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid=\"admin-bus-companies-form-submit\"]"))).click();
		Assert.assertFalse(driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Tạo nhà xe thành công!\")]"))
				.isEmpty());

	}

	@Test(description = "BC-07 Cập nhật nhà xe")
	public void case_BC_007() {
		loginSuperAdmin();
		openBusCompaniesPage();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid^='admin-bus-companies-btn-edit-']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("name"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).sendKeys("Test1");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//button[contains(.,'Lưu') or contains(.,'Cập nhật') or contains(.,'Thêm') or contains(.,'Tạo')]")))
				.click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Cập nhật thành công!\")]")).isEmpty());

	}

	@Test(description = "BC-08 Xóa nhà xe")
	public void case_BC_008() {
		loginSuperAdmin();
		openBusCompaniesPage();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid^='admin-bus-companies-btn-delete-']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']")))
				.click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Xóa thành công!\")]")).isEmpty());

	}

	@Test(description = "BC-09 Kiểm tra heading trang nhà xe")
	public void case_BC_009() {
		loginSuperAdmin();
		openBusCompaniesPage();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".text-2xl"))).getText()
				.contains("Quản lý Nhà xe"));

	}

	@Test(description = "BC-10 Kiểm tra hiển thị ô tìm kiếm nhà xe")
	public void case_BC_010() {
		loginSuperAdmin();
		openBusCompaniesPage();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid='admin-bus-companies-search-input']"))).click();
		Assert.assertFalse(
				driver.findElements(By.cssSelector("[data-testid='admin-bus-companies-search-input']")).isEmpty(),
				"Missing element");

	}

	@Test(description = "BC-11 Tìm kiếm nhà xe theo từ khóa")
	public void case_BC_011() {
		loginSuperAdmin();
		openBusCompaniesPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".p-6"))).click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid='admin-bus-companies-search-input']"))).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bus-companies-search-input']")))
				.clear();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bus-companies-search-input']")))
				.sendKeys("Test");
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid='admin-bus-companies-search-submit']"))).click();

	}

}
