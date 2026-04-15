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

public class AdminVASTest {

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

	private void openVasPage() {
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[contains(normalize-space(.),'VAS') or contains(normalize-space(.),'Dịch vụ')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Dịch vụ bổ sung"))).click();
		wait.until(ExpectedConditions.titleContains("Dịch vụ"));
	}

	@Test(description = "VS-01 Trang Quản lý Dịch vụ hiển thị")
	public void case_VS_001() {
		loginAdmin();
		openVasPage();
		Assert.assertEquals(driver.getTitle(), "Quản lý Dịch vụ - BookingHub");

	}

	@Test(description = "VS-02 Tiêu đề trang quản lý dịch vụ hiển thị đúng")
	public void case_VS_002() {
		loginAdmin();
		openVasPage();
		Assert.assertEquals(driver.getTitle(), "Quản lý Dịch vụ - BookingHub");

	}

	@Test(description = "VS-03 Heading Quản lý dịch vụ hiển thị đúng")
	public void case_VS_003() {
		loginAdmin();
		openVasPage();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".text-2xl"))).getText()
				.contains("Quản lý dịch vụ bổ sung"));

	}

	@Test(description = "VS-04 Nút thêm dịch vụ hiển thị")
	public void case_VS_004() {
		loginAdmin();
		openVasPage();
		Assert.assertFalse(driver.findElements(By.cssSelector("[data-testid='admin-vas-btn-add']")).isEmpty(),
				"Missing element");
		Assert.assertTrue(wait
				.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='admin-vas-btn-add']")))
				.getText().contains("+ Thêm dịch vụ mới"));

	}

	@Test(description = "VS-05 Tìm kiếm dịch vụ theo từ khóa")
	public void case_VS_005() {
		loginAdmin();
		openVasPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-vas-search-input']")))
				.click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vas-search-input']")))
				.clear();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vas-search-input']")))
				.sendKeys("Aqua");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-vas-search-submit']")))
				.click();
		Assert.assertTrue(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".border-t > .font-medium")))
						.getText().contains("Aqua"));

	}

	@Test(description = "VS-07 Thêm mới dịch vụ không nhập thông tin")
	public void case_VS_007() {
		loginAdmin();
		openVasPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-vas-btn-add']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50:nth-child(2)")))
				.click();
		Assert.assertTrue(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div:nth-child(1) > .px-3")))
						.isEnabled());

	}

	@Test(description = "VS-08 Thêm mới dịch vụ thành công")
	public void case_VS_008() {
		loginAdmin();
		openVasPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-vas-btn-add']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(1) > .px-3"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(1) > .px-3"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(1) > .px-3")))
				.sendKeys("Aqua");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(2) > .px-3"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div:nth-child(2) > .px-3"))))
				.selectByVisibleText("Nước uống");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(3) > .px-3"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(3) > .px-3"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(3) > .px-3")))
				.sendKeys("1000");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50:nth-child(2)")))
				.click();
		Assert.assertFalse(driver
				.findElements(By.xpath("//*[contains(normalize-space(.),\"Tạo dịch vụ thành công!\")]")).isEmpty());

	}

	@Test(description = "VS-09 Chỉnh sửa dịch vụ")
	public void case_VS_009() {
		loginAdmin();
		openVasPage();
//		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".border-t:nth-child(1) .text-blue-600")))
//				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid^='admin-vas-btn-edit-']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div:nth-child(3) > .px-3"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(3) > .px-3"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(3) > .px-3")))
				.sendKeys("10000");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50:nth-child(2)")))
				.click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Cập nhật dịch vụ thành công!\")]"))
						.isEmpty());

	}

	@Test(description = "VS-10 Xóa dịch vụ thành công")
	public void case_VS_010() {
		loginAdmin();
		openVasPage();
//		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".border-t:nth-child(1) .text-red-600")))
//				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid^='admin-vas-btn-delete-']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']")))
				.click();
		Assert.assertFalse(driver
				.findElements(By.xpath("//*[contains(normalize-space(.),\"Xóa dịch vụ thành công!\")]")).isEmpty());

	}

	@Test(description = "VS-11 Chỉnh sửa dịch vụ xóa thông tin bắt buộc nhập báo lỗi")
	public void case_VS_011() {
		loginAdmin();
		openVasPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".border-t:nth-child(1) .text-blue-600")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".fixed"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(1) > .px-3"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div:nth-child(1) > .px-3")))
				.sendKeys("");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".disabled\\3Aopacity-50:nth-child(2)")))
				.click();
		Assert.assertTrue(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div:nth-child(1) > .px-3")))
						.isEnabled());

	}

}
