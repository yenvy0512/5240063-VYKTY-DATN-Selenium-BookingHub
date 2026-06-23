package tests.customer;

import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingPageTest extends CustomerBaseTest {

	private static final By SUBMIT_BTN = By.cssSelector("[data-testid='booking-submit']");

	private void searchTrip() {
		wait.until(ExpectedConditions.elementToBeClickable(By.id("departureLocationId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("departureLocationId"))))
				.selectByVisibleText("Hà Nội - Long Biên");

		wait.until(ExpectedConditions.elementToBeClickable(By.id("arrivalLocationId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("arrivalLocationId"))))
				.selectByVisibleText("Hải Phòng - Lê Chân");

		wait.until(ExpectedConditions.elementToBeClickable(By.id("departureDate"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".react-datepicker__day--020"))).click();

		By searchBtn = By.cssSelector("[data-testid='home-search-submit']");

		wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
	}

	private void openBooking() {
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[contains(.,'Đặt vé') or contains(.,'Chọn ghế') or contains(.,'Tiếp tục')]")))
				.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(.,'Đặt vé xe khách')]")));
	}

	private void prepareBookingPage() {
		loginCustomer();
		searchTrip();
		openBooking();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-testid^='seat-']")));
	}

	private String getSubmitButtonText() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_BTN)).getText();
	}

	private String extractPriceDigits(String text) {
		if (text == null) {
			return "";
		}
		return text.replaceAll("[^0-9]", "");
	}

	private int getSeatPriceFromTitle(WebElement seat) {
		String title = seat.getAttribute("title");
		String digits = extractPriceDigits(title);
		return digits.isEmpty() ? 0 : Integer.parseInt(digits);
	}

	private WebElement findAvailableSeat(String preferredSeatNumber) {
		if (preferredSeatNumber != null) {
			By preferredSeat = By.cssSelector("[data-testid='seat-" + preferredSeatNumber + "']");
			List<WebElement> preferredSeats = driver.findElements(preferredSeat);
			if (!preferredSeats.isEmpty()) {
				WebElement seat = preferredSeats.get(0);
				if (seat.isEnabled() && !seat.getText().contains("Đã đặt")) {
					return seat;
				}
			}
		}

		for (WebElement seat : driver.findElements(By.cssSelector("[data-testid^='seat-']"))) {
			if (seat.isEnabled() && !seat.getText().contains("Đã đặt")) {
				return seat;
			}
		}

		return null;
	}

	private WebElement findAnotherAvailableSeat(String excludedSeatNumber) {
		for (WebElement seat : driver.findElements(By.cssSelector("[data-testid^='seat-']"))) {
			if (!seat.isEnabled() || seat.getText().contains("Đã đặt")) {
				continue;
			}

			String testId = seat.getAttribute("data-testid");
			if (excludedSeatNumber != null && ("seat-" + excludedSeatNumber).equals(testId)) {
				continue;
			}

			return seat;
		}

		return null;
	}

	private WebElement findBookedSeat() {
		for (WebElement seat : driver.findElements(By.cssSelector("[data-testid^='seat-']"))) {
			if (seat.getText().contains("Đã đặt")) {
				return seat;
			}
		}

		return null;
	}

	private WebElement waitForAvailableSeat(String preferredSeatNumber) {
		return wait.until(driver -> {
			WebElement seat = findAvailableSeat(preferredSeatNumber);
			return seat != null && seat.isDisplayed() && seat.isEnabled() ? seat : null;
		});
	}

	private WebElement waitForAnotherAvailableSeat(String excludedSeatNumber) {
		return wait.until(driver -> {
			WebElement seat = findAnotherAvailableSeat(excludedSeatNumber);
			return seat != null && seat.isDisplayed() && seat.isEnabled() ? seat : null;
		});
	}

	@Test(description = "BK-01 Trang đặt vé hiển thị và có tiêu đề")
	public void case_BK_001() {
		loginCustomer();
		searchTrip();
		openBooking();

		Assert.assertTrue(driver.findElement(By.xpath("//h1[contains(.,'Đặt vé xe khách')]")).getText()
				.contains("Đặt vé xe khách"));

	}

	@Test(description = "BK-03 Khi ở trang booking có form đặt vé")
	public void case_BK_003() {
		loginCustomer();
		searchTrip();
		openBooking();

		Assert.assertTrue(wait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.cssSelector("[data-testid='booking-customer-info-heading']"), "Thông tin liên hệ")));

	}

	@Test(description = "BK-04 Form có hiển thị các trường thông tin")
	public void case_BK_004() {
		loginCustomer();
		searchTrip();
		openBooking();

		Assert.assertTrue(
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("customerName"))).isDisplayed());

		Assert.assertTrue(
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("customerPhone"))).isDisplayed());

		Assert.assertTrue(
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("customerEmail"))).isDisplayed());

		Assert.assertTrue(
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("paymentMethod"))).isDisplayed());

	}

	@Test(description = "BK-06 Gửi thông tin trống có thể hiện thông báo lỗi")
	public void case_BK_006() {
		loginCustomer();
		searchTrip();
		openBooking();

		By seatA1 = By.cssSelector("[data-testid='seat-A1']");
		By nameInputBy = By.name("customerName");
		By submitBtn = By.cssSelector("[data-testid='booking-submit']");

		wait.until(ExpectedConditions.elementToBeClickable(seatA1)).click();

		WebElement nameInput = wait.until(ExpectedConditions.presenceOfElementLocated(nameInputBy));

		nameInput.clear();
		nameInput.sendKeys("");

		wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;

		Boolean isValid = (Boolean) js.executeScript("return arguments[0].checkValidity();", nameInput);

		Assert.assertFalse(isValid);

	}

	@Test(description = "BK-07 Điền họ tên và gửi thông tin vẫn báo bắt buộc nhập trường khác")
	public void case_BK_007() {
		loginCustomer();
		searchTrip();
		openBooking();

		By seatA1 = By.cssSelector("[data-testid='seat-A1']");
		By phoneInputBy = By.name("customerPhone");
		By submitBtn = By.cssSelector("[data-testid='booking-submit']");

		wait.until(ExpectedConditions.elementToBeClickable(seatA1)).click();

		WebElement phoneInput = wait.until(ExpectedConditions.presenceOfElementLocated(phoneInputBy));

		phoneInput.clear();
		phoneInput.sendKeys("");

		wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();

		JavascriptExecutor js = (JavascriptExecutor) driver;

		Boolean isValid = (Boolean) js.executeScript("return arguments[0].checkValidity();", phoneInput);

		Assert.assertFalse(isValid);

	}

	@Test(description = "BK-08 Nút đặt vé hiển thị")
	public void case_BK_008() {
		loginCustomer();
		searchTrip();
		openBooking();

		By btn = By.cssSelector("[data-testid='booking-submit']");

		Assert.assertTrue(
				wait.until(ExpectedConditions.visibilityOfElementLocated(btn)).getText().contains("Vui lòng chọn ghế"));

	}

	@Test(description = "BK-09 Đặt vé thành công")
	public void case_BK_009() {
		loginCustomer();
		searchTrip();
		openBooking();

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='seat-1']"))).click();

		By submitBtn = By.cssSelector("[data-testid='booking-submit']");

		wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();

		By toast = By.xpath("//*[contains(text(),'Đặt vé thành công')]");

		wait.until(ExpectedConditions.presenceOfElementLocated(toast));

		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(toast)).isDisplayed());

	}

	@Test(description = "BK-10 Chọn ghế cập nhật tổng tiền trên nút đặt vé")
	public void case_BK_010() {
		prepareBookingPage();

		Assert.assertTrue(getSubmitButtonText().contains("Vui lòng chọn ghế"));

		WebElement seat = waitForAvailableSeat("A1");
		int seatPrice = getSeatPriceFromTitle(seat);
		Assert.assertTrue(seatPrice > 0, "Không đọc được giá ghế từ title");

		seat.click();

		wait.until(ExpectedConditions.textMatches(SUBMIT_BTN, Pattern.compile(".*Đặt 1 ghế.*")));

		String submitText = getSubmitButtonText();
		Assert.assertTrue(submitText.contains("Đặt 1 ghế"));
		Assert.assertEquals(extractPriceDigits(submitText), String.valueOf(seatPrice));
	}

	@Test(description = "BK-11 Chọn nhiều ghế cập nhật đúng tổng tiền")
	public void case_BK_011() {
		prepareBookingPage();

		WebElement firstSeat = waitForAvailableSeat("A1");
		String firstSeatId = firstSeat.getAttribute("data-testid");
		String firstSeatNumber = firstSeatId.replace("seat-", "");
		int firstSeatPrice = getSeatPriceFromTitle(firstSeat);

		WebElement secondSeat = waitForAnotherAvailableSeat(firstSeatNumber);
		int secondSeatPrice = getSeatPriceFromTitle(secondSeat);

		firstSeat.click();
		secondSeat.click();

		int expectedTotal = firstSeatPrice + secondSeatPrice;

		wait.until(ExpectedConditions.textMatches(SUBMIT_BTN, Pattern.compile(".*Đặt 2 ghế.*")));

		String submitText = getSubmitButtonText();
		Assert.assertTrue(submitText.contains("Đặt 2 ghế"));
		Assert.assertEquals(extractPriceDigits(submitText), String.valueOf(expectedTotal));
	}

	@Test(description = "BK-12 Bỏ chọn ghế quay lại trạng thái chưa chọn")
	public void case_BK_012() {
		prepareBookingPage();

		WebElement seat = waitForAvailableSeat("A1");
		seat.click();

		wait.until(ExpectedConditions.textToBePresentInElementLocated(SUBMIT_BTN, "Đặt 1 ghế"));

		seat.click();

		wait.until(ExpectedConditions.textToBePresentInElementLocated(SUBMIT_BTN, "Vui lòng chọn ghế"));
		Assert.assertTrue(getSubmitButtonText().contains("Vui lòng chọn ghế"));
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),'Chưa chọn ghế nào')]")).isEmpty());
	}

	@Test(description = "BK-13 Ghế đã đặt không chọn được")
	public void case_BK_013() {
		prepareBookingPage();

		WebElement bookedSeat = findBookedSeat();
		Assert.assertNotNull(bookedSeat, "Không tìm thấy ghế đã đặt trên sơ đồ — cần dữ liệu chuyến có ghế đã chọn");

		Assert.assertFalse(bookedSeat.isEnabled());
		Assert.assertTrue(bookedSeat.getText().contains("Đã đặt"));
		Assert.assertTrue(bookedSeat.getAttribute("title").contains("Đã đặt"));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", bookedSeat);

		Assert.assertTrue(getSubmitButtonText().contains("Vui lòng chọn ghế"));
		Assert.assertFalse(bookedSeat.getText().contains("Chọn"));
	}

}