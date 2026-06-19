package tests.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminTripsPageTest extends AdminBaseTest {

	private void openTripsPage() {

	    By tripsMenu = By.linkText("Quản lý chuyến");

	    wait.until(ExpectedConditions.presenceOfElementLocated(tripsMenu));

	    wait.until(driver -> {
	        try {
	            WebElement element = driver.findElement(tripsMenu);

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
	            ExpectedConditions.titleContains("Chuyến"),
	            ExpectedConditions.urlContains("/trips")
	    ));
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

		wait.until(driver -> heading.getText().contains("Quản lý Chuyến xe"));

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

		WebElement departure = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.name("departureLocationId")));

		Assert.assertTrue(departure.isDisplayed());
		Assert.assertTrue(driver.findElement(By.name("arrivalLocationId")).isDisplayed());

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
		By searchInput = By.cssSelector("[data-testid='admin-trips-search-input']");
		By searchBtn = By.cssSelector("[data-testid='admin-trips-search-submit']");

		WebElement input = wait.until(ExpectedConditions.elementToBeClickable(searchInput));

		input.clear();
		input.sendKeys("Hải Phòng");

		wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();

	}

	@Test(description = "TR-11 Trang tạo chuyến hiển thị form cơ bản")
	public void case_TR_011() {
		loginAdmin();
		openTripsPage();
		By createBtn = By.cssSelector("[data-testid='admin-trips-btn-create']");

		wait.until(ExpectedConditions.elementToBeClickable(createBtn)).click();

		wait.until(driver -> {
			return driver.findElement(By.name("vehicleId")).isDisplayed()
					&& driver.findElement(By.name("departureLocationId")).isDisplayed()
					&& driver.findElement(By.name("arrivalLocationId")).isDisplayed()
					&& driver.findElement(By.name("departureDate")).isDisplayed()
					&& driver.findElement(By.name("departureTime")).isDisplayed()
					&& driver.findElement(By.name("basePrice")).isDisplayed();
		});

	}

	@Test(description = "TR-12 Tiêu đề trang tạo chuyến xe đúng")
	public void case_TR_012() {
		loginAdmin();
		openTripsPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-trips-btn-create']")))
				.click();

		wait.until(ExpectedConditions.titleIs("Tạo Chuyến Xe Mới - BookingHub"));

		Assert.assertEquals(driver.getTitle(), "Tạo Chuyến Xe Mới - BookingHub");

	}

	@Test(description = "TR-13 Gửi thông tin tạo chuyến khi chưa chọn phương tiện nút tạo chuyến disable")
	public void case_TR_013() {
		loginAdmin();
		openTripsPage();
		By createBtn = By.cssSelector("[data-testid='admin-trips-btn-create']");
		By departureSelect = By.name("departureLocationId");
		By arrivalSelect = By.name("arrivalLocationId");
		By submitBtn = By.cssSelector("[data-testid='admin-trip-create-submit']");

		wait.until(ExpectedConditions.elementToBeClickable(createBtn)).click();

		WebElement departure = wait.until(ExpectedConditions.elementToBeClickable(departureSelect));
		WebElement arrival = wait.until(ExpectedConditions.elementToBeClickable(arrivalSelect));

		wait.until(driver -> new Select(departure).getOptions().size() > 1);
		wait.until(driver -> new Select(arrival).getOptions().size() > 1);

		new Select(departure).selectByVisibleText("Hải Phòng - Lê Chân");
		new Select(arrival).selectByVisibleText("Buôn Ma Thuột - Tân Lợi");

		WebElement submit = wait.until(ExpectedConditions.visibilityOfElementLocated(submitBtn));

		wait.until(driver -> !submit.isEnabled());

		Assert.assertFalse(submit.isEnabled());

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
		By deleteBtn = By.cssSelector("[data-testid^='admin-trips-btn-delete-']");
		By confirmBtn = By.cssSelector("[data-testid='confirm-modal-confirm']");
		By toast = By.xpath("//*[contains(text(),'Xóa thành công')]");

		wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(confirmBtn));
		wait.until(ExpectedConditions.elementToBeClickable(confirmBtn)).click();

		wait.until(ExpectedConditions.presenceOfElementLocated(toast));

		WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(toast));

		Assert.assertTrue(success.isDisplayed());

	}

	@Test(description = "TR-16 Sửa chuyến xe thành công")
	public void case_TR_016() {
		loginAdmin();
		openTripsPage();
		By editBtn = By.xpath("(//*[contains(@data-testid,'btn-edit-')])[1]");
		By priceInputBy = By.name("basePrice");
		By timeInputBy = By.name("departureTime");
		By submitBtn = By.cssSelector("[data-testid='admin-trips-form-submit']");
		By toast = By.xpath("//*[contains(text(),'Cập nhật thành công')]");

		wait.until(ExpectedConditions.elementToBeClickable(editBtn)).click();

		WebElement priceInput = wait.until(ExpectedConditions.elementToBeClickable(priceInputBy));
		priceInput.click();
		priceInput.clear();
		priceInput.sendKeys("200000");

		WebElement timeInput = wait.until(ExpectedConditions.elementToBeClickable(timeInputBy));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(
				"arguments[0].value = arguments[1];"
						+ "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));"
						+ "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
				timeInput, "2026-04-29T09:00");

		wait.until(driver -> {
			WebElement btn = driver.findElement(submitBtn);
			return btn.isEnabled();
		});

		wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();

		wait.until(ExpectedConditions.presenceOfElementLocated(toast));

		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(toast)).isDisplayed());

	}

	@Test(description = "TR-17 Tạo chuyến mới thành công")
	public void case_TR_017() {
		loginAdmin();
		openTripsPage();

		By createBtn = By.cssSelector("[data-testid='admin-trips-btn-create']");
		By vehicleBy = By.name("vehicleId");
		By departureBy = By.name("departureLocationId");
		By arrivalBy = By.name("arrivalLocationId");
		By bulkCreateBy = By.id("bulkCreate");
		By createRangeBtn = By.cssSelector("[data-testid='admin-trip-create-button-create-range']");
		By submitBtn = By.cssSelector("[data-testid='admin-trip-create-submit']");
		By toast = By.xpath("//*[contains(text(),'Tạo chuyến thành công')]");

		wait.until(ExpectedConditions.elementToBeClickable(createBtn)).click();

		WebElement vehicle = wait.until(ExpectedConditions.elementToBeClickable(vehicleBy));
		wait.until(driver -> new Select(vehicle).getOptions().size() > 1);
		new Select(vehicle).selectByVisibleText("Xe 16 (Ghế ngồi)");

		WebElement departure = wait.until(ExpectedConditions.elementToBeClickable(departureBy));
		wait.until(driver -> new Select(departure).getOptions().size() > 1);
		new Select(departure).selectByVisibleText("Hà Nội - Long Biên");

		WebElement arrival = wait.until(ExpectedConditions.elementToBeClickable(arrivalBy));
		wait.until(driver -> new Select(arrival).getOptions().size() > 1);
		new Select(arrival).selectByVisibleText("Hải Phòng - Lê Chân");

		wait.until(ExpectedConditions.elementToBeClickable(bulkCreateBy)).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("rangeFrom")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("rangeTo")));

		selectDate(By.name("rangeFrom"), 15, 7, 2026);
		selectDate(By.name("rangeTo"), 30, 7, 2026);

		wait.until(ExpectedConditions.elementToBeClickable(createRangeBtn)).click();

		wait.until(driver -> driver.findElements(By.cssSelector("[data-testid='trip-date-item']")).size() > 0);

		wait.until(driver -> driver.findElement(submitBtn).isEnabled());

		wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();

		wait.until(ExpectedConditions.presenceOfElementLocated(toast));

		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(toast)).isDisplayed());

	}

}