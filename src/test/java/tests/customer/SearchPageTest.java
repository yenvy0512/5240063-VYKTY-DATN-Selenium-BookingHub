package tests.customer;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchPageTest extends CustomerBaseTest {

	private void goToSearchPage() {
		openCustomerHome();

		WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href='/search']")));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
	}

	private void waitForError(String message) {
		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(d -> d.findElement(By.tagName("body")).getText().contains(message));
	}

	@Test(description = "SR-01 Trang tìm chuyến hiển thị form tìm kiếm")
	public void case_SR_001() {
		goToSearchPage();

		WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-6")));

		Assert.assertTrue(heading.getText().contains("Tìm chuyến xe"));
	}

	@Test(description = "SR-03 Tiêu đề trang tìm chuyến đúng")
	public void case_SR_003() {
		goToSearchPage();

		wait.until(ExpectedConditions.titleIs("Tìm chuyến xe - BookingHub"));
		Assert.assertEquals(driver.getTitle(), "Tìm chuyến xe - BookingHub");

	}

	@Test(description = "SR-04 Heading Tìm chuyến xe hiển thị")
	public void case_SR_004() {
		goToSearchPage();

		WebElement heading = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='customer-search-heading']")));

		Assert.assertTrue(heading.isDisplayed());
	}

	@Test(description = "SR-05 Form tìm kiếm có đủ trường dữ liệu")
	public void case_SR_005() {
		goToSearchPage();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-departureLocationId")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-arrivalLocationId")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-departureDate")));

		Assert.assertTrue(true);
	}

	@Test(description = "SR-06 Nút tìm kiếm hiển thị")
	public void case_SR_006() {
		goToSearchPage();

		WebElement btn = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='search-submit']")));

		Assert.assertTrue(btn.getText().contains("Tìm kiếm"));

	}

	@Test(description = "SR-08 Bấm tìm kiếm khi chưa đủ điều kiện báo lỗi")
	public void case_SR_008() {
		goToSearchPage();

		WebElement btn = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='search-submit']")));

		btn.click();;
		
		waitForError("Vui lòng điền đầy đủ thông tin tìm kiếm");

	}

	@Test(description = "SR-09 Chỉ chọn điểm đi rồi tìm kiểm hiển thị lỗi thiếu thông tin")
	public void case_SR_009() {
		goToSearchPage();

		new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-departureLocationId"))))
				.selectByVisibleText("Hà Nội - Hoàng Mai");

		WebElement btn = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='search-submit']")));

		btn.click();

		waitForError("Vui lòng điền đầy đủ thông tin tìm kiếm");
	}

	@Test(description = "SR-10 Chỉ chọn điểm đến rồi tìm kiểm hiển thị lỗi thiếu thông tin")
	public void case_SR_010() {
		goToSearchPage();

		new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-arrivalLocationId"))))
				.selectByVisibleText("Hồ Chí Minh - Quận 1");

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".px-6"))).click();

		waitForError("Vui lòng điền đầy đủ thông tin tìm kiếm");
	}

	@Test(description = "SR-11 Chỉ chọn ngày đi hiển thị báo lỗi thiếu thông tin")
	public void case_SR_011() {
		goToSearchPage();

		wait.until(ExpectedConditions.elementToBeClickable(By.id("search-departureDate"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".react-datepicker__day--020"))).click();

		WebElement btn = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='search-submit']")));

		btn.click();

		waitForError("Vui lòng điền đầy đủ thông tin tìm kiếm");
	}

	@Test(description = "SR-12 Nhập đủ thông tin tìm kiếm thành công")
	public void case_SR_012() {
		goToSearchPage();

		new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-departureLocationId"))))
				.selectByVisibleText("Hà Nội - Long Biên");

		new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-arrivalLocationId"))))
				.selectByVisibleText("Hải Phòng - Lê Chân");

		wait.until(ExpectedConditions.elementToBeClickable(By.id("search-departureDate"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".react-datepicker__day--020"))).click();

		WebElement btn = wait
				.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='search-submit']")));

		btn.click();

		wait.until(ExpectedConditions.titleContains("Tìm chuyến xe"));

	}

}