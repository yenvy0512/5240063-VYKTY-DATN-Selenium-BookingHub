package tests.admin;

import base.AdminAuthBaseTest;
import config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.AdminVASPage;

/**
 * Test case trang Quản lý Dịch vụ (VAS) web-admin.
 */
public class AdminVASPageTest extends AdminAuthBaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrlAdmin() + "/vas");
    }

    @Test(description = "TC-A20: Trang Quản lý Dịch vụ hiển thị")
    public void vasPageDisplayed() {
        AdminVASPage page = new AdminVASPage(getDriver());
        Assert.assertTrue(page.isPageDisplayed(), "Trang Quản lý Dịch vụ phải hiển thị");
    }

    @Test(description = "TC-A21: Title trang VAS đúng")
    public void vasPageTitle() {
        AdminVASPage page = new AdminVASPage(getDriver());
        String title = page.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Dịch vụ") || title.contains("VAS") || title.contains("BookingHub"),
                "Title phải chứa Dịch vụ/VAS/BookingHub");
    }
}
