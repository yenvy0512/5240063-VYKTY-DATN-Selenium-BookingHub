package tests.customer;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PaymentPageTest extends CustomerBaseTest {

	private void openPaymentPage() {
		WebElement myBooking = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/my-bookings']")));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", myBooking);

		By btn = By.xpath("(//div[.//span[contains(.,'Chờ thanh toán')]]//button[contains(.,'Thanh toán')])[1]");

		wait.until(ExpectedConditions.presenceOfElementLocated(btn));
		wait.until(ExpectedConditions.elementToBeClickable(btn)).click();
	}

	private void waitForToast(String message) {
		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(d -> d.findElement(By.tagName("body")).getText().contains(message));
	}

	@Test(description = "PM-01 Trang thanh toán hiển thị")
	public void case_PM_001() {
		loginCustomer();

		openPaymentPage();

		WebElement heading = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='customer-payment-heading']")));

		Assert.assertTrue(heading.getText().contains("Thanh toán"));
	}

	@Test(description = "PM-02 Tiêu đề trang chứa Thanh toán")
	public void case_PM_002() {
		loginCustomer();
		openPaymentPage();

		wait.until(ExpectedConditions.titleIs("Thanh toán - BookingHub"));
		Assert.assertEquals(driver.getTitle(), "Thanh toán - BookingHub");
	}

	@Test(description = "PM-03 Hiển thị heading Thanh toán")
	public void case_PM_003() {
		loginCustomer();
		openPaymentPage();

		WebElement heading = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='customer-payment-heading']")));

		Assert.assertTrue(heading.getText().contains("Thanh toán"));
	}

	@Test(description = "PM-07 Hiển thị phương thức thanh toán")
	public void case_PM_007() {
		loginCustomer();
		openPaymentPage();

		By paymentMethod = By.cssSelector("[data-testid='customer-payment-methods']");

		WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentMethod));
		Assert.assertTrue(el.isDisplayed());
	}

	@Test(description = "PM-08 Hiển thị thông tin vé")
	public void case_PM_008() {
		loginCustomer();
		openPaymentPage();

		By paymentMethod = By.cssSelector("[data-testid='customer-payment-booking-summary']");

		WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentMethod));
		Assert.assertTrue(el.isDisplayed());
	}

	@Test(description = "PM-09 Hiển thị nút Xác nhận thanh toán")
	public void case_PM_009() {
		loginCustomer();
		openPaymentPage();

		By paymentMethod = By.cssSelector("[data-testid='customer-payment-confirm']");

		WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentMethod));
		Assert.assertTrue(el.isDisplayed());
	}

	@Test(description = "PM-10 Thanh toán bằng phương thức tiền mặt và Xác nhận thanh toán thành công")
	public void case_PM_010() {
		loginCustomer();
		openPaymentPage();

		By confirmBtn = By.cssSelector("[data-testid='customer-payment-confirm']");

		WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(confirmBtn));
		btn.click();

		waitForToast("Đặt vé thành công! Vui lòng thanh toán khi lên xe");
	}

	@Test(description = "PM-11 Thanh toán bằng momo chuyển sang trang thanh toán của momo")
	public void case_PM_011() {
		loginCustomer();
		openPaymentPage();

		By momoOption = By.cssSelector("[data-testid='customer-payment-method-momo']");
		wait.until(ExpectedConditions.elementToBeClickable(momoOption)).click();

		By confirmBtn = By.cssSelector("[data-testid='customer-payment-confirm']");
		wait.until(ExpectedConditions.elementToBeClickable(confirmBtn)).click();

		wait.until(ExpectedConditions.or(ExpectedConditions.titleContains("MoMo"),
				ExpectedConditions.urlContains("momo")));

		Assert.assertTrue(driver.getTitle().contains("MoMo"));
	}

	@Test(description = "PM-12 Thanh toán bằng phương thức vnpay chuyển sang trang thanh toán vnpay")
	public void case_PM_012() {
		loginCustomer();
		openPaymentPage();

		By vnpayOption = By.cssSelector("[data-testid='customer-payment-method-vnpay']");
		wait.until(ExpectedConditions.elementToBeClickable(vnpayOption)).click();

		By confirmBtn = By.cssSelector("[data-testid='customer-payment-confirm']");
		wait.until(ExpectedConditions.elementToBeClickable(confirmBtn)).click();

		wait.until(ExpectedConditions.or(ExpectedConditions.urlContains("vnpay"),
				ExpectedConditions.titleContains("VNPAY")));

		Assert.assertTrue(driver.getCurrentUrl().contains("vnpay"));
	}

}
