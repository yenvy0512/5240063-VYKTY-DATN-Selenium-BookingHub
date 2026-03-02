package tests.admin;

import base.AdminAuthBaseTest;
import config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.AdminBusCompaniesPage;

/**
 * Test case trang Quản lý Nhà xe web-admin.
 */
public class AdminBusCompaniesPageTest extends AdminAuthBaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrlAdmin() + "/bus-companies");
    }

    @Test(description = "TC-A16: Trang Quản lý Nhà xe hiển thị")
    public void busCompaniesPageDisplayed() {
        AdminBusCompaniesPage page = new AdminBusCompaniesPage(getDriver());
        Assert.assertTrue(page.isPageDisplayed(), "Trang Quản lý Nhà xe phải hiển thị");
    }

    @Test(description = "TC-A17: Title trang nhà xe đúng")
    public void busCompaniesPageTitle() {
        AdminBusCompaniesPage page = new AdminBusCompaniesPage(getDriver());
        String title = page.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Nhà xe") || title.contains("BookingHub"),
                "Title phải chứa Nhà xe/BookingHub");
    }

    @Test(description = "TC-A16b: Bảng nhà xe có cột Tên nhà xe")
    public void busCompaniesTableHasExpectedHeaders() {
        AdminBusCompaniesPage page = new AdminBusCompaniesPage(getDriver());
        Assert.assertTrue(page.hasTableHeaderTenNhaXe(), "Bảng phải có cột Tên nhà xe");
    }

    @Test(description = "TC-A16c: Nút Thêm nhà xe hiển thị")
    public void busCompaniesAddButtonDisplayed() {
        AdminBusCompaniesPage page = new AdminBusCompaniesPage(getDriver());
        Assert.assertTrue(page.isAddButtonDisplayed(), "Nút Thêm nhà xe phải hiển thị");
    }
}
