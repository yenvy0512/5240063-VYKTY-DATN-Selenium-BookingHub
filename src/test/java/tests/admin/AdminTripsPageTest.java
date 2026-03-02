package tests.admin;

import base.AdminAuthBaseTest;
import config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.AdminTripsPage;

/**
 * Test case trang Quản lý Chuyến xe web-admin.
 */
public class AdminTripsPageTest extends AdminAuthBaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrlAdmin() + "/trips");
    }

    @Test(description = "TC-A09: Trang Quản lý Chuyến xe hiển thị")
    public void tripsPageDisplayed() {
        AdminTripsPage page = new AdminTripsPage(getDriver());
        Assert.assertTrue(page.isPageDisplayed(), "Trang Quản lý Chuyến xe phải hiển thị");
    }

    @Test(description = "TC-A10: Nút tạo chuyến hiển thị")
    public void tripsCreateButtonDisplayed() {
        AdminTripsPage page = new AdminTripsPage(getDriver());
        Assert.assertTrue(page.isCreateButtonDisplayed(), "Nút tạo chuyến phải hiển thị");
    }

    @Test(description = "TC-A11: Title trang chuyến xe đúng")
    public void tripsPageTitle() {
        AdminTripsPage page = new AdminTripsPage(getDriver());
        String title = page.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Chuyến") || title.contains("BookingHub"),
                "Title phải chứa Chuyến/BookingHub");
    }

    @Test(description = "TC-A09b: Bảng chuyến xe có cột Điểm đi, Điểm đến")
    public void tripsTableHasExpectedHeaders() {
        AdminTripsPage page = new AdminTripsPage(getDriver());
        Assert.assertTrue(page.hasTableHeaderDiemDi(), "Bảng phải có cột Điểm đi");
        Assert.assertTrue(page.hasTableHeaderDiemDen(), "Bảng phải có cột Điểm đến");
    }

    @Test(description = "TC-A09c: Ô tìm kiếm hiển thị")
    public void tripsSearchInputDisplayed() {
        AdminTripsPage page = new AdminTripsPage(getDriver());
        Assert.assertTrue(page.isSearchInputDisplayed(), "Ô tìm kiếm chuyến xe phải hiển thị");
    }
}
