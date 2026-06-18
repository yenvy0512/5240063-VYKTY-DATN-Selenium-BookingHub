package tests.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminReviewsPageTest extends AdminBaseTest {

	private void openReviewsPage() {

	    By reviewsMenu = By.linkText("Đánh giá");

	    wait.until(driver -> {
	        try {

	            WebElement element = driver.findElement(reviewsMenu);

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
	            ExpectedConditions.titleContains("Đánh giá"),
	            ExpectedConditions.urlContains("/reviews")
	    ));
	}

	@Test(description = "RV-01 Trang Quản lý Đánh giá hiển thị")
	public void case_RV_001() {
		loginAdmin();
		openReviewsPage();
		Assert.assertEquals(driver.getTitle(), "Quản lý Đánh giá - BookingHub");

	}

	@Test(description = "RV-02 Tiêu đề trang đánh giá hiển thị đúng")
	public void case_RV_002() {
		loginAdmin();
		openReviewsPage();
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-reviews-heading']")))
				.getText().contains("Quản lý Đánh giá"));

	}

	@Test(description = "RV-03 Nút tìm kiếm hiển thị")
	public void case_RV_003() {
		loginAdmin();
		openReviewsPage();
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-reviews-search-submit']")))
				.isDisplayed());

	}

	@Test(description = "RV-04 Nhập thông tin và tìm kiếm đánh giá")
	public void case_RV_004() {
		loginAdmin();
		openReviewsPage();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-reviews-search-input']"))).clear();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-reviews-search-input']")))
				.sendKeys("BK202510220008");
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-reviews-search-submit']")))
				.click();
	}

	@Test(description = "RV-05 Nhập thông tin tìm kiếm không tồn tại")
	public void case_RV_005() {
		loginAdmin();
		openReviewsPage();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-reviews-search-input']"))).clear();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-reviews-search-input']")))
				.sendKeys("ZXCSAWR");
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-reviews-search-submit']")))
				.click();
		Assert.assertTrue(wait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.cssSelector("[data-testid='admin-reviews-table']"), "Không có dữ liệu")));

	}

	@Test(description = "RV-06 Xóa đánh giá")
	public void case_RV_006() {
		loginAdmin();
		openReviewsPage();
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid^='admin-reviews-btn-delete-']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']")))
				.click();
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Xóa đánh giá thành công')]")))
				.isDisplayed());
	}

}