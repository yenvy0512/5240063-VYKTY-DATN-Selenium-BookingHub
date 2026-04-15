package tests.admin;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminUsersPageTest extends AdminBaseTest {

	private void openUsersPage() {
		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Quản lý người dùng"))).click();
		wait.until(ExpectedConditions.titleContains("Người dùng"));
	}

	@Test(description = "US-01 Trang Quản lý Người dùng hiển thị")
	public void case_US_001() {
		loginAdmin();
		openUsersPage();
		Assert.assertEquals(driver.getTitle(), "Quản lý Người dùng - BookingHub");

	}

	@Test(description = "US-02 Tiêu đề trang người dùng đúng")
	public void case_US_002() {
		loginAdmin();
		openUsersPage();
		Assert.assertEquals(driver.getTitle(), "Quản lý Người dùng - BookingHub");

	}

	@Test(description = "US-03 Heading Quản lý Người dùng hiển thị")
	public void case_US_003() {
		loginAdmin();
		openUsersPage();
		WebElement heading = wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-heading']")));

		Assert.assertTrue(heading.getText().contains("Quản lý Người dùng"));

	}

	@Test(description = "US-04 Nút Thêm người dùng hiển thị")
	public void case_US_004() {
		loginAdmin();
		openUsersPage();
		Assert.assertFalse(driver.findElements(By.cssSelector("[data-testid='admin-users-btn-add']")).isEmpty(),
				"Missing element");
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.presenceOfElementLocated(By.cssSelector("[data-testid='admin-users-btn-add']")))
				.getText().contains("Thêm người dùng"));

	}

	@Test(description = "US-05 Bảng người dùng hiển thị")
	public void case_US_005() {
		loginAdmin();
		openUsersPage();
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-table']")))
				.isDisplayed());

	}

	@Test(description = "US-06 Modal thêm người dùng hiển thị")
	public void case_US_006() {
		loginAdmin();
		openUsersPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-users-btn-add']")))
				.click();
		WebElement title = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-modal-title']")));

		Assert.assertTrue(title.getText().contains("Thêm người dùng"));

	}

	@Test(description = "US-07 Thêm người dùng mới thành công")
	public void case_US_007() {
		loginAdmin();
		openUsersPage();
		Date now = new Date();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-users-btn-add']")))
				.click();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-input-username']")))
				.sendKeys("test" + now.getTime());

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-input-email']")))
				.sendKeys("test" + now.getTime() + "@gmail.com");

		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-input-name']")))
				.sendKeys("test");

		new Select(wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-select-role']"))))
				.selectByVisibleText("company_admin");

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-input-password']")))
				.sendKeys("123456aA@");

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-users-btn-save']")))
				.click();

		wait.until(ExpectedConditions.textToBePresentInElementLocated(
				By.xpath("//*[contains(text(),'Tạo người dùng thành công')]"), "Tạo người dùng thành công"));

	}

	@Test(description = "US-08 Sửa người dùng")
	public void case_US_008() {
		loginAdmin();
		openUsersPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid^='admin-users-btn-edit-']")))
				.click();
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-input-name']")))
				.sendKeys("test1");
		By submitBtn = By.cssSelector("[data-testid='admin-users-btn-save']");
		wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Cập nhật người dùng thành công')]")))
				.isDisplayed());

	}

	@Test(description = "US-09 Xóa người dùng")
	public void case_US_009() {
		loginAdmin();
		openUsersPage();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid^='admin-users-btn-delete-']")))
				.click();
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']")))
				.click();
		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Xóa người dùng thành công')]")))
				.isDisplayed());

	}

	@Test(description = "US-10 Thêm mới người dùng không nhập mật khẩu báo lỗi")
	public void case_US_010() {
		loginAdmin();
		openUsersPage();

		Date now = new Date();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-input-username']")))
				.sendKeys("test" + now.getTime());

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-input-email']")))
				.sendKeys("test" + now.getTime() + "@gmail.com");

		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-input-name']")))
				.sendKeys("test");

		new Select(wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-select-role']"))))
				.selectByVisibleText("company_admin");

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".px-4:nth-child(2)"))).click();
		Assert.assertFalse(
				driver.findElements(By.xpath("//*[contains(normalize-space(.),\"Mật khẩu phải có ít nhất 6 ký tự\")]"))
						.isEmpty());

	}

	@Test(description = "US-11 Thêm mới người dùng không nhập username báo lỗi")
	public void case_US_011() {
		loginAdmin();
		openUsersPage();

		Date now = new Date();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-input-email']")))
				.sendKeys("test" + now.getTime() + "@gmail.com");

		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-input-name']")))
				.sendKeys("test");

		new Select(wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-select-role']"))))
				.selectByVisibleText("company_admin");

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-input-password']")))
				.sendKeys("123456aA@");

		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Vui lòng nhập tên đăng nhập')]")))
				.isDisplayed());
	}

	@Test(description = "US-12 Thêm mới người dùng không chọn Role báo lỗi")
	public void case_US_012() {
		loginAdmin();
		openUsersPage();
		Date now = new Date();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-input-username']")))
				.sendKeys("test" + now.getTime());

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-input-email']")))
				.sendKeys("test" + now.getTime() + "@gmail.com");

		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-input-name']")))
				.sendKeys("test");

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-input-password']")))
				.sendKeys("123456aA@");

		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Vui lòng chọn role')]")))
				.isDisplayed());

	}

	@Test(description = "US-13 Thêm mới người dùng nhập trùng username báo lỗi")
	public void case_US_013() {
		loginAdmin();
		openUsersPage();

		Date now = new Date();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-input-username']")))
				.sendKeys("test");

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-input-email']")))
				.sendKeys("test" + now.getTime() + "@gmail.com");

		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-input-name']")))
				.sendKeys("test");

		new Select(wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-select-role']"))))
				.selectByVisibleText("company_admin");

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-input-password']")))
				.sendKeys("123456aA@");

		Assert.assertTrue(wait
				.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Tài khoản đã tồn tại')]")))
				.isDisplayed());

	}

}