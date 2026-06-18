package tests.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminVASTest extends AdminBaseTest {

	private void openVasPage() {

	    By serviceMenu = By.xpath(
	            "//button[.//span[contains(normalize-space(.),'Dịch vụ')]]"
	    );

	    wait.until(driver -> {
	        try {

	            WebElement menu = driver.findElement(serviceMenu);

	            if (menu.isDisplayed() && menu.isEnabled()) {
	                menu.click();
	                return true;
	            }

	            return false;

	        } catch (StaleElementReferenceException e) {
	            return false;
	        }
	    });

	    By vasSubMenu = By.linkText("Dịch vụ bổ sung");

	    wait.until(driver -> {
	        try {

	            WebElement element = driver.findElement(vasSubMenu);

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
	            ExpectedConditions.titleContains("Dịch vụ"),
	            ExpectedConditions.urlContains("/vas")
	    ));
	}

	@Test(description = "VS-01 Trang Quản lý Dịch vụ hiển thị")
	public void case_VS_001() {
		loginAdmin();
		openVasPage();
		wait.until(ExpectedConditions.titleIs("Quản lý Dịch vụ - BookingHub"));
		Assert.assertEquals(driver.getTitle(), "Quản lý Dịch vụ - BookingHub");

	}

	@Test(description = "VS-02 Tiêu đề trang quản lý dịch vụ hiển thị đúng")
	public void case_VS_002() {
		loginAdmin();
		openVasPage();
		wait.until(ExpectedConditions.titleIs("Quản lý Dịch vụ - BookingHub"));
		Assert.assertEquals(driver.getTitle(), "Quản lý Dịch vụ - BookingHub");

	}

	@Test(description = "VS-03 Heading Quản lý dịch vụ hiển thị đúng")
	public void case_VS_003() {
		loginAdmin();
		openVasPage();
		WebElement heading = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vas-heading']")));

		Assert.assertTrue(heading.getText().contains("Quản lý dịch vụ bổ sung"));
	}

	@Test(description = "VS-04 Nút thêm dịch vụ hiển thị")
	public void case_VS_004() {
		loginAdmin();
		openVasPage();
		WebElement btn = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vas-btn-add']")));

		Assert.assertTrue(btn.getText().contains("+ Thêm dịch vụ mới"));

	}

	@Test(description = "VS-05 Tìm kiếm dịch vụ theo từ khóa")
	public void case_VS_005() {
		loginAdmin();
		openVasPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-vas-search-input']")))
				.click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vas-search-input']")))
				.clear();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vas-search-input']")))
				.sendKeys("Aqua");
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-vas-search-submit']")))
				.click();
		Assert.assertTrue(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".border-t > .font-medium")))
						.getText().contains("Aqua"));

	}

	@Test(description = "VS-07 Thêm mới dịch vụ không nhập thông tin")
	public void case_VS_007() {
		loginAdmin();
		openVasPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-vas-btn-add']")))
				.click();
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid='admin-vas-form'] button[type='submit']"))).click();
		WebElement input = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vas-input-name']")));
		Assert.assertFalse(input.getAttribute("validationMessage").isEmpty());

	}

	@Test(description = "VS-08 Thêm mới dịch vụ thành công")
	public void case_VS_008() {
		loginAdmin();
		openVasPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-vas-btn-add']")))
				.click();
		WebElement inputName = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vas-input-name']")));
		inputName.clear();
		inputName.sendKeys("Aqua");
		Select select = new Select(wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vas-input-type']"))));

		select.selectByVisibleText("Nước uống");
		WebElement inputPrice = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vas-input-price']")));

		inputPrice.clear();
		inputPrice.sendKeys("1000");
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid='admin-vas-form'] button[type='submit']"))).click();
		Assert.assertTrue(wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Tạo dịch vụ thành công!')]")))
				.isDisplayed());

	}

	@Test(description = "VS-09 Chỉnh sửa dịch vụ")
	public void case_VS_009() {
		loginAdmin();
		openVasPage();
//		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".border-t:nth-child(1) .text-blue-600")))
//				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid^='admin-vas-btn-edit-']")))
				.click();
		WebElement inputPrice = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vas-input-price']")));

		inputPrice.clear();
		inputPrice.sendKeys("10000");
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid='admin-vas-form'] button[type='submit']"))).click();
		Assert.assertTrue(wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Cập nhật dịch vụ thành công!')]")))
				.isDisplayed());

	}

	@Test(description = "VS-10 Xóa dịch vụ thành công")
	public void case_VS_010() {
		loginAdmin();
		openVasPage();
//		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".border-t:nth-child(1) .text-red-600")))
//				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid^='admin-vas-btn-delete-']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']")))
				.click();
		Assert.assertTrue(wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Xóa dịch vụ thành công!')]")))
				.isDisplayed());

	}

	@Test(description = "VS-11 Chỉnh sửa dịch vụ xóa thông tin bắt buộc nhập báo lỗi")
	public void case_VS_011() {
		loginAdmin();
		openVasPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid^='admin-vas-btn-edit-']")))
				.click();
		WebElement inputPrice = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vas-input-price']")));

		inputPrice.clear();
		inputPrice.sendKeys("");
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid='admin-vas-form'] button[type='submit']"))).click();
		WebElement input = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vas-input-price']")));
		Assert.assertFalse(input.getAttribute("validationMessage").isEmpty());

	}

}
