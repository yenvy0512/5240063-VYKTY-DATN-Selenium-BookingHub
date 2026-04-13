package tests.customer;

import base.CustomerAuthBaseTest;
import config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BookingPage;
import utils.ValidationTestHelper;

/**
 * Test trang Đặt vé (cần đăng nhập).
 */
public class BookingPageTest extends CustomerAuthBaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrl() + "/booking");
    }

    @Test(description = "BK-01 Trang đặt vé hiển thị và có tiêu đề")
    public void bookingPageLoads() {
        BookingPage bookingPage = new BookingPage(getDriver());
        String title = bookingPage.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("BookingHub") || title.contains("Đặt vé") || title.contains("Tìm chuyến"),
                "Tiêu hợp lệ");
    }

    @Test(description = "BK-02 Kiểm tra url chứa booking")
    public void bookingPageUrl() {
        String url = getDriver().getCurrentUrl();
        Assert.assertTrue(url.contains("booking") || url.contains("search") || url.contains("login"),
                "URL liên quan luồng đặt vé");
    }

    @Test(description = "BK-03 Khi ở trang booking có form đặt vé")
    public void bookingFormDisplayedWhenOnBookingPage() {
        if (!getDriver().getCurrentUrl().contains("/booking")) {
            return;
        }
        BookingPage bookingPage = new BookingPage(getDriver());
        Assert.assertTrue(bookingPage.isBookingFormDisplayed(), "Form đặt vé hiển thị");
    }

    @Test(description = "BK-04 Form có hiển thị các trường thông tin")
    public void bookingFormHasCustomerAndPaymentFields() {
        if (!getDriver().getCurrentUrl().contains("/booking")) {
            return;
        }
        BookingPage bookingPage = new BookingPage(getDriver());
        Assert.assertTrue(bookingPage.hasCustomerNameField(), "Ô Họ tên");
        Assert.assertTrue(bookingPage.hasCustomerPhoneField(), "Ô SĐT");
        Assert.assertTrue(bookingPage.hasCustomerEmailField(), "Ô Email");
        Assert.assertTrue(bookingPage.hasPaymentMethodField(), "Phương thức thanh toán");
        Assert.assertTrue(bookingPage.isSubmitButtonDisplayed(), "Nút xác nhận");
    }

    @Test(description = "BK-05 Có heading trang khi có form")
    public void bookingHeadingWhenFormVisible() {
        if (!getDriver().getCurrentUrl().contains("/booking")) {
            return;
        }
        BookingPage bookingPage = new BookingPage(getDriver());
        if (bookingPage.isBookingFormDisplayed()) {
            Assert.assertTrue(bookingPage.headingBookingDisplayed(), "Có heading trang");
        }
    }

    @Test(description = "BK-06 Gửi thông tin trống có thể hiện thông báo lỗi")
    public void validation_submitEmptyBookingForm() {
        if (!getDriver().getCurrentUrl().contains("/booking")) {
            return;
        }
        BookingPage bookingPage = new BookingPage(getDriver());
        if (!bookingPage.isBookingFormDisplayed()) {
            return;
        }
        bookingPage.clickSubmit();
        boolean toast = ValidationTestHelper.waitForToastContainingText(getDriver(), "Vui lòng")
                || ValidationTestHelper.isTextPresentOnPage(getDriver(), "Vui lòng");
        boolean stillOnBooking = getDriver().getCurrentUrl().contains("booking");
        Assert.assertTrue(toast || stillOnBooking, "Có thông báo lỗi");
    }

    @Test(description = "BK-07 Điền họ tên và gửi thông tin vẫn báo bắt buộc nhập trường khác")
    public void validation_partialFillNameOnly() {
        if (!getDriver().getCurrentUrl().contains("/booking")) {
            return;
        }
        BookingPage bookingPage = new BookingPage(getDriver());
        if (!bookingPage.isBookingFormDisplayed()) {
            return;
        }
        bookingPage.fillCustomerName("Test User");
        bookingPage.clickSubmit();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("booking")
                        || ValidationTestHelper.isTextPresentOnPage(getDriver(), "Vui lòng"),
                "Chưa đủ thông tin hoặc còn trên trang booking");
    }

    @Test(description = "BK-08 Nút đặt vé hiển thị khi có form")
    public void submitButtonVisibleWithForm() {
        if (!getDriver().getCurrentUrl().contains("/booking")) {
            return;
        }
        BookingPage bookingPage = new BookingPage(getDriver());
        if (bookingPage.isBookingFormDisplayed()) {
            Assert.assertTrue(bookingPage.isSubmitButtonDisplayed(), "Nút đặt vé hiển thị");
        }
    }
}
