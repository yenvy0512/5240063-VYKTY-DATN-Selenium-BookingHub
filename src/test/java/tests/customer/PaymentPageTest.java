package tests.customer;

import base.BaseTest;
import config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.PaymentPage;

/**
 * Test case trang Thanh toán web-customer.
 */
public class PaymentPageTest extends BaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrl() + "/payment");
    }

    @Test(description = "TC-C15: Trang thanh toán load")
    public void paymentPageLoads() {
        PaymentPage paymentPage = new PaymentPage(getDriver());
        Assert.assertTrue(paymentPage.isPageLoaded(), "Trang thanh toán phải load");
    }

    @Test(description = "TC-C16: Title trang thanh toán chứa Thanh toán hoặc BookingHub")
    public void paymentPageTitle() {
        PaymentPage paymentPage = new PaymentPage(getDriver());
        String title = paymentPage.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Thanh toán") || title.contains("BookingHub"),
                "Title phải chứa 'Thanh toán' hoặc 'BookingHub'");
    }
}
