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
    @Test(description = "LC-01 Trang Quản lý Địa điểm hiển thị")
    public void pageDisplayed() {
        AdminLocationsPage page = new AdminLocationsPage(getDriver());
        Assert.assertTrue(page.isPageDisplayed(), "Trang Quản lý Địa điểm phải hiển thị");
    }

    @Test(description = "LC-02 Tiêu đề trang địa điểm đúng")
    public void pageTitle() {
        AdminLocationsPage page = new AdminLocationsPage(getDriver());
        String title = page.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Địa điểm") || title.contains("BookingHub"),
                "Tiêu đề phải chứa Địa điểm/BookingHub");
    }

    @Test(description = "LC-03 Bảng có cột Thành phố")
    public void tableHasHeaderThanhPho() {
        AdminLocationsPage page = new AdminLocationsPage(getDriver());
        Assert.assertTrue(page.hasTableHeaderThanhPho(), "Bảng phải có cột Thành phố");
    }

    @Test(description = "LC-04 Nút Thêm địa điểm hiển thị")
    public void addButtonDisplayed() {
        AdminLocationsPage page = new AdminLocationsPage(getDriver());
        Assert.assertTrue(page.isAddButtonDisplayed(), "Nút Thêm địa điểm phải hiển thị");
    }

    // --- Validation ---
    @Test(description = "LC-05 Gửi thông tin trống modal vẫn mở")
    public void validation_submitEmpty_modalStaysOpen() {
        AdminLocationsPage page = new AdminLocationsPage(getDriver());
        page.clickAdd();
        Assert.assertTrue(page.isModalDisplayed(), "Modal phải mở");
        page.clickSubmitOnly();
        new WebDriverWait(getDriver(), Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-locations-modal']")));
        Assert.assertTrue(page.isModalDisplayed(), "Gửi thông tin trống phải không đóng modal");
    }

    @Test(description = "LC-06 Chỉ điền Thành phố bỏ trống Quận modal vẫn mở")
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
    @Test(description = "LC-07 Create - Thêm địa điểm mới")
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

    @Test(description = "LC-08 Update - Chỉnh sửa địa điểm", dependsOnMethods = "crud_create")
    public void crud_update() {
        AdminLocationsPage page = new AdminLocationsPage(getDriver());
        int countBefore = page.getTableRowCount();
        if (countBefore == 0) {
            Assert.fail("Cần có ít nhất 1 địa điểm để test cập nhật.");
        }

        String updatedCity = "Cập nhật " + System.currentTimeMillis();
        page.clickEditFirstRow();
        Assert.assertTrue(page.isModalDisplayed(), "Modal sửa phải mở");
        page.fillForm(updatedCity, "Quận 1", "Địa chỉ cập nhật", "departure");
        page.submitForm();

        Assert.assertTrue(page.tableContainsCity(updatedCity),
                "Sau khi sửa, bảng phải chứa: " + updatedCity);
    }

    @Test(description = "LC-09 Delete - Xóa địa điểm", dependsOnMethods = "crud_update")
    public void crud_delete() {
        AdminLocationsPage page = new AdminLocationsPage(getDriver());
        int countBefore = page.getTableRowCount();
        if (countBefore == 0) {
            Assert.fail("Cần có ít nhất 1 địa điểm để test xóa.");
        }

        page.clickDeleteFirstRow();
        page.confirmDelete();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        wait.until(d -> page.getTableRowCount() == countBefore - 1);
        Assert.assertEquals(page.getTableRowCount(), countBefore - 1,
                "Sau khi xóa, số dòng phải giảm 1");
    }

    @Test(description = "LC-10 Heading Quản lý Địa điểm hiển thị")
    public void headingDisplayed() {
        AdminLocationsPage page = new AdminLocationsPage(getDriver());
        Assert.assertTrue(page.headingContainsQuanLyDiaDiem(), "Heading địa điểm hiển thị");
    }

    @Test(description = "LC-11 Ô tìm kiếm địa điểm hiển thị")
    public void searchInputDisplayed() {
        AdminLocationsPage page = new AdminLocationsPage(getDriver());
        Assert.assertTrue(page.isSearchInputDisplayed(), "Ô tìm kiếm hiển thị");
    }

    @Test(description = "LC-12 Tìm kiếm địa điểm theo từ khóa")
    public void searchByKeyword() {
        AdminLocationsPage page = new AdminLocationsPage(getDriver());
        page.typeSearchKeyword("Hà Nội");
        page.clickSearchSubmit();
        Assert.assertTrue(page.isPageDisplayed(), "Trang ổn định sau tìm kiếm");
    }

    @Test(description = "LC-13 Tìm kiếm chuỗi rỗng")
    public void searchEmptyKeyword() {
        AdminLocationsPage page = new AdminLocationsPage(getDriver());
        page.typeSearchKeyword("");
        page.clickSearchSubmit();
        Assert.assertTrue(page.isPageDisplayed(), "Trang ổn định");
    }

    @Test(description = "LC-14 Phân trang khi có dữ liệu")
    public void paginationWhenHasData() {
        AdminLocationsPage page = new AdminLocationsPage(getDriver());
        if (page.getTableRowCount() > 0) {
            Assert.assertTrue(page.isPaginationInfoDisplayed(), "Có thông tin phân trang");
        }
    }

    @Test(description = "LC-15 Hủy modal xác nhận xóa")
    public void cancelDeleteConfirm() {
        AdminLocationsPage page = new AdminLocationsPage(getDriver());
        if (page.getTableRowCount() == 0) {
            return;
        }
        page.clickDeleteFirstRow();
        page.cancelConfirmDelete();
        Assert.assertTrue(page.isPageDisplayed(), "Vẫn ở trang danh sách");
    }
}
