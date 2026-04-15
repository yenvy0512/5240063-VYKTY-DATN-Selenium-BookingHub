package tests.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminDashboardPageTest extends AdminBaseTest {

	@Test(description = "AD-01 Dashboard hiển thị sau khi đăng nhập")
	public void case_AD_001() {
		loginAdmin();

		wait.until(ExpectedConditions.titleIs("Dashboard - BookingHub Admin"));
		Assert.assertEquals(driver.getTitle(), "Dashboard - BookingHub Admin");

	}

	@Test(description = "AD-02 Tiêu đề Dashboard đúng")
	public void case_AD_002() {
		loginAdmin();

		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Dashboard')]")))
				.isDisplayed());

	}

	@Test(description = "AD-03 Kiểm tra hiển thị thông tin Tổng quan")
	public void case_AD_003() {
		loginAdmin();

		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Tổng quan hệ thống')]")))
				.isDisplayed());

	}

	@Test(description = "AD-04 Kiểm tra hiển thị thông tin Thống kê đặt vé")
	public void case_AD_004() {
		loginAdmin();

		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Thống kê đặt vé')]")))
				.isDisplayed());

	}

}