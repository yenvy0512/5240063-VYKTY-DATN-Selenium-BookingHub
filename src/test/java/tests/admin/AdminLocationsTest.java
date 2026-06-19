package tests.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminLocationsTest extends AdminBaseTest {

	private void openLocationPage() {



		By menuLocation = By.xpath(

		"//button[.//span[contains(normalize-space(.),'Địa điểm')]]"

		);



		wait.until(driver -> {

		try {



		WebElement menu = driver.findElement(menuLocation);



		if (menu.isDisplayed() && menu.isEnabled()) {

		menu.click();

		return true;

		}



		return false;



		} catch (StaleElementReferenceException e) {

		return false;

		}

		});



		By subMenu = By.xpath(

		"//a[contains(normalize-space(.),'Quản lý địa điểm')]"

		);



		wait.until(driver -> {

		try {



		WebElement element = driver.findElement(subMenu);



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

		ExpectedConditions.titleContains("Địa điểm"),

		ExpectedConditions.urlContains("/locations")

		));

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

	    // 1. Đợi và click nút mở Form Add
	    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-locations-btn-add']")))
	            .click();

	    // 2. Nhập thông tin thành phố
	    WebElement city = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("city")));
	    city.sendKeys("Thành phố HCM");

	    // [QUAN TRỌNG] Đợi 1 giây để dropdown thành phố ẩn đi hoặc form hoàn tất render
	    try {
	        Thread.sleep(1000);
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }

	    // Khai báo nút lưu
	    By saveBtn = By.cssSelector("[data-testid='admin-locations-form-submit']");
	    WebElement saveButtonElement = wait.until(ExpectedConditions.presenceOfElementLocated(saveBtn));

	    // Khởi tạo JavascriptExecutor
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    // 3. Cuộn mượt xuống nút Lưu và click bằng JS
	    js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", saveButtonElement);
	    
	    // Thêm 500ms để trình duyệt cuộn xong rồi mới Click
	    try { Thread.sleep(500); } catch (Exception e) {}
	    js.executeScript("arguments[0].click();", saveButtonElement);

	    // [QUAN TRỌNG] Đợi thêm 500ms để hiệu ứng bấm nút Lưu kích hoạt validation của HTML5
	    try { Thread.sleep(500); } catch (Exception e) {}

	    // 4. Kiểm tra Validation ô district
	    WebElement district = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("district")));
	    Boolean valid = (Boolean) js.executeScript("return arguments[0].checkValidity();", district);

	    // Mong đợi valid là false vì ô district bắt buộc nhưng đang trống
	    Assert.assertFalse(valid);

	}

	@Test(description = "LC-07 Nhập đầy đủ thông tin và lưu địa điểm")
	public void case_LC_007() {
		loginSuperAdmin();
	    openLocationPage();

	    // 1. Mở Form thêm mới
	    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-locations-btn-add']")))
	            .click();

	    // 2. Nhập đầy đủ thông tin các trường
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("city"))).sendKeys("Thành phố test");
	    driver.findElement(By.name("district")).sendKeys("Test");
	    driver.findElement(By.id("latitude")).sendKeys("21.028222");
	    driver.findElement(By.id("longitude")).sendKeys("105.88888");

	    // Khai báo bộ định vị nút Lưu
	    By saveBtn = By.cssSelector("[data-testid='admin-locations-form-submit']");
	    
	    // Đợi nút Lưu xuất hiện trong DOM
	    WebElement saveButtonElement = wait.until(ExpectedConditions.presenceOfElementLocated(saveBtn));

	    // Khởi tạo JavascriptExecutor
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    // 3. Cuộn mượt màn hình đưa nút Lưu vào chính giữa tầm nhìn (tránh bị fixed footer che)
	    js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", saveButtonElement);
	    
	    // Đợi 500ms để trình duyệt hoàn tất hành động cuộn trang và đóng các gợi ý ẩn
	    try { 
	        Thread.sleep(500); 
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }

	    // Ép click trực tiếp bằng JavaScript để bypass hoàn toàn lỗi ElementClickIntercepted
	    js.executeScript("arguments[0].click();", saveButtonElement);

	    // 4. Kiểm tra thông báo Toast hiển thị thành công
	    WebElement toast = wait.until(ExpectedConditions
	            .visibilityOfElementLocated(By.xpath("//*[contains(normalize-space(.),'Tạo địa điểm thành công!')]")));

	    Assert.assertTrue(toast.isDisplayed());
	}

	

	@Test(description = "LC-08 Chỉnh sửa địa điểm")
	public void case_LC_008() {
		loginSuperAdmin();
	    openLocationPage();

	    // 1. Chờ và click vào nút chức năng (ví dụ: Sửa/Xóa/Xem chi tiết) công đoạn đầu
	    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid^='admin-locations-btn-edit-']")))
	            .click();

	    // 2. Chờ ô nhập liệu hiển thị và cập nhật thông tin dữ liệu mới
	    WebElement district = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("district")));
	    district.clear();
	    district.sendKeys("Test2"); // Thay đổi dữ liệu test tương ứng với mục đích của case

	    // Khai báo bộ định vị nút Lưu form
	    By saveBtn = By.cssSelector("[data-testid='admin-locations-form-submit']");
	    
	    // Đợi nút Lưu xuất hiện sẵn sàng trong cấu trúc DOM
	    WebElement saveButtonElement = wait.until(ExpectedConditions.presenceOfElementLocated(saveBtn));

	    // Khởi tạo JavascriptExecutor
	    JavascriptExecutor js = (JavascriptExecutor) driver;

	    // 3. Cuộn mượt màn hình đưa nút Lưu vào chính giữa tầm nhìn (tránh bị che khuất bởi footer/dropdown)
	    js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", saveButtonElement);
	    
	    // Đợi ngắn 500ms để trình duyệt thực hiện xong hành động cuộn và đóng các menu gợi ý ẩn nếu có
	    try { 
	        Thread.sleep(500); 
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }

	    // Ép click trực tiếp vào DOM bằng JavaScript để bypass hoàn toàn lỗi ElementClickIntercepted
	    js.executeScript("arguments[0].click();", saveButtonElement);

	    // 4. Chờ thông báo thành công (Toast message) hiển thị và xác nhận kết quả
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