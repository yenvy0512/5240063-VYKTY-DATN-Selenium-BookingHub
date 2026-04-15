package tests.customer;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import config.Config;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ProfilePageTest {

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

	private void loginCustomer() {
		driver.get(Config.getBaseUrl() + "/");

		List<WebElement> loginBtn = driver.findElements(By.cssSelector(".btn-login"));

		if (!loginBtn.isEmpty()) {

			loginBtn.get(0).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();

			WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.id("usernameOrEmail")));
			username.clear();
			username.sendKeys(Config.getCustomerUsername());

			WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
			password.clear();
			password.sendKeys(Config.getCustomerPassword());

			WebElement submitBtn = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Đăng nhập')]")));
			submitBtn.click();

			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".btn-login")));

		}
	}

	private void openProfilePage() {

		WebElement userMenuBtn = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//button[contains(@class,'flex items-center') and .//span]")));
		userMenuBtn.click();

		WebElement profileBtn = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//a[@href='/profile' and contains(.,'Tài khoản')]")));

		profileBtn.click();

		wait.until(ExpectedConditions.urlContains("/profile"));
	}

	private void waitForToast(String message) {
		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(d -> d.findElement(By.tagName("body")).getText().contains(message));
	}

	@Test(description = "PF-01 Trang thông tin cá nhân hiển thị sau khi đăng nhập")
	public void case_PF_001() {
		loginCustomer();
		openProfilePage();

		Assert.assertEquals(driver.getTitle(), "Thông tin cá nhân - BookingHub");
	}

	@Test(description = "PF-02 Tiêu đề trang cá nhân hiển thị đúng")
	public void case_PF_002() {
		loginCustomer();
		openProfilePage();

		wait.until(ExpectedConditions.titleIs("Thông tin cá nhân - BookingHub"));
		Assert.assertEquals(driver.getTitle(), "Thông tin cá nhân - BookingHub");
	}

	@Test(description = "PF-04 Heading Thông tin cá nhân hiển thị")
	public void case_PF_004() {
		loginCustomer();
		openProfilePage();

		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".text-3xl"))).getText()
				.contains("Thông tin cá nhân"));
	}

	@Test(description = "PF-05 Form hiển thị các trường dữ liệu")
	public void case_PF_005() {
		loginCustomer();
		openProfilePage();

		Assert.assertFalse(driver.findElements(By.cssSelector(".bg-gray-100")).isEmpty(), "Missing element");
		Assert.assertFalse(driver.findElements(By.name("name")).isEmpty(), "Missing element");
		Assert.assertFalse(driver.findElements(By.name("email")).isEmpty(), "Missing element");
		Assert.assertFalse(driver.findElements(By.name("phone")).isEmpty(), "Missing element");
	}

	@Test(description = "PF-06 Nút Lưu thay đổi hiển thị")
	public void case_PF_006() {
		loginCustomer();
		openProfilePage();

		Assert.assertTrue(wait
				.until(ExpectedConditions.presenceOfElementLocated(
						By.xpath("//button[contains(.,'Lưu thay đổi') or contains(.,'Lưu')]")))
				.getText().contains("Lưu thay đổi"));
	}

	@Test(description = "PF-08 Có thể điền lại họ tên và lưu lại")
	public void case_PF_008() {
		loginCustomer();
		openProfilePage();

		wait.until(ExpectedConditions.elementToBeClickable(By.name("name"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).sendKeys("Nguyễn Văn B");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='customer-profile-submit']")))
				.click();
		waitForToast("Cập nhật profile thành công!");
	}

	@Test(description = "PF-09 Có thể điền lại email và lưu lại")
	public void case_PF_009() {
		loginCustomer();
		openProfilePage();

		wait.until(ExpectedConditions.elementToBeClickable(By.name("email"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))).sendKeys("customer1@gmail.com");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='customer-profile-submit']")))
				.click();
		waitForToast("Cập nhật profile thành công!");
	}

	@Test(description = "PF-10 Có thể điền lại SĐT và lưu lại")
	public void case_PF_010() {
		loginCustomer();
		openProfilePage();

		wait.until(ExpectedConditions.elementToBeClickable(By.name("phone"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phone"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phone"))).sendKeys("0854256406");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='customer-profile-submit']")));
		waitForToast("Cập nhật profile thành công!");
	}

	@Test(description = "PF-11 Để trống thông tin họ tên thông báo lỗi")
	public void case_PF_011() {
		loginCustomer();
		openProfilePage();

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("html"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).sendKeys("");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".space-y-6"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='customer-profile-submit']")));
		WebElement name = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("name")));

		Boolean isValid = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].checkValidity();",
				name);

		Assert.assertFalse(isValid);
	}

	@Test(description = "PF-12 Để trống thông tin email thông báo lỗi")
	public void case_PF_012() {
		loginCustomer();
		openProfilePage();

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("html"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))).sendKeys("");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='customer-profile-submit']")));
		WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));

		Boolean isValid = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].checkValidity();",
				email);

		Assert.assertFalse(isValid);
	}

	@Test(description = "PF-13 Để trống thông tin SĐT thông báo lỗi")
	public void case_PF_013() {
		loginCustomer();
		openProfilePage();

		wait.until(ExpectedConditions.elementToBeClickable(By.name("phone"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("phone"))).click();
		new Actions(driver).doubleClick(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("phone"))))
				.perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phone"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phone"))).sendKeys("");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='customer-profile-submit']")));
		WebElement phone = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("phone")));

		Boolean isValid = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].checkValidity();",
				phone);

		Assert.assertFalse(isValid);
	}

}
