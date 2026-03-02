package tests.customer;

import base.BaseTest;
import config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BookingPage;

/**
 * Test case trang Đặt vé web-customer.
 */
public class BookingPageTest extends BaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrl() + "/booking");
    }

    @Test(description = "TC-C13: Trang đặt vé load (có thể redirect nếu chưa chọn chuyến)")
    public void bookingPageLoads() {
        BookingPage bookingPage = new BookingPage(getDriver());
        String title = bookingPage.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("BookingHub") || title.contains("Đặt vé") || title.contains("Tìm chuyến"),
                "Title phải liên quan BookingHub/Đặt vé/Tìm chuyến");
    }

    @Test(description = "TC-C14: Nếu có form đặt vé thì form hiển thị")
    public void bookingFormDisplayedWhenOnBookingPage() {
        String url = getDriver().getCurrentUrl();
        if (url.contains("/booking")) {
            BookingPage bookingPage = new BookingPage(getDriver());
            Assert.assertTrue(bookingPage.isBookingFormDisplayed(),
                    "Khi ở trang booking phải hiển thị form đặt vé");
        }
    }

    @Test(description = "TC-C14b: Form đặt vé có Họ tên, SĐT, Email, Phương thức thanh toán")
    public void bookingFormHasCustomerAndPaymentFields() {
        String url = getDriver().getCurrentUrl();
        if (url.contains("/booking")) {
            BookingPage bookingPage = new BookingPage(getDriver());
            Assert.assertTrue(bookingPage.hasCustomerNameField(), "Form phải có ô Họ tên");
            Assert.assertTrue(bookingPage.hasCustomerPhoneField(), "Form phải có ô Số điện thoại");
            Assert.assertTrue(bookingPage.hasCustomerEmailField(), "Form phải có ô Email");
            Assert.assertTrue(bookingPage.hasPaymentMethodField(), "Form phải có chọn Phương thức thanh toán");
            Assert.assertTrue(bookingPage.isSubmitButtonDisplayed(), "Form phải có nút Xác nhận");
        }
    }
}
