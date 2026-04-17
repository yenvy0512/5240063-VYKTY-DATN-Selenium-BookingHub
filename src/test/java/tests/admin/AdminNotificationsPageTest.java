package tests.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminNotificationsPageTest extends AdminBaseTest {

	private void openNotificationsPage() {
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='sidebar-link-notifications']")))
				.click();
		wait.until(ExpectedConditions.titleContains("Thông báo"));
	}

	@Test(description = "NT-01 Trang Thông báo hiển thị")
	public void case_NT_001() {
		loginAdmin();
		openNotificationsPage();
		Assert.assertEquals(driver.getTitle(), "Thông báo - BookingHub Admin");

	}

	@Test(description = "NT-02 Tiêu đề trang thông báo đúng")
	public void case_NT_002() {
		loginAdmin();
		openNotificationsPage();
		Assert.assertEquals(driver.getTitle(), "Thông báo - BookingHub Admin");

	}

	@Test(description = "NT-03 Ô tìm kiếm thông báo hiển thị")
	public void case_NT_003() {
		loginAdmin();
		openNotificationsPage();
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-notifications-input-search']")))
				.isDisplayed());

	}

	@Test(description = "NT-04 Nút tải lại hiển thị")
	public void case_NT_004() {
		loginAdmin();
		openNotificationsPage();
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-notifications-btn-refresh']")))
				.isDisplayed());

	}

	@Test(description = "NT-05 Nút đánh dấu đã đọc hiển thị")
	public void case_NT_005() {
		loginAdmin();
		openNotificationsPage();
		By markAllReadBtn = By.cssSelector("[data-testid='admin-notifications-btn-mark-all-read']");

		WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(markAllReadBtn));

		Assert.assertTrue(btn.isDisplayed());

	}

	@Test(description = "NT-06 Đánh dấu tất cả là đã đọc thông báo")
	public void case_NT_006() {
		loginAdmin();
		openNotificationsPage();
		List<WebElement> buttons = driver
				.findElements(By.cssSelector("[data-testid='admin-notifications-btn-mark-all-read']"));

		if (!buttons.isEmpty()) {
			WebElement btn = wait.until(ExpectedConditions
					.elementToBeClickable(By.cssSelector("[data-testid='admin-notifications-btn-mark-all-read']")));
			btn.click();

			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath("//*[contains(text(),'Đã đánh dấu tất cả thông báo là đã đọc')]")));
		}

	}

	@Test(description = "NT-07 Tải lại trang thông báo")
	public void case_NT_007() {
		loginAdmin();
		openNotificationsPage();
		By refreshBtn = By.cssSelector("[data-testid='admin-notifications-btn-refresh']");
		By toast = By.xpath("//*[contains(normalize-space(.),'Đã tải lại thông báo')]");

		wait.until(ExpectedConditions.elementToBeClickable(refreshBtn)).click();

		wait.until(ExpectedConditions.presenceOfElementLocated(toast));

		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(toast)).isDisplayed());

	}

}