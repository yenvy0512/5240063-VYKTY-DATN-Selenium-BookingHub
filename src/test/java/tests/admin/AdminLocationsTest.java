package tests.admin;

import base.AdminAuthBaseTest;
import config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.AdminLocationsPage;

import java.time.Duration;

/**
 * Test Địa điểm: trang, CRUD (Create/Update/Delete), Validation - gộp 1 file.
 */
public class AdminLocationsTest extends AdminAuthBaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrlAdmin() + "/locations");
    }

    // --- Trang ---
    @Test(description = "Trang Quản lý Địa điểm hiển thị")
    public void pageDisplayed() {
        AdminLocationsPage page = new AdminLocationsPage(getDriver());
        Assert.assertTrue(page.isPageDisplayed(), "Trang Quản lý Địa điểm phải hiển thị");
    }

    @Test(description = "Title trang địa điểm đúng")
    public void pageTitle() {
        AdminLocationsPage page = new AdminLocationsPage(getDriver());
        String title = page.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Địa điểm") || title.contains("BookingHub"),
                "Title phải chứa Địa điểm/BookingHub");
    }

    @Test(description = "Bảng có cột Thành phố")
    public void tableHasHeaderThanhPho() {
        AdminLocationsPage page = new AdminLocationsPage(getDriver());
        Assert.assertTrue(page.hasTableHeaderThanhPho(), "Bảng phải có cột Thành phố");
    }

    @Test(description = "Nút Thêm địa điểm hiển thị")
    public void addButtonDisplayed() {
        AdminLocationsPage page = new AdminLocationsPage(getDriver());
        Assert.assertTrue(page.isAddButtonDisplayed(), "Nút Thêm địa điểm phải hiển thị");
    }

    // --- Validation ---
    @Test(description = "Submit form trống modal vẫn mở")
    public void validation_submitEmpty_modalStaysOpen() {
        AdminLocationsPage page = new AdminLocationsPage(getDriver());
        page.clickAdd();
        Assert.assertTrue(page.isModalDisplayed(), "Modal phải mở");
        page.clickSubmitOnly();
        new WebDriverWait(getDriver(), Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-locations-modal']")));
        Assert.assertTrue(page.isModalDisplayed(), "Submit form trống phải không đóng modal");
    }

    @Test(description = "Chỉ điền Thành phố bỏ trống Quận modal vẫn mở")
    public void validation_partialFill_modalStaysOpen() {
        AdminLocationsPage page = new AdminLocationsPage(getDriver());
        page.clickAdd();
        page.fillForm("TP Test", "", "", null);
        page.clickSubmitOnly();
        new WebDriverWait(getDriver(), Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-locations-modal']")));
        Assert.assertTrue(page.isModalDisplayed(), "Thiếu Quận phải không đóng modal");
    }

    // --- CRUD ---
    @Test(description = "Create - Thêm địa điểm mới")
    public void crud_create() {
        AdminLocationsPage page = new AdminLocationsPage(getDriver());
        String city = "Test City " + System.currentTimeMillis();
        String district = "Quận Test";
        String address = "Địa chỉ test automation";

        page.clickAdd();
        Assert.assertTrue(page.isModalDisplayed(), "Modal thêm địa điểm phải mở");
        page.fillForm(city, district, address, "both");
        page.submitForm();

        Assert.assertTrue(page.tableContainsCity(city),
                "Sau khi thêm, bảng phải chứa thành phố: " + city);
    }

    @Test(description = "Update - Chỉnh sửa địa điểm", dependsOnMethods = "crud_create")
    public void crud_update() {
        AdminLocationsPage page = new AdminLocationsPage(getDriver());
        int countBefore = page.getTableRowCount();
        if (countBefore == 0) {
            Assert.fail("Cần có ít nhất 1 địa điểm để test Update.");
        }

        String updatedCity = "Updated City " + System.currentTimeMillis();
        page.clickEditFirstRow();
        Assert.assertTrue(page.isModalDisplayed(), "Modal sửa phải mở");
        page.fillForm(updatedCity, "Quận 1", "Địa chỉ cập nhật", "departure");
        page.submitForm();

        Assert.assertTrue(page.tableContainsCity(updatedCity),
                "Sau khi sửa, bảng phải chứa: " + updatedCity);
    }

    @Test(description = "Delete - Xóa địa điểm", dependsOnMethods = "crud_update")
    public void crud_delete() {
        AdminLocationsPage page = new AdminLocationsPage(getDriver());
        int countBefore = page.getTableRowCount();
        if (countBefore == 0) {
            Assert.fail("Cần có ít nhất 1 địa điểm để test Delete.");
        }

        page.clickDeleteFirstRow();
        page.confirmDelete();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        wait.until(d -> page.getTableRowCount() == countBefore - 1);
        Assert.assertEquals(page.getTableRowCount(), countBefore - 1,
                "Sau khi xóa, số dòng phải giảm 1");
    }
}
