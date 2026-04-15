package tests.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminBookingsPageTest extends AdminBaseTest {

	private void openBookingsPage() {
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý đặt vé"))).click();
		wait.until(ExpectedConditions.titleContains("Đặt vé"));
	}

	@Test(description = "AB-01 Trang Quản lý Đặt vé hiển thị")
	public void case_AB_001() {
		loginAdmin();
		openBookingsPage();
		Assert.assertEquals(driver.getTitle(), "Quản lý Đặt vé - BookingHub");

	}

	@Test(description = "AB-02 Ô tìm kiếm đặt vé hiển thị")
	public void case_AB_002() {
		loginAdmin();
		openBookingsPage();
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bookings-search-input']")))
				.isDisplayed());

	}

	@Test(description = "AB-03 Tiêu đề trang đặt vé đúng")
	public void case_AB_003() {
		loginAdmin();
		openBookingsPage();
		Assert.assertEquals(driver.getTitle(), "Quản lý Đặt vé - BookingHub");

	}

	@Test(description = "AB-04 Bảng đặt vé có cột Mã đặt vé Khách hàng Trạng thái")
	public void case_AB_004() {
		loginAdmin();
		openBookingsPage();
		String[] headers = { "MÃ ĐẶT VÉ", "KHÁCH HÀNG", "CHUYẾN ĐI", "TRẠNG THÁI" };

		WebElement table = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bookings-table']")));

		List<WebElement> ths = table.findElements(By.tagName("th"));

		List<String> actualHeaders = ths.stream().map(e -> e.getText().trim().toLowerCase()).toList();

		for (String header : headers) {
			Assert.assertTrue(actualHeaders.contains(header.toLowerCase()));
		}
	}

	@Test(description = "AB-06 Nhập dữ liệu tìm kiếm không tồn tại hiển thị trống")
	public void case_AB_006() {
		loginAdmin();
		openBookingsPage();
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
		loginAdmin();
		openBookingsPage();
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-bookings-search-input']")))
				.click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bookings-search-input']"))).clear();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bookings-search-input']")))
				.sendKeys("BK202604130004");
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-bookings-search-submit']")))
				.click();
		Assert.assertTrue(wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".font-mono")))
				.getText().contains("BK202604130004"));

	}

	@Test(description = "AB-08 Ấn xem chi tiết thông tin vé hiển thị")
	public void case_AB_008() {
		loginAdmin();
		openBookingsPage();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid^='admin-bookings-btn-view-detail-']"))).click();
		WebElement title = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-booking-detail-modal-title']")));

		Assert.assertTrue(title.getText().contains("Chi tiết đặt vé"));

	}

	@Test(description = "AB-09 Xác nhận thanh toán cho vé")
	public void case_AB_009() {
		loginAdmin();
		openBookingsPage();
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-bookings-search-input']")))
				.click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bookings-search-input']"))).clear();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bookings-search-input']")))
				.sendKeys("BK202510220007");
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-bookings-search-submit']")))
				.click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid^='admin-bookings-btn-confirm-payment-']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']")))
				.click();

		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Xác nhận thanh toán thành công')]")))
				.isDisplayed());

	}

	@Test(description = "AB-10 Hủy vé khi vé đã gần giờ khởi hành")
	public void case_AB_010() {
		loginAdmin();
		openBookingsPage();
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-bookings-search-input']")))
				.click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bookings-search-input']"))).clear();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bookings-search-input']")))
				.sendKeys("BK202510220010");
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-bookings-search-submit']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".lucide-x"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']")))
				.click();
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Không thể hủy vé. Chỉ có thể hủy vé trước 30 phút giờ khởi hành.')]")))
				.isDisplayed());

	}

	@Test(description = "AB-11 Hủy vé thành công")
	public void case_AB_011() {
		loginAdmin();
		openBookingsPage();
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-bookings-search-input']")))
				.click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bookings-search-input']"))).clear();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bookings-search-input']")))
				.sendKeys("BK202604150004");
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-bookings-search-submit']")))
				.click();
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid^='admin-bookings-btn-cancel-']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']")))
				.click();
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Hủy vé thành công!')]")))
				.isDisplayed());

	}

}
