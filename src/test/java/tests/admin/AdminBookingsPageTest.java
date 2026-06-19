package tests.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminBookingsPageTest extends AdminBaseTest {

	private void openBookingsPage() {

	    By bookingMenu = By.linkText("Quản lý đặt vé");

	    wait.until(driver -> {
	        try {

	            WebElement element = driver.findElement(bookingMenu);

	            if (element.isDisplayed() && element.isEnabled()) {
	                element.click();
	                return true;
	            }

	            return false;

	        } catch (StaleElementReferenceException e) {
	            return false;
	        }
	    });

	    wait.until(ExpectedConditions.or(
	            ExpectedConditions.titleContains("Đặt vé"),
	            ExpectedConditions.urlContains("/booking")
	    ));
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
		wait.until(ExpectedConditions.titleIs("Quản lý Đặt vé - BookingHub"));
		Assert.assertEquals(driver.getTitle(), "Quản lý Đặt vé - BookingHub");

	}

	@Test(description = "AB-04 Bảng đặt vé có cột Mã đặt vé Khách hàng Trạng thái")
	public void case_AB_004() {
		loginAdmin();
		openBookingsPage();
		String[] headers = { "Mã đặt vé", "Khách hàng", "Chuyến đi", "Trạng thái" };

		WebElement table = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bookings-table']")));

		wait.until(d -> table.findElements(By.tagName("th")).size() > 0);

		List<WebElement> ths = table.findElements(By.tagName("th"));

		List<String> actualHeaders = ths.stream().map(e -> e.getText().replace("\n", " ").trim()).toList();

		for (String header : headers) {
			boolean exists = actualHeaders.stream().anyMatch(h -> h.equalsIgnoreCase(header));

			Assert.assertTrue(exists, "Missing header: " + header);
		}
	}

	@Test(description = "AB-06 Nhập dữ liệu tìm kiếm không tồn tại hiển thị trống")
	public void case_AB_006() {
		loginAdmin();
		openBookingsPage();
		By searchInput = By.cssSelector("[data-testid='admin-bookings-search-input']");
		By searchBtn = By.cssSelector("[data-testid='admin-bookings-search-submit']");
		By emptyText = By.xpath("//*[contains(normalize-space(.),'Không có dữ liệu')]");

		WebElement input = wait.until(ExpectedConditions.elementToBeClickable(searchInput));
		input.clear();
		input.sendKeys("BK1212121");

		wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();

		WebElement empty = wait.until(ExpectedConditions.visibilityOfElementLocated(emptyText));

		Assert.assertTrue(empty.isDisplayed());

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
		By viewDetailBtn = By.cssSelector("[data-testid^='admin-bookings-btn-view-detail-']");
		By modalTitle = By.cssSelector("[data-testid='admin-booking-detail-modal-title']");

		List<WebElement> buttons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(viewDetailBtn));

		buttons.get(0).click();

		WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(modalTitle));

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
				.sendKeys("BK202606190003");
//		BK202606190004 BK202606190005
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
		By searchInput = By.cssSelector("[data-testid='admin-bookings-search-input']");
		By searchBtn = By.cssSelector("[data-testid='admin-bookings-search-submit']");
		By cancelBtn = By.cssSelector("[data-testid^='admin-bookings-btn-cancel-']");
		By confirmBtn = By.cssSelector("[data-testid='confirm-modal-confirm']");

		WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
		input.clear();
		input.sendKeys("BK202606190010");
//BK202606190011
		wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();

		wait.until(ExpectedConditions.elementToBeClickable(cancelBtn)).click();

		wait.until(ExpectedConditions.elementToBeClickable(confirmBtn)).click();

		By errorMsg = By.xpath("//*[contains(normalize-space(.),'Không thể hủy vé')]");

		WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg));
		Assert.assertTrue(msg.isDisplayed());

	}

	@Test(description = "AB-11 Hủy vé thành công")
	public void case_AB_011() {
		loginAdmin();
		openBookingsPage();
		By searchInput = By.cssSelector("[data-testid='admin-bookings-search-input']");
		By searchBtn = By.cssSelector("[data-testid='admin-bookings-search-submit']");
		By cancelBtn = By.cssSelector("[data-testid^='admin-bookings-btn-cancel-']");
		By confirmBtn = By.cssSelector("[data-testid='confirm-modal-confirm']");
		By successMsg = By.xpath("//*[contains(normalize-space(.),'Hủy vé thành công')]");

		WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
		input.clear();
		input.sendKeys("BK202606190009");

		wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();

		List<WebElement> cancelButtons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cancelBtn));
		cancelButtons.get(0).click();

		wait.until(ExpectedConditions.elementToBeClickable(confirmBtn)).click();

		WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg));
		Assert.assertTrue(msg.isDisplayed());

	}

}
