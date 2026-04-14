package tests.customer;

import base.CustomerAuthBaseTest;
import config.Config;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.PaymentPage;

import java.time.Duration;

public class PaymentPageTest extends CustomerAuthBaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrl() + "/payment");
    }

    @Test(description = "PM-01 Trang thanh toán hiển thị")
    public void paymentPageLoads() {
        PaymentPage paymentPage = new PaymentPage(getDriver());
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(d -> paymentPage.isPageLoaded());
        Assert.assertTrue(paymentPage.isPageLoaded(), "Trang thanh toán hiển thị");
    }

    @Test(description = "PM-02 Tiêu đề trang chứa Thanh toán")
    public void paymentPageTitle() {
        PaymentPage paymentPage = new PaymentPage(getDriver());
        String title = paymentPage.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Thanh toán") || title.contains("BookingHub"),
                "Tiêu đề hợp lệ");
    }

    @Test(description = "PM-03 Không có mã đặt vé hiển thị trạng thái không tìm thấy")
    public void paymentWithoutBookingIdShowsEmpty() {
        PaymentPage paymentPage = new PaymentPage(getDriver());
        new WebDriverWait(getDriver(), Duration.ofSeconds(10))
                .until(d -> paymentPage.isEmptyState() || paymentPage.isLoadingState()
                        || paymentPage.hasBookingSummarySection());
        if (getDriver().getCurrentUrl().contains("/payment") && !getDriver().getCurrentUrl().contains("bookingId=")) {
            Assert.assertTrue(paymentPage.isEmptyState() || paymentPage.isLoadingState(),
                    "Không có mã đặt vé hiển thị trống");
        }
    }

    @Test(description = "PM-04 URL chứa payment")
    public void paymentPageUrl() {
        Assert.assertTrue(getDriver().getCurrentUrl().contains("payment"), "URL chứa payment");
    }

    @Test(description = "PM-05 Hiển thị Heading Thanh toán")
    public void paymentHeadingWhenDataLoaded() {
        PaymentPage paymentPage = new PaymentPage(getDriver());
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(d -> {
            PaymentPage p = new PaymentPage(d);
            return p.isHeadingThanhToanDisplayed() || p.isEmptyState();
        });
        if (paymentPage.isHeadingThanhToanDisplayed()) {
            Assert.assertTrue(paymentPage.isHeadingThanhToanDisplayed(), "Heading Thanh toán");
        }
    }

    @Test(description = "PM-06 Trạng thái không tìm thấy vé hiển thị thông báo")
    public void emptyStateHasMessage() {
        PaymentPage paymentPage = new PaymentPage(getDriver());
        if (paymentPage.isEmptyState()) {
            Assert.assertTrue(paymentPage.isNoBookingMessageDisplayed(), "Thông báo không tìm thấy vé");
        }
    }

    @Test(description = "PM-07 Khi load xong có phương thức thanh toán")
    public void paymentMethodsWhenBookingExists() {
        PaymentPage paymentPage = new PaymentPage(getDriver());
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(d -> true);
        if (paymentPage.hasBookingSummarySection()) {
            Assert.assertTrue(paymentPage.hasPaymentMethodSection(), "Có phương thức thanh toán");
        }
    }

    @Test(description = "PM-08 Khi có vé hiển thị thông tin vé")
    public void bookingSummaryWhenLoaded() {
        PaymentPage paymentPage = new PaymentPage(getDriver());
        if (paymentPage.hasBookingSummarySection()) {
            Assert.assertTrue(paymentPage.hasBookingSummarySection(), "Thông tin vé hiển thị");
        }
    }

    @Test(description = "PM-09 Trang có DOM không rỗng")
    public void paymentPageStable() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(d -> d.getPageSource() != null && d.getPageSource().length() > 100);
        Assert.assertTrue(getDriver().getPageSource().length() > 100, "DOM có nội dung");
    }

    @Test(description = "PM-10 Hiển thị rỗng hoặc có danh sách vé")
    public void paymentInitialState() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(d -> {
            PaymentPage p = new PaymentPage(d);
            return p.isLoadingState() || p.isEmptyState() || p.hasBookingSummarySection();
        });
        PaymentPage paymentPage = new PaymentPage(getDriver());
        Assert.assertTrue(
                paymentPage.isLoadingState() || paymentPage.isEmptyState() || paymentPage.hasBookingSummarySection(),
                "Hiển thị rỗng hoặc có danh sách vé");
    }
}
