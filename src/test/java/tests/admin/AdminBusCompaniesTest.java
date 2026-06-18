package tests.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminBusCompaniesTest extends AdminBaseTest {

	private void openBusCompaniesPage() {

	    By busCompaniesMenu = By.linkText("Nhà xe");

	    wait.until(driver -> {
	        try {

	            WebElement element = driver.findElement(busCompaniesMenu);

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
	            ExpectedConditions.titleContains("Nhà xe"),
	            ExpectedConditions.urlContains("/bus-companies")
	    ));
	}

	@Test(description = "BC-01 Trang Quản lý Nhà xe hiển thị")
	public void case_BC_001() {
		loginSuperAdmin();
		openBusCompaniesPage();
		wait.until(ExpectedConditions.titleContains("Quản lý Nhà xe"));

		Assert.assertTrue(driver.getTitle().contains("Quản lý Nhà xe"));

	}

	@Test(description = "BC-02 Tiêu đề trang nhà xe đúng")
	public void case_BC_002() {
		loginSuperAdmin();
		openBusCompaniesPage();
		wait.until(ExpectedConditions.titleContains("Quản lý Nhà xe"));

		Assert.assertTrue(driver.getTitle().contains("Quản lý Nhà xe"));

	}

	@Test(description = "BC-03 Bảng có các cột thông tin nhà xe")
	public void case_BC_003() {
		loginSuperAdmin();
		openBusCompaniesPage();
		WebElement table = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bus-companies-table']")));

		List<WebElement> ths = table.findElements(By.tagName("th"));

		List<String> actualHeaders = ths.stream().map(e -> e.getText().trim().toLowerCase()).toList();

		String[] expectedHeaders = { "tên nhà xe", "số điện thoại", "địa chỉ" };

		for (String header : expectedHeaders) {
			Assert.assertTrue(actualHeaders.contains(header));
		}
	}

	@Test(description = "BC-04 Nút Thêm nhà xe hiển thị với super admin")
	public void case_BC_004() {
		loginSuperAdmin();
		openBusCompaniesPage();
		Assert.assertFalse(driver.findElements(By.cssSelector("[data-testid='admin-bus-companies-btn-add']")).isEmpty(),
				"Missing element");
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("[data-testid='admin-bus-companies-btn-add']")))
				.getText().contains("Thêm mới"));

	}

	@Test(description = "BC-05 Thêm nhà xe không nhập thông tin và lưu lại")
	public void case_BC_005() {
		loginSuperAdmin();
		openBusCompaniesPage();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid=\"admin-bus-companies-btn-add\"]")));
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid=\"admin-bus-companies-btn-add\"]"))).click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid=\"admin-bus-companies-form-submit\"]"))).click();
		WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name")));

		Assert.assertNotNull(input.getAttribute("required"));

	}

	@Test(description = "BC-06 Thêm nhà xe nhập đầy đủ thông tin và lưu lại")
	public void case_BC_006() {
		loginSuperAdmin();
		openBusCompaniesPage();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid=\"admin-bus-companies-btn-add\"]")));
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid=\"admin-bus-companies-btn-add\"]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("name"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).sendKeys("Test");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))).sendKeys("ackckkc@test.com");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phone"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("phone"))).sendKeys("0333333333");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("address"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("address"))).sendKeys("Test");
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid=\"admin-bus-companies-form-submit\"]"))).click();
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Tạo nhà xe thành công')]")))
				.isDisplayed());
	}

	@Test(description = "BC-07 Cập nhật nhà xe")
	public void case_BC_007() {
		loginSuperAdmin();
		openBusCompaniesPage();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid^='admin-bus-companies-btn-edit-']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.name("name"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).clear();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("name"))).sendKeys("Test1");
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid=\"admin-bus-companies-form-submit\"]"))).click();
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Cập nhật thành công')]")))
				.isDisplayed());

	}

	@Test(description = "BC-08 Xóa nhà xe")
	public void case_BC_008() {
		loginSuperAdmin();
		openBusCompaniesPage();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid^='admin-bus-companies-btn-delete-']"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']")))
				.click();
		Assert.assertTrue(wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Xóa thành công')]")))
				.isDisplayed());

	}

	@Test(description = "BC-09 Kiểm tra heading trang nhà xe")
	public void case_BC_009() {
		loginSuperAdmin();
		openBusCompaniesPage();
		WebElement heading = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bus-companies-heading']")));

		Assert.assertTrue(heading.getText().contains("Quản lý Nhà xe"));

	}

	@Test(description = "BC-10 Kiểm tra hiển thị ô tìm kiếm nhà xe")
	public void case_BC_010() {
		loginSuperAdmin();
		openBusCompaniesPage();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid='admin-bus-companies-search-input']"))).click();
		Assert.assertFalse(
				driver.findElements(By.cssSelector("[data-testid='admin-bus-companies-search-input']")).isEmpty(),
				"Missing element");

	}

	@Test(description = "BC-11 Tìm kiếm nhà xe theo từ khóa")
	public void case_BC_011() {
		loginSuperAdmin();
		openBusCompaniesPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".p-6"))).click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid='admin-bus-companies-search-input']"))).click();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bus-companies-search-input']")))
				.clear();
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bus-companies-search-input']")))
				.sendKeys("Test");
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid='admin-bus-companies-search-submit']"))).click();

	}

}
