package tests.customer;

import base.BaseTest;
import config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ProfilePage;

/**
 * Test case trang Thông tin cá nhân web-customer.
 */
public class ProfilePageTest extends BaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrl() + "/profile");
    }

    @Test(description = "TC-C19: Trang profile load")
    public void profilePageLoads() {
        ProfilePage page = new ProfilePage(getDriver());
        Assert.assertTrue(page.isPageLoaded(), "Trang profile phải load");
    }

    @Test(description = "TC-C20: Title trang profile chứa Thông tin hoặc BookingHub")
    public void profilePageTitle() {
        ProfilePage page = new ProfilePage(getDriver());
        String title = page.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("BookingHub") || title.contains("cá nhân") || title.contains("Profile"),
                "Title phải liên quan profile/BookingHub");
    }
}
