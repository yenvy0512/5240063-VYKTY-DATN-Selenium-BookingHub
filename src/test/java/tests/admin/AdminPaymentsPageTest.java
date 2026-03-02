package tests.admin;

import base.AdminAuthBaseTest;
import config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.AdminPaymentsPage;

/**
 * Test case trang Quản lý Thanh toán web-admin.
 */
public class AdminPaymentsPageTest extends AdminAuthBaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrlAdmin() + "/payments");
    }

    @Test(description = "TC-A24: Trang Quản lý Thanh toán load")
    public void paymentsPageLoads() {
        AdminPaymentsPage page = new AdminPaymentsPage(getDriver());
        Assert.assertTrue(page.isPageLoaded(), "Trang Quản lý Thanh toán phải load");
    }

    @Test(description = "TC-A25: Title trang thanh toán đúng")
    public void paymentsPageTitle() {
        AdminPaymentsPage page = new AdminPaymentsPage(getDriver());
        String title = page.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Thanh toán") || title.contains("BookingHub"),
                "Title phải chứa Thanh toán/BookingHub");
    }
}
