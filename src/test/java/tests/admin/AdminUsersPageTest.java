package tests.admin;

import base.AdminAuthBaseTest;
import config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.AdminUsersPage;

/**
 * Test case trang Quản lý Người dùng web-admin.
 */
public class AdminUsersPageTest extends AdminAuthBaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrlAdmin() + "/users");
    }

    @Test(description = "TC-A18: Trang Quản lý Người dùng hiển thị")
    public void usersPageDisplayed() {
        AdminUsersPage page = new AdminUsersPage(getDriver());
        Assert.assertTrue(page.isPageDisplayed(), "Trang Quản lý Người dùng phải hiển thị");
    }

    @Test(description = "TC-A19: Title trang người dùng đúng")
    public void usersPageTitle() {
        AdminUsersPage page = new AdminUsersPage(getDriver());
        String title = page.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Người dùng") || title.contains("BookingHub"),
                "Title phải chứa Người dùng/BookingHub");
    }
}
