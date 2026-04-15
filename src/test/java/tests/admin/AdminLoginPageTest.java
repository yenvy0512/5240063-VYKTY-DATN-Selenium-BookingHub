package tests.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminLoginPageTest extends AdminBaseTest {

	@Test(description = "AL-01 Trang admin login hiển thị form đăng nhập")
	public void case_AL_001() {
		openAdmin();
		logoutAdminIfNeeded();

		Assert.assertTrue(driver.findElement(By.id("admin-usernameOrEmail")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("admin-password")).isDisplayed());
	}

	@Test(description = "AL-02 Tiêu đề trang đăng nhập hiển thỉ đúng")
	public void case_AL_002() {
		openAdmin();
		logoutAdminIfNeeded();

		Assert.assertTrue(wait.until(ExpectedConditions.titleIs("Đăng nhập - Admin Portal")));
	}

	@Test(description = "AL-03 Đăng nhập sai báo lỗi sai thông tin")
	public void case_AL_003() {
		openAdmin();
		logoutAdminIfNeeded();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail"))).sendKeys("wrong");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-password"))).sendKeys("wrong");

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-login-submit']")))
				.click();

		Assert.assertFalse(driver
				.findElements(By.xpath("//*[contains(normalize-space(.),'Sai tài khoản hoặc mật khẩu')]")).isEmpty());

	}

	@Test(description = "AL-04 Đăng nhập đúng thông báo đăng nhập thành công")
	public void case_AL_004() {
		openAdmin();
		logoutAdminIfNeeded();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-usernameOrEmail"))).sendKeys("admin");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("admin-password"))).sendKeys("123456aA@");

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-login-submit']")))
				.click();

		Assert.assertTrue(wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Đăng nhập thành công')]")))
				.isDisplayed());
	}

}