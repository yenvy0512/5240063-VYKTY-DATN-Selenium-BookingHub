package tests.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminLocationsTest extends AdminBaseTest {

	private void openLocationPage() {
		By menuLocation = By.xpath("//button[.//span[contains(normalize-space(.),'Địa điểm')]]");

		WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(menuLocation));
		menu.click();

		By subMenu = By.xpath("//a[contains(normalize-space(.),'Quản lý địa điểm')]");

		wait.until(ExpectedConditions.visibilityOfElementLocated(subMenu));
		wait.until(ExpectedConditions.elementToBeClickable(subMenu)).click();

		wait.until(ExpectedConditions.titleContains("Địa điểm"));
	}

	@Test(description = "LC-01 Kiểm tra hiển thị trang quản lý địa điểm")
	public void case_LC_001() {
		loginSuperAdmin();
		openLocationPage();

		wait.until(ExpectedConditions.titleIs("Quản lý Địa điểm - BookingHub"));
		Assert.assertEquals(driver.getTitle(), "Quản lý Địa điểm - BookingHub");

	}

	@Test(description = "LC-02 Kiểm tra hiển thị tiêu đề trang địa điểm")
	public void case_LC_002() {
		loginSuperAdmin();
		openLocationPage();

		wait.until(ExpectedConditions.titleIs("Quản lý Địa điểm - BookingHub"));
		Assert.assertEquals(driver.getTitle(), "Quản lý Địa điểm - BookingHub");

	}

	@Test(description = "LC-03 Kiểm tra bảng có cột Thành phố")
	public void case_LC_003() {
		loginSuperAdmin();
		openLocationPage();

		By tableBy = By.cssSelector("[data-testid='admin-locations-table']");

		WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(tableBy));

		wait.until(d -> table.findElements(By.tagName("th")).size() > 0);

		List<WebElement> ths = table.findElements(By.tagName("th"));

		List<String> headers = ths.stream().map(e -> e.getText().replace("\n", " ").trim().toLowerCase()).toList();

		Assert.assertTrue(headers.stream().anyMatch(h -> h.contains("thành phố")), "Không tìm thấy cột 'Thành phố'");

	}

	@Test(description = "LC-04 Kiểm tra hiển thị nút thêm địa điểm")
	public void case_LC_004() {
		loginSuperAdmin();
		openLocationPage();

		By addBtn = By.cssSelector("[data-testid='admin-locations-btn-add']");

		WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(addBtn));

		wait.until(ExpectedConditions.textToBePresentInElement(btn, "Thêm mới"));

		Assert.assertTrue(btn.getText().contains("Thêm mới"));
	}

	@Test(description = "LC-05 Không nhập thông tin ấn lưu")
	public void case_LC_005() {
		loginSuperAdmin();
		openLocationPage();

		WebElement addBtn = wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-locations-btn-add']")));
		addBtn.click();

		WebElement city = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("city")));

		wait.until(driver -> {
			Boolean isValid = (Boolean) ((JavascriptExecutor) driver)
					.executeScript("return arguments[0].checkValidity();", city);
			return !isValid;
		});

		JavascriptExecutor js = (JavascriptExecutor) driver;
		Boolean valid = (Boolean) js.executeScript("return arguments[0].checkValidity();", city);
		Assert.assertFalse(valid);
	}

	@Test(description = "LC-06 Chỉ nhập thông tin thành phố ấn lưu lại")
	public void case_LC_006() {
		loginSuperAdmin();
		openLocationPage();

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-locations-btn-add']")))
				.click();

		WebElement city = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("city")));
		city.sendKeys("Thành phố test");

		By saveBtn = By.cssSelector("[data-testid='admin-locations-form-submit']");

		wait.until(ExpectedConditions.visibilityOfElementLocated(saveBtn));
		wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();

		WebElement district = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("district")));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		Boolean valid = (Boolean) js.executeScript("return arguments[0].checkValidity();", district);

		Assert.assertFalse(valid);

	}

	@Test(description = "LC-07 Nhập đầy đủ thông tin và lưu địa điểm")
	public void case_LC_007() {
		loginSuperAdmin();
		openLocationPage();

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-locations-btn-add']")))
				.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("city"))).sendKeys("Thành phố test");

		driver.findElement(By.name("district")).sendKeys("Test");
		driver.findElement(By.id("latitude")).sendKeys("21.028222");
		driver.findElement(By.id("longitude")).sendKeys("105.88888");

		By saveBtn = By.cssSelector("[data-testid='admin-locations-form-submit']");

		wait.until(ExpectedConditions.visibilityOfElementLocated(saveBtn));
		wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();

		WebElement toast = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Tạo địa điểm thành công!')]")));

		Assert.assertTrue(toast.isDisplayed());

	}

	@Test(description = "LC-08 Chỉnh sửa địa điểm")
	public void case_LC_008() {
		loginSuperAdmin();
		openLocationPage();

		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid^='admin-locations-btn-edit-']")))
				.click();

		WebElement district = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("district")));
		district.clear();
		district.sendKeys("Test1");

		By saveBtn = By.cssSelector("[data-testid='admin-locations-form-submit']");

		wait.until(ExpectedConditions.visibilityOfElementLocated(saveBtn));
		wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();

		WebElement toast = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Cập nhật thành công')]")));

		Assert.assertTrue(toast.isDisplayed());

	}

	@Test(description = "LC-09 Xóa địa điểm")
	public void case_LC_009() {
		loginSuperAdmin();
		openLocationPage();

		wait.until(
				ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid^='admin-locations-btn-delete-']")))
				.click();

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']")))
				.click();

		WebElement toast = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Xóa thành công')]")));

		Assert.assertTrue(toast.isDisplayed());

	}

	@Test(description = "LC-10 Heading Quản lý Địa điểm hiển thị")
	public void case_LC_010() {
		loginSuperAdmin();
		openLocationPage();

		WebElement title = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".text-2xl")));

		Assert.assertTrue(title.getText().contains("Quản lý Địa điểm"));

	}

	@Test(description = "LC-11 Ô tìm kiếm địa điểm hiển thị")
	public void case_LC_011() {
		loginSuperAdmin();
		openLocationPage();

		WebElement search = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-locations-search-input']")));

		Assert.assertTrue(search.isDisplayed());

	}

	@Test(description = "LC-12 Tìm kiếm địa điểm theo từ khóa")
	public void case_LC_012() {
		loginSuperAdmin();
		openLocationPage();

		WebElement search = wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid='admin-locations-search-input']")));

		search.sendKeys("Test");

		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid='admin-locations-search-submit']"))).click();

		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("table, .table, tbody")));

	}

}