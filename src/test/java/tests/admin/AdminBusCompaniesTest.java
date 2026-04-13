package tests.admin;

import base.AdminAuthBaseTest;
import config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.AdminBusCompaniesPage;

import java.time.Duration;

/**
 * Test Nhà xe: trang, CRUD (Create/Update/Delete), Validation - gộp 1 file.
 */
public class AdminBusCompaniesTest extends AdminAuthBaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrlAdmin() + "/bus-companies");
    }

    // --- Trang ---
    @Test(description = "BC-01 Trang Quản lý Nhà xe hiển thị")
    public void pageDisplayed() {
        AdminBusCompaniesPage page = new AdminBusCompaniesPage(getDriver());
        Assert.assertTrue(page.isPageDisplayed(), "Trang Quản lý Nhà xe phải hiển thị");
    }

    @Test(description = "BC-02 Tiêu đề trang nhà xe đúng")
    public void pageTitle() {
        AdminBusCompaniesPage page = new AdminBusCompaniesPage(getDriver());
        String title = page.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Nhà xe") || title.contains("BookingHub"),
                "Tiêu đề phải chứa Nhà xe/BookingHub");
    }

    @Test(description = "BC-03 Bảng có cột Tên nhà xe")
    public void tableHasHeaderTenNhaXe() {
        AdminBusCompaniesPage page = new AdminBusCompaniesPage(getDriver());
        Assert.assertTrue(page.hasTableHeaderTenNhaXe(), "Bảng phải có cột Tên nhà xe");
    }

    @Test(description = "BC-04 Nút Thêm nhà xe hiển thị")
    public void addButtonDisplayed() {
        AdminBusCompaniesPage page = new AdminBusCompaniesPage(getDriver());
        Assert.assertTrue(page.isAddButtonDisplayed(), "Nút Thêm nhà xe phải hiển thị");
    }

    @Test(description = "BC-05 Gửi thông tin trống modal vẫn mở")
    public void validation_submitEmpty_modalStaysOpen() {
        AdminBusCompaniesPage page = new AdminBusCompaniesPage(getDriver());
        page.clickAdd();
        Assert.assertTrue(page.isModalDisplayed(), "Modal phải mở");
        page.clickSubmitOnly();
        new WebDriverWait(getDriver(), Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bus-companies-modal']")));
        Assert.assertTrue(page.isModalDisplayed(), "Gửi thông tin trống phải không đóng modal");
    }

    // --- CRUD ---
    @Test(description = "BC-06 Create - Thêm nhà xe mới")
    public void crud_create() {
        AdminBusCompaniesPage page = new AdminBusCompaniesPage(getDriver());
        String name = "Nhà xe Test " + System.currentTimeMillis();
        String email = "test" + System.currentTimeMillis() + "@test.com";
        String phone = "0900123456";
        String address = "Địa chỉ test automation";

        page.clickAdd();
        Assert.assertTrue(page.isModalDisplayed(), "Modal thêm nhà xe phải mở");
        page.fillForm(name, email, phone, address, "active");
        page.submitForm();

        Assert.assertTrue(page.tableContainsName(name),
                "Sau khi thêm, bảng phải chứa tên nhà xe: " + name);
    }

    @Test(description = "BC-07 Update - Chỉnh sửa nhà xe", dependsOnMethods = "crud_create")
    public void crud_update() {
        AdminBusCompaniesPage page = new AdminBusCompaniesPage(getDriver());
        if (page.getTableRowCount() == 0) {
            Assert.fail("Cần có ít nhất 1 nhà xe để test cập nhật.");
        }

        String updatedName = "Nhà xe cập nhật " + System.currentTimeMillis();
        page.clickEditFirstRow();
        Assert.assertTrue(page.isModalDisplayed(), "Modal sửa phải mở");
        page.fillForm(updatedName, "updated@test.com", "0911222333", "Địa chỉ mới", "active");
        page.submitForm();

        Assert.assertTrue(page.tableContainsName(updatedName),
                "Sau khi sửa, bảng phải chứa: " + updatedName);
    }

    @Test(description = "BC-08 Delete - Xóa nhà xe", dependsOnMethods = "crud_update")
    public void crud_delete() {
        AdminBusCompaniesPage page = new AdminBusCompaniesPage(getDriver());
        int countBefore = page.getTableRowCount();
        if (countBefore == 0) {
            Assert.fail("Cần có ít nhất 1 nhà xe để test xóa.");
        }

        page.clickDeleteFirstRow();
        page.confirmDelete();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        wait.until(d -> page.getTableRowCount() == countBefore - 1);
        Assert.assertEquals(page.getTableRowCount(), countBefore - 1,
                "Sau khi xóa, số dòng phải giảm 1");
    }

    @Test(description = "BC-09 Heading Quản lý Nhà xe hiển thị")
    public void headingDisplayed() {
        AdminBusCompaniesPage page = new AdminBusCompaniesPage(getDriver());
        Assert.assertTrue(page.headingContainsNhaXe(), "Heading nhà xe hiển thị");
    }

    @Test(description = "BC-10 Ô tìm kiếm nhà xe hiển thị")
    public void searchInputDisplayed() {
        AdminBusCompaniesPage page = new AdminBusCompaniesPage(getDriver());
        Assert.assertTrue(page.isSearchInputDisplayed(), "Ô tìm kiếm hiển thị");
    }

    @Test(description = "BC-11 Tìm kiếm nhà xe theo từ khóa")
    public void searchByKeyword() {
        AdminBusCompaniesPage page = new AdminBusCompaniesPage(getDriver());
        page.typeSearchKeyword("test");
        page.clickSearchSubmit();
        Assert.assertTrue(page.isPageDisplayed(), "Trang ổn định");
    }

    @Test(description = "BC-12 Tìm kiếm chuỗi rỗng")
    public void searchEmptyKeyword() {
        AdminBusCompaniesPage page = new AdminBusCompaniesPage(getDriver());
        page.typeSearchKeyword("");
        page.clickSearchSubmit();
        Assert.assertTrue(page.isPageDisplayed(), "Trang ổn định");
    }

    @Test(description = "BC-13 Phân trang khi có dữ liệu")
    public void paginationWhenHasData() {
        AdminBusCompaniesPage page = new AdminBusCompaniesPage(getDriver());
        if (page.getTableRowCount() > 0) {
            Assert.assertTrue(page.isPaginationInfoDisplayed(), "Có phân trang");
        }
    }
}
