package tests.admin;

import base.AdminAuthBaseTest;
import config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.AdminReviewsPage;

public class AdminReviewsPageTest extends AdminAuthBaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrlAdmin() + "/reviews");
    }

    @Test(description = "RV-01 Trang Quản lý Đánh giá load")
    public void reviewsPageLoads() {
        AdminReviewsPage page = new AdminReviewsPage(getDriver());
        Assert.assertTrue(page.isPageLoaded(), "Trang Quản lý Đánh giá phải load");
    }

    @Test(description = "RV-02 Tiêu đề trang đánh giá đúng")
    public void reviewsPageTitle() {
        AdminReviewsPage page = new AdminReviewsPage(getDriver());
        String title = page.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Đánh giá") || title.contains("BookingHub"),
                "Tiêu đề phải chứa Đánh giá/BookingHub");
    }
}
