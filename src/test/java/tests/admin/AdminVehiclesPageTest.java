package tests.admin;

import base.AdminAuthBaseTest;
import config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.AdminVehiclesPage;

import java.time.Duration;

public class AdminVehiclesPageTest extends AdminAuthBaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrlAdmin() + "/vehicles");
    }

    @Test(description = "VH-01 Trang Quản lý Xe hiển thị")
    public void vehiclesPageDisplayed() {
        AdminVehiclesPage page = new AdminVehiclesPage(getDriver());
        Assert.assertTrue(page.isPageDisplayed(), "Trang Quản lý Xe phải hiển thị");
    }

    @Test(description = "VH-02 Tiêu đề trang xe đúng")
    public void vehiclesPageTitle() {
        AdminVehiclesPage page = new AdminVehiclesPage(getDriver());
        String title = page.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Xe") || title.contains("BookingHub"),
                "Tiêu đề phải chứa Xe/BookingHub");
    }

    @Test(description = "VH-03 Bảng xe có cột Tên xe và Biển số")
    public void vehiclesTableHasExpectedHeaders() {
        AdminVehiclesPage page = new AdminVehiclesPage(getDriver());
        Assert.assertTrue(page.hasTableHeaderTenXe(), "Bảng phải có cột Tên xe");
        Assert.assertTrue(page.hasTableHeaderBienSo(), "Bảng phải có cột Biển số");
    }

    @Test(description = "VH-04 Nút Thêm xe hiển thị")
    public void vehiclesAddButtonDisplayed() {
        AdminVehiclesPage page = new AdminVehiclesPage(getDriver());
        Assert.assertTrue(page.isAddButtonDisplayed(), "Nút Thêm xe phải hiển thị");
    }

    @Test(description = "VH-05 Ô tìm kiếm xe hiển thị và có placeholder")
    public void searchInputDisplayed() {
        AdminVehiclesPage page = new AdminVehiclesPage(getDriver());
        Assert.assertTrue(page.isSearchInputDisplayed(), "Ô tìm kiếm phải hiển thị");
        Assert.assertFalse(page.getSearchPlaceholder().isEmpty(), "Placeholder tìm kiếm không rỗng");
    }

    @Test(description = "VH-06 Tìm kiếm với từ khóa không làm crash trang")
    public void searchByKeyword() {
        AdminVehiclesPage page = new AdminVehiclesPage(getDriver());
        page.typeSearchKeyword("test");
        page.clickSearchSubmit();
        Assert.assertTrue(page.isPageDisplayed(), "Sau tìm kiếm trang vẫn hiển thị");
    }

    @Test(description = "VH-07 Tìm kiếm chuỗi rỗng vẫn thực hiện được")
    public void searchEmptyKeyword() {
        AdminVehiclesPage page = new AdminVehiclesPage(getDriver());
        page.typeSearchKeyword("");
        page.clickSearchSubmit();
        Assert.assertTrue(page.isPageDisplayed(), "Trang vẫn ổn định");
    }

    @Test(description = "VH-08 Khi có dữ liệu hiển thị thông tin phân trang")
    public void paginationInfoWhenDataExists() {
        AdminVehiclesPage page = new AdminVehiclesPage(getDriver());
        if (page.getTableRowCount() > 0) {
            Assert.assertTrue(page.isPaginationInfoDisplayed(), "Có phân trang khi có bản ghi");
        }
    }

    @Test(description = "VH-09 Gửi thông tin trống modal vẫn mở")
    public void validation_submitEmpty_modalStaysOpen() {
        AdminVehiclesPage page = new AdminVehiclesPage(getDriver());
        page.clickAdd();
        Assert.assertTrue(page.isModalDisplayed(), "Modal phải mở");
        page.clickSubmitOnly();
        new WebDriverWait(getDriver(), Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vehicles-modal']")));
        Assert.assertTrue(page.isModalDisplayed(), "Gửi thông tin trống không đóng modal");
        page.cancelForm();
    }

    @Test(description = "VH-10 Create - Thêm xe mới")
    public void crud_create() {
        AdminVehiclesPage page = new AdminVehiclesPage(getDriver());
        String suffix = String.valueOf(System.currentTimeMillis());
        String busName = "Xe Auto " + suffix;
        String plate = "29A-" + suffix.substring(suffix.length() - 5);

        page.clickAdd();
        Assert.assertTrue(page.isModalDisplayed(), "Modal thêm xe phải mở");
        page.fillForm(busName, "Ghế ngồi", plate, 40, "active", null, 1);
        page.submitForm();

        Assert.assertTrue(page.tableContainsBusName(busName),
                "Bảng phải chứa tên xe vừa thêm: " + busName);
    }

    @Test(description = "VH-11 Update - Chỉnh sửa xe", dependsOnMethods = "crud_create")
    public void crud_update() {
        AdminVehiclesPage page = new AdminVehiclesPage(getDriver());
        if (page.getTableRowCount() == 0) {
            Assert.fail("Cần có ít nhất 1 xe để sửa.");
        }
        String updated = "Xe cập nhật " + System.currentTimeMillis();
        page.clickEditFirstRow();
        Assert.assertTrue(page.isModalDisplayed(), "Modal sửa phải mở");
        page.fillForm(updated, "Limousine", "30B-11111", 35, "active", null, 1);
        page.submitForm();
        Assert.assertTrue(page.tableContainsBusName(updated), "Bảng phải chứa tên sau cập nhật");
    }

    @Test(description = "VH-12 Delete - Xóa xe", dependsOnMethods = "crud_update")
    public void crud_delete() {
        AdminVehiclesPage page = new AdminVehiclesPage(getDriver());
        int before = page.getTableRowCount();
        if (before == 0) {
            Assert.fail("Cần có ít nhất 1 xe để xóa.");
        }
        page.clickDeleteFirstRow();
        page.confirmDelete();
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        wait.until(d -> page.getTableRowCount() == before - 1);
        Assert.assertEquals(page.getTableRowCount(), before - 1, "Số dòng giảm 1 sau xóa");
    }
}
