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

public class AdminReviewsPageTest {

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

	@Test(description = "RV-01 Trang Quản lý Đánh giá hiển thị")
	public void case_RV_001() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đánh giá"))).click();
		Assert.assertEquals(driver.getTitle(), "Quản lý Đánh giá - BookingHub");

	}

	@Test(description = "RV-02 Tiêu đề trang đánh giá hiển thị đúng")
	public void case_RV_002() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đánh giá"))).click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Quản lý Đánh giá\")]")).isEmpty());

	}

	@Test(description = "RV-03 Nút tìm kiếm hiển thị")
	public void case_RV_003() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đánh giá"))).click();
		Assert.assertFalse(driver.findElements(By.cssSelector("[data-testid='admin-reviews-search-submit']")).isEmpty(),
				"Missing element");

	}

	@Test(description = "RV-04 Nhập thông tin và tìm kiếm đánh giá")
	public void case_RV_004() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đánh giá"))).click();
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-reviews-search-input']")))
				.click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-reviews-search-input']"))).clear();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-reviews-search-input']")))
				.sendKeys("BK202510220008");
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-reviews-search-submit']")))
				.click();

	}

	@Test(description = "RV-05 Nhập thông tin tìm kiếm không tồn tại")
	public void case_RV_005() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đánh giá"))).click();
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-reviews-search-input']")))
				.click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-reviews-search-input']"))).clear();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-reviews-search-input']")))
				.sendKeys("ZXCSAWR");
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-reviews-search-submit']")))
				.click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Không có dữ liệu\")]")).isEmpty());

	}

	@Test(description = "RV-06 Xóa đánh giá")
	public void case_RV_006() {
		driver.get(Config.getBaseUrlAdmin() + "/");
		driver.manage().window().setSize(new Dimension(945, 1012));
		driver.get(Config.getBaseUrlAdmin() + "/login");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail")))
				.sendKeys(Config.getAdminUsername());
		driver.findElement(By.name("password")).sendKeys(Config.getAdminPassword());
		driver.findElement(By.cssSelector("[data-testid='admin-login-submit']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đánh giá"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".lucide-trash2"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']")))
				.click();
		Assert.assertFalse(driver
				.findElements(By.xpath("//*[contains(normalize-space(.),\"Xóa đánh giá thành công!\")]")).isEmpty());

	}

}