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

public class AdminReviewsPageTest {

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

	private void openReviewsPage() {
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đánh giá"))).click();
		wait.until(ExpectedConditions.titleContains("Đánh giá"));
	}

	@Test(description = "RV-01 Trang Quản lý Đánh giá hiển thị")
	public void case_RV_001() {
		loginAdmin();
		openReviewsPage();
		Assert.assertEquals(driver.getTitle(), "Quản lý Đánh giá - BookingHub");

	}

	@Test(description = "RV-02 Tiêu đề trang đánh giá hiển thị đúng")
	public void case_RV_002() {
		loginAdmin();
		openReviewsPage();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Quản lý Đánh giá\")]")).isEmpty());

	}

	@Test(description = "RV-03 Nút tìm kiếm hiển thị")
	public void case_RV_003() {
		loginAdmin();
		openReviewsPage();
		Assert.assertFalse(driver.findElements(By.cssSelector("[data-testid='admin-reviews-search-submit']")).isEmpty(),
				"Missing element");

	}

	@Test(description = "RV-04 Nhập thông tin và tìm kiếm đánh giá")
	public void case_RV_004() {
		loginAdmin();
		openReviewsPage();
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
		loginAdmin();
		openReviewsPage();
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
		loginAdmin();
		openReviewsPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".lucide-trash2"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']")))
				.click();
		Assert.assertFalse(driver
				.findElements(By.xpath("//*[contains(normalize-space(.),\"Xóa đánh giá thành công!\")]")).isEmpty());

	}

}