package tests.customer;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BookingPageTest extends CustomerBaseTest {

	private void searchTrip() {
		wait.until(ExpectedConditions.elementToBeClickable(By.id("departureLocationId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("departureLocationId"))))
				.selectByVisibleText("Hà Nội - Long Biên");

		wait.until(ExpectedConditions.elementToBeClickable(By.id("arrivalLocationId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.id("arrivalLocationId"))))
				.selectByVisibleText("Hải Phòng - Lê Chân");

		wait.until(ExpectedConditions.elementToBeClickable(By.id("departureDate"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".react-datepicker__day--016"))).click();

		By searchBtn = By.cssSelector("[data-testid='home-search-submit']");

		wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
	}

	private void openBooking() {
		wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[contains(.,'Đặt vé') or contains(.,'Chọn ghế') or contains(.,'Tiếp tục')]")))
				.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(.,'Đặt vé xe khách')]")));
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

		Assert.assertTrue(
				driver.findElement(By.cssSelector(".mb-4 > .font-bold")).getText().contains("Thông tin liên hệ"));

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

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".px-6"))).click();

		Assert.assertTrue(driver.findElement(By.name("customerName")).getAttribute("validationMessage").length() > 0);

	}

	@Test(description = "BK-07 Điền họ tên và gửi thông tin vẫn báo bắt buộc nhập trường khác")
	public void case_BK_007() {
		loginCustomer();
		searchTrip();
		openBooking();

		driver.findElement(By.name("customerName")).sendKeys("Nguyen Van A");

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".px-6"))).click();

		Assert.assertTrue(driver.findElement(By.name("customerPhone")).getAttribute("validationMessage").length() > 0);

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

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".grid:nth-child(3) .p-2"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".px-6"))).click();

		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-4 > .text-2xl")))
				.getText().contains("Đặt vé thành công"));

	}

}