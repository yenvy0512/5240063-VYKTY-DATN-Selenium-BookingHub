package tests.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminVehiclesPageTest extends AdminBaseTest {

	private void openVehiclesPage() {
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Phương tiện"))).click();
		wait.until(ExpectedConditions.titleContains("Xe"));
	}

	@Test(description = "VH-01 Trang Quản lý phương tiện hiển thị")
	public void case_VH_001() {
		loginAdmin();
		openVehiclesPage();
		Assert.assertEquals(driver.getTitle(), "Quản lý Xe - BookingHub");

	}

	@Test(description = "VH-02 Tiêu đề trang quản lý  phương tiện đúng")
	public void case_VH_002() {
		loginAdmin();
		openVehiclesPage();
		Assert.assertEquals(driver.getTitle(), "Quản lý Xe - BookingHub");

	}

	@Test(description = "VH-03 Bảng phương tiên có cột Tên xe và Biển số")
	public void case_VH_003() {
		loginAdmin();
		openVehiclesPage();

		WebElement table = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vehicles-table']")));

		List<WebElement> ths = table.findElements(By.tagName("th"));

		List<String> actualHeaders = ths.stream().map(e -> e.getText().trim().toLowerCase()).toList();

		String[] expectedHeaders = { "tên xe", "biển số" };

		for (String header : expectedHeaders) {
			Assert.assertTrue(actualHeaders.contains(header));
		}

	}

	@Test(description = "VH-04 Nút Thêm xe hiển thị")
	public void case_VH_004() {
		loginAdmin();
		openVehiclesPage();
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vehicles-btn-add']")))
				.isDisplayed(), "Missing add button");

	}

	@Test(description = "VH-06 Tìm kiếm với từ khóa")
	public void case_VH_006() {
		loginAdmin();
		openVehiclesPage();
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-vehicles-search-input']")))
				.click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vehicles-search-input']"))).clear();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vehicles-search-input']")))
				.sendKeys("xe 16");
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-vehicles-search-submit']")))
				.click();

	}

	@Test(description = "VH-07 Tìm kiếm chuỗi rỗng vẫn thực hiện được")
	public void case_VH_007() {
		loginAdmin();
		openVehiclesPage();
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-vehicles-search-submit']")))
				.click();

	}

	@Test(description = "VH-09 Gửi thông tin trống thông báo lỗi")
	public void case_VH_009() {
		loginAdmin();
		openVehiclesPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-vehicles-btn-add']")))
				.click();
		WebElement btn = wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-vehicles-form-submit']")));

		Assert.assertTrue(btn.isDisplayed());
		btn.click();
		WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("busType")));

		Boolean valid = (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].checkValidity();",
				el);

		Assert.assertFalse(valid);

	}

	@Test(description = "VH-10 Thêm mới phương tiện thành công")
	public void case_VH_010() {
		loginAdmin();
		openVehiclesPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-vehicles-btn-add']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("busName"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("busName"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("busName"))).sendKeys("Xe 16 chỗ");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("busType"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("busType"))))
				.selectByVisibleText("Limousine");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("licensePlate"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("licensePlate"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("licensePlate"))).sendKeys("30A12345");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("layoutTemplateId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("layoutTemplateId"))))
				.selectByVisibleText("Xe 16 chỗ (16 chỗ)");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("totalSeats"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("totalSeats"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("totalSeats"))).sendKeys("16");
		By submitBtn = By.cssSelector("[data-testid='admin-vehicles-form-submit']");

		wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Tạo xe thành công')]")))
				.isDisplayed());

	}

	@Test(description = "VH-11 Chỉnh sửa phương tiện thành công")
	public void case_VH_011() {
		loginAdmin();
		openVehiclesPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid^='admin-vehicles-btn-edit-']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("busName"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("busName"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("busName"))).sendKeys("Xe 16 chỗ 1");
		By submitBtn = By.cssSelector("[data-testid='admin-vehicles-form-submit']");

		wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Cập nhật thành công')]")))
				.isDisplayed());

	}

	@Test(description = "VH-12 Xóa phương tiện")
	public void case_VH_012() {
		loginAdmin();
		openVehiclesPage();
		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid^='admin-vehicles-btn-delete-']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']")))
				.click();
		Assert.assertTrue(wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Xóa thành công')]")))
				.isDisplayed());

	}

	@Test(description = "VH-13 Thêm phương tiện trùng biển số thông báo lỗi")
	public void case_VH_013() {
		loginAdmin();
		openVehiclesPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-vehicles-btn-add']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("busName"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("busName"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("busName"))).sendKeys("Xe 16 chỗ 1");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("busType"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("busType"))))
				.selectByVisibleText("Limousine");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("licensePlate"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("licensePlate"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("licensePlate"))).sendKeys("29K-50505");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("layoutTemplateId"))).click();
		new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("layoutTemplateId"))))
				.selectByVisibleText("Xe 16 chỗ (16 chỗ)");
		wait.until(ExpectedConditions.elementToBeClickable(By.name("totalSeats"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("totalSeats"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("totalSeats"))).sendKeys("16");
		By submitBtn = By.cssSelector("[data-testid='admin-vehicles-form-submit']");

		wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Biển số đã tồn tại!')]")))
				.isDisplayed());

	}

}