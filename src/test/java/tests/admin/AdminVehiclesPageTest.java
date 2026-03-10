package tests.admin;

import base.AdminAuthBaseTest;
import config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.AdminVehiclesPage;

/**
 * Test case trang Quản lý Xe web-admin.
 */
public class AdminVehiclesPageTest extends AdminAuthBaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrlAdmin() + "/vehicles");
    }

    @Test(description = "Trang Quản lý Xe hiển thị")
    public void vehiclesPageDisplayed() {
        AdminVehiclesPage page = new AdminVehiclesPage(getDriver());
        Assert.assertTrue(page.isPageDisplayed(), "Trang Quản lý Xe phải hiển thị");
    }

    @Test(description = "Title trang xe đúng")
    public void vehiclesPageTitle() {
        AdminVehiclesPage page = new AdminVehiclesPage(getDriver());
        String title = page.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Xe") || title.contains("BookingHub"),
                "Title phải chứa Xe/BookingHub");
    }

    @Test(description = "Bảng xe có cột Tên xe, Biển số")
    public void vehiclesTableHasExpectedHeaders() {
        AdminVehiclesPage page = new AdminVehiclesPage(getDriver());
        Assert.assertTrue(page.hasTableHeaderTenXe(), "Bảng phải có cột Tên xe");
        Assert.assertTrue(page.hasTableHeaderBienSo(), "Bảng phải có cột Biển số");
    }

    @Test(description = "Nút Thêm xe hiển thị")
    public void vehiclesAddButtonDisplayed() {
        AdminVehiclesPage page = new AdminVehiclesPage(getDriver());
        Assert.assertTrue(page.isAddButtonDisplayed(), "Nút Thêm xe phải hiển thị");
    }
}
