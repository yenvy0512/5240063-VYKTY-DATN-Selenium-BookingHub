package tests.admin;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AdminVehiclesPageTest {

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

	private void loginAdmin() {
		driver.get(Config.getBaseUrlAdmin() + "/");

		WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.id("admin-usernameOrEmail")));
		username.clear();
		username.sendKeys(Config.getAdminUsername());

		WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
		password.clear();
		password.sendKeys(Config.getAdminPassword());

		WebElement btn = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-login-submit']")));
		btn.click();

		wait.until(ExpectedConditions.titleContains("Admin"));
	}

	private void openVehiclesPage() {
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Phương tiện"))).click();
		wait.until(ExpectedConditions.titleContains("Xe"));
	}

	@Test(description = "VH-01 Trang Quản lý phương tiện hiển thị")
	public void case_VH_001() {
		loginAdmin();
		openVehiclesPage();
		Assert.assertEquals(driver.getTitle(), "Quản lý Xe - BookingHub");
		
	}

	@Test(description = "VH-02 Tiêu đề trang quản lý  phương tiện đúng")
	public void case_VH_002() {
		loginAdmin();
		openVehiclesPage();
		Assert.assertEquals(driver.getTitle(), "Quản lý Xe - BookingHub");
		
	}

	@Test(description = "VH-03 Bảng phương tiên có cột Tên xe và Biển số")
	public void case_VH_003() {
		loginAdmin();
		openVehiclesPage();
		Assert.assertFalse(driver.findElements(By.xpath("//*[contains(normalize-space(.),\"TÊN XE\")]")).isEmpty());
		Assert.assertFalse(driver.findElements(By.xpath("//*[contains(normalize-space(.),\"BIỂN SỐ\")]")).isEmpty());
		
	}

	@Test(description = "VH-04 Nút Thêm xe hiển thị")
	public void case_VH_004() {
		loginAdmin();
		openVehiclesPage();
		Assert.assertFalse(driver.findElements(By.cssSelector("[data-testid='admin-vehicles-btn-add']")).isEmpty(),
				"Missing element");
		Assert.assertEquals(driver.getTitle(), "Quản lý Xe - BookingHub");
		
	}

	@Test(description = "VH-06 Tìm kiếm với từ khóa")
	public void case_VH_006() {
		loginAdmin();
		openVehiclesPage();
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-vehicles-search-input']")))
				.click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vehicles-search-input']"))).clear();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vehicles-search-input']")))
				.sendKeys("xe 16");
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-vehicles-search-submit']")))
				.click();
		Assert.assertFalse(driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Xe 16\")]")).isEmpty());
		
	}

	@Test(description = "VH-07 Tìm kiếm chuỗi rỗng vẫn thực hiện được")
	public void case_VH_007() {
		loginAdmin();
		openVehiclesPage();
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-vehicles-search-submit']")))
				.click();
		
	}

	@Test(description = "VH-09 Gửi thông tin trống thông báo lỗi")
	public void case_VH_009() {
		loginAdmin();
		openVehiclesPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-vehicles-btn-add']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
				"//button[contains(.,'Lưu') or contains(.,'Cập nhật') or contains(.,'Thêm') or contains(.,'Tạo')]")))
				.click();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("busType"))).isEnabled());
		
	}

	@Test(description = "VH-10 Thêm mới phương tiện thành công")
	public void case_VH_010() {
		loginAdmin();
		openVehiclesPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-vehicles-btn-add']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("busName"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("busName"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("busName"))).sendKeys("Xe 16 chỗ");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("busType"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("busType"))))
				.selectByVisibleText("Limousine");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("licensePlate"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("licensePlate"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("licensePlate"))).sendKeys("30A12345");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("layoutTemplateId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("layoutTemplateId"))))
				.selectByVisibleText("Xe 16 chỗ (16 chỗ)");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("totalSeats"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("totalSeats"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("totalSeats"))).sendKeys("16");
		By submitBtn = By.cssSelector("[data-testid='admin-vehicles-form-submit']");

		wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Tạo xe thành công!\")]")).isEmpty());
		
	}

	@Test(description = "VH-11 Chỉnh sửa phương tiện thành công")
	public void case_VH_011() {
		loginAdmin();
		openVehiclesPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid^='admin-vehicles-btn-edit-']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("busName"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("busName"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("busName"))).sendKeys("Xe 16 chỗ 1");
		By submitBtn = By.cssSelector("[data-testid='admin-vehicles-form-submit']");

		wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Cập nhật thành công!\")]")).isEmpty());
		
	}

	@Test(description = "VH-12 Xóa phương tiện")
	public void case_VH_012() {
		loginAdmin();
		openVehiclesPage();
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid^='admin-vehicles-btn-delete-']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']")))
				.click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Xóa thành công!\")]")).isEmpty());
		
	}

	@Test(description = "VH-13 Thêm phương tiện trùng biển số thông báo lỗi")
	public void case_VH_013() {
		loginAdmin();
		openVehiclesPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-vehicles-btn-add']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("busName"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("busName"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("busName"))).sendKeys("Xe 16 chỗ 1");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("busType"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("busType"))))
				.selectByVisibleText("Limousine");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("licensePlate"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("licensePlate"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("licensePlate"))).sendKeys("29K-50505");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("layoutTemplateId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("layoutTemplateId"))))
				.selectByVisibleText("Xe 16 chỗ (16 chỗ)");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("totalSeats"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("totalSeats"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("totalSeats"))).sendKeys("16");
		By submitBtn = By.cssSelector("[data-testid='admin-vehicles-form-submit']");

		wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Biển số đã tồn tại!\")]")).isEmpty());
		
	}

}