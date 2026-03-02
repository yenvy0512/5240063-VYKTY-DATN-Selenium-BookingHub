package tests.admin;

import base.AdminAuthBaseTest;
import config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.AdminNotificationsPage;

/**
 * Test case trang Thông báo web-admin.
 */
public class AdminNotificationsPageTest extends AdminAuthBaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrlAdmin() + "/notifications");
    }

    @Test(description = "TC-A22: Trang Thông báo hiển thị")
    public void notificationsPageDisplayed() {
        AdminNotificationsPage page = new AdminNotificationsPage(getDriver());
        Assert.assertTrue(page.isPageDisplayed(), "Trang Thông báo phải hiển thị");
    }

    @Test(description = "TC-A23: Title trang thông báo đúng")
    public void notificationsPageTitle() {
        AdminNotificationsPage page = new AdminNotificationsPage(getDriver());
        String title = page.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Thông báo") || title.contains("BookingHub"),
                "Title phải chứa Thông báo/BookingHub");
    }
}
