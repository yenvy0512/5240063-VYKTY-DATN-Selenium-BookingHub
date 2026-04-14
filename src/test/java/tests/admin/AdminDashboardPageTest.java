package tests.admin;

import base.AdminAuthBaseTest;
import config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.AdminDashboardPage;

public class AdminDashboardPageTest extends AdminAuthBaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrlAdmin() + "/");
    }

    @Test(description = "AD-01 Dashboard hiển thị sau khi đăng nhập")
    public void dashboardDisplayed() {
        AdminDashboardPage page = new AdminDashboardPage(getDriver());
        Assert.assertTrue(page.isDashboardDisplayed(), "Dashboard phải hiển thị");
    }

    @Test(description = "AD-02 Tiêu đề Dashboard đúng")
    public void dashboardTitle() {
        AdminDashboardPage page = new AdminDashboardPage(getDriver());
        String title = page.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Dashboard") || title.contains("Admin") || title.contains("BookingHub"),
                "Tiêu đề phải chứa Dashboard/Admin/BookingHub");
    }
}
