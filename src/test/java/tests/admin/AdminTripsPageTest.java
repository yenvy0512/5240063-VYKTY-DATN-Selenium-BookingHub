package tests.admin;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class AdminTripsPageTest {

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

	private void openTripsPage() {
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý chuyến"))).click();
		wait.until(ExpectedConditions.titleContains("Chuyến"));
	}

	@Test(description = "TR-01 Trang Quản lý Chuyến xe hiển thị")
	public void case_TR_001() {
		loginAdmin();
		openTripsPage();
		Assert.assertEquals(driver.getTitle(), "Quản lý Chuyến xe - BookingHub");

	}

	@Test(description = "TR-02 Heading trang Quản lý Chuyến xe hiển thị")
	public void case_TR_002() {
		loginAdmin();
		openTripsPage();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".text-2xl"))).getText()
				.contains("Quản lý Chuyến xe"));

	}

	@Test(description = "TR-03 Nút tạo chuyến hiển thị")
	public void case_TR_003() {
		loginAdmin();
		openTripsPage();
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='admin-trips-btn-create']")));
		Assert.assertFalse(driver.findElements(By.cssSelector("[data-testid='admin-trips-btn-create']")).isEmpty(),
				"Missing element");
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("[data-testid='admin-trips-btn-create']")))
				.getText().contains("Tạo chuyến mới"));

	}

	@Test(description = "TR-04 Ấn Tạo chuyến mới chuyển sang trang tạo chuyến")
	public void case_TR_004() {
		loginAdmin();
		openTripsPage();
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='admin-trips-btn-create']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-trips-btn-create']")))
				.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".text-2xl")));
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".text-2xl"))).getText()
				.contains("Tạo Chuyến Xe Mới"));

	}

	@Test(description = "TR-06 Bảng chuyến xe có thông tin điểm đến điểm đi")
	public void case_TR_006() {
		loginAdmin();
		openTripsPage();
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='admin-trips-btn-create']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-trips-btn-create']")))
				.click();
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(.,'Điểm đi') and contains(@class,'block')]")));
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(.,'Điểm đến') and contains(@class,'block')]")));
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(.,'Điểm đi') and contains(@class,'block')]")))
				.getText().contains("📍 Điểm đi *"));
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(.,'Điểm đến') and contains(@class,'block')]")))
				.getText().contains("📍 Điểm đến *"));

	}

	@Test(description = "TR-07 Ô tìm kiếm chuyến hiển thị")
	public void case_TR_007() {
		loginAdmin();
		openTripsPage();
		Assert.assertFalse(driver.findElements(By.cssSelector("[data-testid='admin-trips-search-input']")).isEmpty(),
				"Missing element");

	}

	@Test(description = "TR-08 Tìm kiếm chuyến với từ khóa")
	public void case_TR_008() {
		loginAdmin();
		openTripsPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-trips-search-input']")))
				.click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-trips-search-input']"))).clear();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-trips-search-input']")))
				.sendKeys("Hải phòng");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-trips-search-submit']")))
				.click();

	}

	@Test(description = "TR-11 Trang tạo chuyến hiển thị form cơ bản")
	public void case_TR_011() {
		loginAdmin();
		openTripsPage();
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='admin-trips-btn-create']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-trips-btn-create']")))
				.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".text-2xl")));
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".bg-white > .block")))
				.getText().contains("🚌 Phương tiện *"));
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(.,'Điểm đi') and contains(@class,'block')]")))
				.getText().contains("📍 Điểm đi *"));
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(.,'Điểm đến') and contains(@class,'block')]")))
				.getText().contains("📍 Điểm đến *"));
		Assert.assertTrue(wait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//*[contains(.,'Ngày khởi hành') and contains(@class,'block')]")))
				.getText().contains("📅 Ngày khởi hành *"));
		Assert.assertTrue(wait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//*[contains(.,'Giờ khởi hành') and contains(@class,'block')]")))
				.getText().contains("🕐 Giờ khởi hành *"));
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"💰 Giá vé cơ bản\")]")).isEmpty());
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath("//*[contains(.,'Loại Ghế') and contains(@class,'block')]")))
				.getText().contains("💺 Loại Ghế & Giá Vé *"));

	}

	@Test(description = "TR-12 Tiêu đề trang tạo chuyến xe đúng")
	public void case_TR_012() {
		loginAdmin();
		openTripsPage();
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='admin-trips-btn-create']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-trips-btn-create']")))
				.click();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		Assert.assertEquals(driver.getTitle(), "Tạo Chuyến Xe Mới - BookingHub");

	}

	@Test(description = "TR-13 Gửi thông tin tạo chuyến khi chưa chọn phương tiện nút tạo chuyến disable")
	public void case_TR_013() {
		loginAdmin();
		openTripsPage();
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='admin-trips-btn-create']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-trips-btn-create']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("departureLocationId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("departureLocationId"))))
				.selectByVisibleText("Hải Phòng - Lê Chân");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("arrivalLocationId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("arrivalLocationId"))))
				.selectByVisibleText("Buôn Ma Thuột - Tân Lợi");
		Assert.assertFalse(wait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("[data-testid=\"admin-trip-create-submit\"]")))
				.isEnabled());

	}

	@Test(description = "TR-14 Mở modal sửa chuyến xe")
	public void case_TR_014() {
		loginAdmin();
		openTripsPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid^='admin-trips-btn-delete-']")))
				.click();
		Assert.assertTrue(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".bg-white > .text-xl")))
						.getText().contains("Chỉnh sửa chuyến"));

	}

	@Test(description = "TR-15 Xóa chuyến xe")
	public void case_TR_015() {
		loginAdmin();
		openTripsPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid^='admin-trips-btn-delete-']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']")))
				.click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Xóa thành công!\")]")).isEmpty());

	}

	@Test(description = "TR-16 Sửa chuyến xe thành công")
	public void case_TR_016() {
		loginAdmin();
		openTripsPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(@data-testid,'btn-edit-')])[1]")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("departureTime"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("departureTime"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("departureTime"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("departureTime"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("departureTime")))
				.sendKeys("2026-04-29T09:00");
		By submitBtn = By.cssSelector("[data-testid='admin-trips-form-submit']");

		wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Cập nhật thành công!\")]")).isEmpty());

	}

	@Test(description = "TR-17 Tạo chuyến mới thành công")
	public void case_TR_017() {
		loginAdmin();
		openTripsPage();
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='admin-trips-btn-create']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-trips-btn-create']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("vehicleId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("vehicleId"))))
				.selectByVisibleText("Xe 16 (Ghế ngồi)");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("departureLocationId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("departureLocationId"))))
				.selectByVisibleText("Hồ Chí Minh - Bình Tân");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("arrivalLocationId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("arrivalLocationId"))))
				.selectByVisibleText("Đà Nẵng - Thanh Khê");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("bulkCreate"))).click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector(".react-datepicker-wrapper:nth-child(2) .w-full"))).click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector(".react-datepicker__time-list-item:nth-child(99)"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(1) > .relative .text-sm")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".react-datepicker__day--016"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(2) > .relative .w-full")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".react-datepicker__day--019"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".w-full:nth-child(3)"))).click();
//		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50"))).click();
		By submitBtn = By.cssSelector("[data-testid='admin-trip-create-submit']");

		wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
		Assert.assertFalse(driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Tạo chuyến thành công!\")]"))
				.isEmpty());

	}

}