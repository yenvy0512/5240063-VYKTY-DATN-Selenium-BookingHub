package tests.customer;

import base.CustomerAuthBaseTest;
import config.Config;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.MyBookingsPage;

import java.time.Duration;

public class MyBookingsPageTest extends CustomerAuthBaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrl() + "/my-bookings");
    }

    @Test(description = "MB-01 Trang Vé của tôi hiển thị đúng")
    public void myBookingsPageLoads() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(d -> {
                    MyBookingsPage p = new MyBookingsPage(d);
                    return p.isPageLoaded();
                });
        MyBookingsPage page = new MyBookingsPage(getDriver());
        Assert.assertTrue(page.isPageLoaded(), "Trang hiển thị");
    }

    @Test(description = "MB-02 Tiêu đề chứa Vé hoặc BookingHub")
    public void myBookingsPageTitle() {
        MyBookingsPage page = new MyBookingsPage(getDriver());
        String title = page.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Vé") || title.contains("BookingHub"),
                "Tiêu đề hợp lệ");
    }

    @Test(description = "MB-03 URL chứa my-bookings")
    public void myBookingsUrl() {
        Assert.assertTrue(getDriver().getCurrentUrl().contains("my-bookings"),
                "URL đúng trang vé");
    }

    @Test(description = "MB-04 Heading Vé của tôi hiển thị")
    public void headingDisplayed() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(d -> new MyBookingsPage(d).isHeadingVeCuaToiDisplayed());
        MyBookingsPage page = new MyBookingsPage(getDriver());
        Assert.assertTrue(page.isHeadingVeCuaToiDisplayed(), "Heading hiển thị");
    }

    @Test(description = "MB-05 Hiển thị trạng thái loading hoặc danh sách sau khi vào trang")
    public void loadingOrContent() {
        MyBookingsPage page = new MyBookingsPage(getDriver());
        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(d -> !new MyBookingsPage(d).isLoadingState() || new MyBookingsPage(d).isEmptyState());
        Assert.assertTrue(page.isPageLoaded(), "Trang hiển thị");
    }

    @Test(description = "MB-06 Khi không có vé có nút Tìm chuyến xe")
    public void emptyStateHasSearchButton() {
        MyBookingsPage page = new MyBookingsPage(getDriver());
        new WebDriverWait(getDriver(), Duration.ofSeconds(15))
                .until(d -> !new MyBookingsPage(d).isLoadingState());
        if (page.isEmptyState()) {
            Assert.assertTrue(page.hasLinkToSearchTrips(), "Nút tìm chuyến khi danh sách trống");
        }
    }

    @Test(description = "MB-07 Đã đăng nhập không bị điều hướng về đăng nhập")
    public void staysAuthenticated() {
        Assert.assertFalse(getDriver().getCurrentUrl().contains("/login"),
                "Không bị điều hướng về đăng nhập");
    }

    @Test(description = "MB-08 Trang có container my-bookings")
    public void pageContainerVisible() {
        MyBookingsPage page = new MyBookingsPage(getDriver());
        Assert.assertTrue(page.isPageLoaded(), "Container hiển thị");
    }

    @Test(description = "MB-09 Tiêu đề không rỗng")
    public void titleNotEmpty() {
        Assert.assertFalse(getDriver().getTitle().isEmpty(), "Có tiều đề");
    }

    @Test(description = "MB-10 Sau khi load dữ liệu xong ẩn loading")
    public void loadingFinishes() {
        new WebDriverWait(getDriver(), Duration.ofSeconds(20))
                .until(d -> !new MyBookingsPage(d).isLoadingState());
        Assert.assertFalse(new MyBookingsPage(getDriver()).isLoadingState(), "Hết loading");
    }
}
