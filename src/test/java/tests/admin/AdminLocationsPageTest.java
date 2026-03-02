package tests.admin;

import base.AdminAuthBaseTest;
import config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.AdminLocationsPage;

/**
 * Test case trang Quản lý Địa điểm web-admin.
 */
public class AdminLocationsPageTest extends AdminAuthBaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrlAdmin() + "/locations");
    }

    @Test(description = "TC-A14: Trang Quản lý Địa điểm hiển thị")
    public void locationsPageDisplayed() {
        AdminLocationsPage page = new AdminLocationsPage(getDriver());
        Assert.assertTrue(page.isPageDisplayed(), "Trang Quản lý Địa điểm phải hiển thị");
    }

    @Test(description = "TC-A15: Title trang địa điểm đúng")
    public void locationsPageTitle() {
        AdminLocationsPage page = new AdminLocationsPage(getDriver());
        String title = page.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Địa điểm") || title.contains("BookingHub"),
                "Title phải chứa Địa điểm/BookingHub");
    }

    @Test(description = "TC-A14b: Bảng địa điểm có cột Thành phố")
    public void locationsTableHasExpectedHeaders() {
        AdminLocationsPage page = new AdminLocationsPage(getDriver());
        Assert.assertTrue(page.hasTableHeaderThanhPho(), "Bảng phải có cột Thành phố");
    }

    @Test(description = "TC-A14c: Nút Thêm địa điểm hiển thị")
    public void locationsAddButtonDisplayed() {
        AdminLocationsPage page = new AdminLocationsPage(getDriver());
        Assert.assertTrue(page.isAddButtonDisplayed(), "Nút Thêm địa điểm phải hiển thị");
    }
}
