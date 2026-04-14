package tests.admin;

import base.AdminAuthBaseTest;
import config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.AdminBookingsPage;

public class AdminBookingsPageTest extends AdminAuthBaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrlAdmin() + "/bookings");
    }

    @Test(description = "AB-01 Trang Quản lý Đặt vé hiển thị")
    public void bookingsPageDisplayed() {
        AdminBookingsPage page = new AdminBookingsPage(getDriver());
        Assert.assertTrue(page.isPageDisplayed(), "Trang Quản lý Đặt vé phải hiển thị");
    }

    @Test(description = "AB-02 Ô tìm kiếm đặt vé hiển thị")
    public void bookingsSearchDisplayed() {
        AdminBookingsPage page = new AdminBookingsPage(getDriver());
        Assert.assertTrue(page.isSearchDisplayed(), "Ô tìm kiếm phải hiển thị");
    }

    @Test(description = "AB-03 Tiêu đề trang đặt vé đúng")
    public void bookingsPageTitle() {
        AdminBookingsPage page = new AdminBookingsPage(getDriver());
        String title = page.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Đặt vé") || title.contains("BookingHub"),
                "Title phải chứa Đặt vé/BookingHub");
    }

    @Test(description = "AB-04 Bảng đặt vé có cột Mã đặt vé Khách hàng Trạng thái")
    public void bookingsTableHasExpectedHeaders() {
        AdminBookingsPage page = new AdminBookingsPage(getDriver());
        Assert.assertTrue(page.hasTableHeaderMaDatVe(), "Bảng phải có cột Mã đặt vé");
        Assert.assertTrue(page.hasTableHeaderKhachHang(), "Bảng phải có cột Khách hàng");
        Assert.assertTrue(page.hasTableHeaderTrangThai(), "Bảng phải có cột Trạng thái");
    }

    @Test(description = "AB-05 Có phân trang hoặc thông tin phân trang")
    public void bookingsPaginationDisplayed() {
        AdminBookingsPage page = new AdminBookingsPage(getDriver());
        Assert.assertTrue(page.isPaginationDisplayed(), "Trang phải có phân trang hoặc thông tin trang");
    }
}
