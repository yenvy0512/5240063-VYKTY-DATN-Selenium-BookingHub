package tests.admin;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import config.Config;

public class AdminBookingsPageTest {

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

	@Test(description = "AB-01 Trang Quản lý Đặt vé hiển thị")
	public void case_AB_001() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý đặt vé"))).click();
		Assert.assertEquals(driver.getTitle(), "Quản lý Đặt vé - BookingHub");
		
	}

	@Test(description = "AB-02 Ô tìm kiếm đặt vé hiển thị")
	public void case_AB_002() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý đặt vé"))).click();
		Assert.assertFalse(driver.findElements(By.cssSelector(".flex-1:nth-child(1)")).isEmpty(), "Missing element");
		
	}

	@Test(description = "AB-03 Tiêu đề trang đặt vé đúng")
	public void case_AB_003() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý đặt vé"))).click();
		Assert.assertEquals(driver.getTitle(), "Quản lý Đặt vé - BookingHub");
		
	}

	@Test(description = "AB-04 Bảng đặt vé có cột Mã đặt vé Khách hàng Trạng thái")
	public void case_AB_004() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý đặt vé"))).click();
		Assert.assertFalse(driver.findElements(By.xpath("//*[contains(normalize-space(.),\"MÃ ĐẶT VÉ\")]")).isEmpty());
		Assert.assertFalse(driver.findElements(By.xpath("//*[contains(normalize-space(.),\"KHÁCH HÀNG\")]")).isEmpty());
		Assert.assertFalse(driver.findElements(By.xpath("//*[contains(normalize-space(.),\"CHUYẾN ĐI\")]")).isEmpty());
		Assert.assertFalse(driver.findElements(By.xpath("//*[contains(normalize-space(.),\"TRẠNG THÁI\")]")).isEmpty());
		
	}

	@Test(description = "AB-06 Nhập dữ liệu tìm kiếm không tồn tại hiển thị trống")
	public void case_AB_006() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(1280, 768));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý đặt vé"))).click();
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-bookings-search-input']")))
				.click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bookings-search-input']"))).clear();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bookings-search-input']")))
				.sendKeys("BK1212121");
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-bookings-search-submit']")))
				.click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Không có dữ liệu\")]")).isEmpty());
		
	}

	@Test(description = "AB-07 Nhập dữ liệu tìm kiếm tồn tại hiển thị dữ liệu")
	public void case_AB_007() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý đặt vé"))).click();
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-bookings-search-input']")))
				.click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bookings-search-input']"))).clear();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bookings-search-input']")))
				.sendKeys("BK202604130004");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".lucide-search"))).click();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".font-mono")))
				.getText().contains("BK202604130004"));
		
	}

	@Test(description = "AB-08 Ấn xem chi tiết thông tin vé hiển thị")
	public void case_AB_008() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý đặt vé"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".hover\\3A bg-gray-50:nth-child(1) path")))
				.click();
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".text-gray-800")))
				.getText().contains("Chi tiết đặt vé"));
		
	}

	@Test(description = "AB-09 Xác nhận thanh toán cho vé")
	public void case_AB_009() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý đặt vé"))).click();
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-bookings-search-input']")))
				.click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bookings-search-input']"))).clear();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bookings-search-input']")))
				.sendKeys("BK202510220008");
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-bookings-search-submit']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".lucide-check-circle"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".bg-yellow-600"))).click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Xác nhận thanh toán thành công!\")]"))
						.isEmpty());
		
	}

	@Test(description = "AB-10 Hủy vé khi vé đã gần giờ khởi hành")
	public void case_AB_010() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý đặt vé"))).click();
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-bookings-search-input']")))
				.click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bookings-search-input']"))).clear();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bookings-search-input']")))
				.sendKeys("BK202510220009");
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-bookings-search-submit']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".lucide-x"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']")))
				.click();
		Assert.assertFalse(driver.findElements(By.xpath(
				"//*[contains(normalize-space(.),\"Không thể hủy vé. Chỉ có thể hủy vé trước 30 phút giờ khởi hành.\")]"))
				.isEmpty());
		
	}

	@Test(description = "AB-11 Hủy vé thành công")
	public void case_AB_011() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý đặt vé"))).click();
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-bookings-search-input']")))
				.click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bookings-search-input']"))).clear();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bookings-search-input']")))
				.sendKeys("BK202604140004");
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-bookings-search-submit']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".lucide-x"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']")))
				.click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Hủy vé thành công!\")]")).isEmpty());
		
	}

}