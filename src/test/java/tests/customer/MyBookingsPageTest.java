package tests.customer;

import base.BaseTest;
import config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MyBookingsPage;

/**
 * Test case trang Vé của tôi web-customer.
 */
public class MyBookingsPageTest extends BaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrl() + "/my-bookings");
    }

    @Test(description = "TC-C17: Trang Vé của tôi load")
    public void myBookingsPageLoads() {
        MyBookingsPage page = new MyBookingsPage(getDriver());
        Assert.assertTrue(page.isPageLoaded(), "Trang Vé của tôi phải load");
    }

    @Test(description = "TC-C18: Title trang Vé của tôi đúng")
    public void myBookingsPageTitle() {
        MyBookingsPage page = new MyBookingsPage(getDriver());
        String title = page.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Vé") || title.contains("BookingHub"),
                "Title phải chứa 'Vé' hoặc 'BookingHub'");
    }
}
