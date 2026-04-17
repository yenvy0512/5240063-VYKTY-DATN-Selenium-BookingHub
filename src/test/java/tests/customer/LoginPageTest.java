package tests.customer;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends CustomerBaseTest {

	@Test(description = "LG-01 Trang login hiển thị form đăng nhập")
	public void case_LG_001() {
		openCustomerHome();
		logoutCustomerIfNeeded();

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-login"))).click();

		By heading = By.cssSelector("[data-testid='customer-login-heading']");

		WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(heading));

		Assert.assertTrue(title.getText().contains("Đăng nhập"));

	}

	@Test(description = "LG-02 Tiêu đề trang login đúng")
	public void case_LG_002() {
		openCustomerHome();
		logoutCustomerIfNeeded();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-login"))).click();

		By heading = By.cssSelector("[data-testid='customer-login-heading']");

		WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(heading));

		Assert.assertTrue(title.getText().contains("Đăng nhập"));

	}

	@Test(description = "LG-04 Ô mật khẩu có type password")
	public void case_LG_004() {
		openCustomerHome();
		logoutCustomerIfNeeded();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-login"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
		Assert.assertFalse(driver.findElements(By.cssSelector("#password[type='password']")).isEmpty(),
				"Missing element");

	}

	@Test(description = "LG-06 Đăng nhập sai vẫn thông báo lỗi")
	public void case_LG_006() {
		openCustomerHome();
		logoutCustomerIfNeeded();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-login"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("usernameOrEmail"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).sendKeys("customer");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("password"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("avcs");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='login-submit']"))).click();
		Assert.assertFalse(driver
				.findElements(By.xpath("//*[contains(normalize-space(.),\"Sai tài khoản hoặc mật khẩu\")]")).isEmpty());

	}

	@Test(description = "LG-07 Form có ô tài khoàn và Mật khẩu")
	public void case_LG_007() {
		openCustomerHome();
		logoutCustomerIfNeeded();

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-login"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
		Assert.assertFalse(driver.findElements(By.id("usernameOrEmail")).isEmpty(), "Missing element");
		Assert.assertFalse(driver.findElements(By.id("password")).isEmpty(), "Missing element");

	}

	@Test(description = "LG-08 Có nút Đăng ký")
	public void case_LG_008() {
		openCustomerHome();
		logoutCustomerIfNeeded();

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-login"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Đăng ký ngay")));
		Assert.assertFalse(driver.findElements(By.linkText("Đăng ký ngay")).isEmpty(), "Missing element");

	}

	@Test(description = "LG-09 Ấn Đăng ký chuyển sang trang đăng ký")
	public void case_LG_009() {
		openCustomerHome();
		logoutCustomerIfNeeded();

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-login"))).click();

		WebElement registerBtn = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng ký ngay")));

		Assert.assertTrue(registerBtn.isDisplayed(), "Missing element");

		registerBtn.click();

		By heading = By.cssSelector("[data-testid='customer-register-heading']");

		WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(heading));

		Assert.assertTrue(title.getText().contains("Đăng ký"), "Không vào trang đăng ký");

	}

	@Test(description = "LG-10 Gửi thông tin form trống vẫn ở trang login và báo bắt buộc nhập")
	public void case_LG_010() {
		openCustomerHome();
		logoutCustomerIfNeeded();

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-login"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
		Assert.assertFalse(driver.findElements(By.id("usernameOrEmail")).isEmpty(), "Missing element");
		Assert.assertFalse(driver.findElements(By.id("password")).isEmpty(), "Missing element");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='login-submit']"))).click();
		WebElement username = driver.findElement(By.id("usernameOrEmail"));

		Boolean isValid = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].checkValidity();",
				username);

		Assert.assertFalse(isValid);

	}

	@Test(description = "LG-11 Đăng nhập đúng tài khoản rời khỏi trang login")
	public void case_LG_011() {
		openCustomerHome();
		logoutCustomerIfNeeded();

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-login"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("usernameOrEmail"))).sendKeys("customer");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys("123456aA@");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='login-submit']"))).click();
		WebElement toast = wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Đăng nhập thành công!')]")));

		Assert.assertTrue(toast.getText().contains("Đăng nhập thành công!"));
	}

}