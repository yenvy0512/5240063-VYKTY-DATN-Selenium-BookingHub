package tests.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminTripsPageTest extends AdminBaseTest {

	private void openTripsPage() {
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý chuyến"))).click();
		wait.until(ExpectedConditions.titleContains("Chuyến"));
	}

	@Test(description = "TR-01 Trang Quản lý Chuyến xe hiển thị")
	public void case_TR_001() {
		loginAdmin();
		openTripsPage();
		Assert.assertEquals(driver.getTitle(), "Quản lý Chuyến xe - BookingHub");

	}

	@Test(description = "TR-02 Heading trang Quản lý Chuyến xe hiển thị")
	public void case_TR_002() {
		loginAdmin();
		openTripsPage();
		WebElement heading = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-trips-heading']")));

		Assert.assertTrue(heading.getText().contains("Quản lý Chuyến xe"));

	}

	@Test(description = "TR-03 Nút tạo chuyến hiển thị")
	public void case_TR_003() {
		loginAdmin();
		openTripsPage();
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='admin-trips-btn-create']")));
		Assert.assertFalse(driver.findElements(By.cssSelector("[data-testid='admin-trips-btn-create']")).isEmpty(),
				"Missing element");
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("[data-testid='admin-trips-btn-create']")))
				.getText().contains("Tạo chuyến mới"));

	}

	@Test(description = "TR-04 Ấn Tạo chuyến mới chuyển sang trang tạo chuyến")
	public void case_TR_004() {
		loginAdmin();
		openTripsPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-trips-btn-create']")))
				.click();
		WebElement heading = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-trip-create-heading']")));

		Assert.assertTrue(heading.getText().contains("Tạo Chuyến Xe Mới"));
	}

	@Test(description = "TR-06 Bảng chuyến xe có thông tin điểm đến điểm đi")
	public void case_TR_006() {
		loginAdmin();
		openTripsPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-trips-btn-create']")))
				.click();
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("departureLocationId")))
				.isDisplayed());

		Assert.assertTrue(
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("arrivalLocationId"))).isDisplayed());

	}

	@Test(description = "TR-07 Ô tìm kiếm chuyến hiển thị")
	public void case_TR_007() {
		loginAdmin();
		openTripsPage();
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-trips-search-input']")))
				.isDisplayed());

	}

	@Test(description = "TR-08 Tìm kiếm chuyến với từ khóa")
	public void case_TR_008() {
		loginAdmin();
		openTripsPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-trips-search-input']")))
				.click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-trips-search-input']"))).clear();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-trips-search-input']")))
				.sendKeys("Hải phòng");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-trips-search-submit']")))
				.click();

	}

	@Test(description = "TR-11 Trang tạo chuyến hiển thị form cơ bản")
	public void case_TR_011() {
		loginAdmin();
		openTripsPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-trips-btn-create']")))
				.click();
		Assert.assertTrue(
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("vehicleId"))).isDisplayed());

		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("departureLocationId")))
				.isDisplayed());

		Assert.assertTrue(
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("arrivalLocationId"))).isDisplayed());

		Assert.assertTrue(wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-trip-create-input-departure-date']")))
				.isDisplayed());

		Assert.assertTrue(wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-trip-create-input-departure-time']")))
				.isDisplayed());

		Assert.assertTrue(
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("basePrice"))).isDisplayed());

	}

	@Test(description = "TR-12 Tiêu đề trang tạo chuyến xe đúng")
	public void case_TR_012() {
		loginAdmin();
		openTripsPage();
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='admin-trips-btn-create']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-trips-btn-create']")))
				.click();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		Assert.assertEquals(driver.getTitle(), "Tạo Chuyến Xe Mới - BookingHub");

	}

	@Test(description = "TR-13 Gửi thông tin tạo chuyến khi chưa chọn phương tiện nút tạo chuyến disable")
	public void case_TR_013() {
		loginAdmin();
		openTripsPage();
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='admin-trips-btn-create']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-trips-btn-create']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("departureLocationId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("departureLocationId"))))
				.selectByVisibleText("Hải Phòng - Lê Chân");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("arrivalLocationId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("arrivalLocationId"))))
				.selectByVisibleText("Buôn Ma Thuột - Tân Lợi");
		Assert.assertFalse(wait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("[data-testid=\"admin-trip-create-submit\"]")))
				.isEnabled());

	}

	@Test(description = "TR-14 Mở modal sửa chuyến xe")
	public void case_TR_014() {
		loginAdmin();
		openTripsPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid^='admin-trips-btn-edit-']")))
				.click();
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-trips-modal-title']")))
				.getText().contains("Chỉnh sửa chuyến"));

	}

	@Test(description = "TR-15 Xóa chuyến xe")
	public void case_TR_015() {
		loginAdmin();
		openTripsPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid^='admin-trips-btn-delete-']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']")))
				.click();
		Assert.assertTrue(wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Xóa thành công')]")))
				.isDisplayed());

	}

	@Test(description = "TR-16 Sửa chuyến xe thành công")
	public void case_TR_016() {
		loginAdmin();
		openTripsPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(@data-testid,'btn-edit-')])[1]")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("departureTime"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("departureTime"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("departureTime"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("departureTime"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("departureTime")))
				.sendKeys("2026-04-29T09:00");
		By submitBtn = By.cssSelector("[data-testid='admin-trips-form-submit']");

		wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Cập nhật thành công')]")))
				.isDisplayed());

	}

	@Test(description = "TR-17 Tạo chuyến mới thành công")
	public void case_TR_017() {
		loginAdmin();
		openTripsPage();
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid='admin-trips-btn-create']")));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-trips-btn-create']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("vehicleId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("vehicleId"))))
				.selectByVisibleText("Xe 16 (Ghế ngồi)");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("departureLocationId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("departureLocationId"))))
				.selectByVisibleText("Hồ Chí Minh - Bình Tân");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("arrivalLocationId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("arrivalLocationId"))))
				.selectByVisibleText("Đà Nẵng - Thanh Khê");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("bulkCreate"))).click();

		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid='admin-trip-create-input-range-from']"))).click();

		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[contains(@class,'react-datepicker__day') and text()='16']")))
				.click();

		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid='admin-trip-create-input-range-to']"))).click();

		wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[contains(@class,'react-datepicker__day') and text()='19']")))
				.click();

		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid='admin-trip-create-button-create-range']"))).click();

		By submitBtn = By.cssSelector("[data-testid='admin-trip-create-submit']");

		wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Tạo chuyến thành công')]")))
				.isDisplayed());

	}

}