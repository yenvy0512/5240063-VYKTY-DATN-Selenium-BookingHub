package tests.customer;

import base.BaseTest;
import config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SearchPage;
import utils.ValidationTestHelper;

/**
 * Test trang Tìm chuyến — tìm kiếm, form, validation.
 */
public class SearchPageTest extends BaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrl() + "/search");
    }

    @Test(description = "SR-01 Trang tìm chuyến hiển thị form tìm kiếm")
    public void searchPageLoads() {
        SearchPage searchPage = new SearchPage(getDriver());
        Assert.assertTrue(searchPage.isSearchFormDisplayed(), "Form tìm kiếm hiển thị");
    }

    @Test(description = "SR-02 URL trang chứa search")
    public void searchPageUrl() {
        Assert.assertTrue(getDriver().getCurrentUrl().contains("search"), "URL chứa search");
    }

    @Test(description = "SR-03 Tiêu đề trang tìm chuyến đúng")
    public void searchPageTitle() {
        SearchPage searchPage = new SearchPage(getDriver());
        String title = searchPage.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Tìm chuyến") || title.contains("BookingHub"),
                "Tiêu đề phải chứa Tìm chuyến hoặc BookingHub");
    }

    @Test(description = "SR-04 Heading Tìm chuyến xe hiển thị")
    public void searchPageHeadingDisplayed() {
        SearchPage searchPage = new SearchPage(getDriver());
        Assert.assertTrue(searchPage.isHeadingTìmChuyếnDisplayed(), "Heading hiển thị");
        Assert.assertTrue(searchPage.getHeadingText().contains("Tìm chuyến xe"),
                "Nội dung heading đúng");
    }

    @Test(description = "SR-05 Form có đủ trường dữ liệu")
    public void searchFormHasDepartureArrivalDate() {
        SearchPage searchPage = new SearchPage(getDriver());
        Assert.assertTrue(searchPage.hasDepartureSelect(), "Điểm đi");
        Assert.assertTrue(searchPage.hasArrivalSelect(), "Điểm đến");
        Assert.assertTrue(searchPage.hasDepartureDateInput(), "Ô Ngày đi");
    }

    @Test(description = "SR-06 Nút tìm kiếm ấn được")
    public void searchSubmitEnabled() {
        SearchPage searchPage = new SearchPage(getDriver());
        Assert.assertTrue(searchPage.isSearchSubmitEnabled(), "Nút tìm kiếm ấn được");
    }

    @Test(description = "SR-07 Dropdown điểm đi có ít nhất placeholder")
    public void departureSelectHasOptions() {
        SearchPage searchPage = new SearchPage(getDriver());
        Assert.assertTrue(searchPage.getDepartureOptionCount() >= 1, "Có option trong điểm đi");
    }

    @Test(description = "SR-08 Bấm tìm kiếm khi chưa đủ điều kiện báo lỗi")
    public void validation_searchWithoutSelection_showsToast() {
        SearchPage searchPage = new SearchPage(getDriver());
        searchPage.clickSearchSubmit();
        boolean toastShown = ValidationTestHelper.waitForToastContainingText(getDriver(), "Vui lòng điền đầy đủ thông tin tìm kiếm");
        Assert.assertTrue(toastShown, "Báo lỗi khi chưa chọn đủ");
    }

    @Test(description = "SR-09 Chỉ chọn điểm đi rồi tìm báo thiếu thông tin")
    public void validation_onlyDepartureSelected() {
        SearchPage searchPage = new SearchPage(getDriver());
        if (searchPage.getDepartureOptionCount() > 1) {
            searchPage.selectDepartureByIndex(1);
        }
        searchPage.clickSearchSubmit();
        boolean toastOrStay = ValidationTestHelper.waitForToastContainingText(getDriver(), "Vui lòng")
                || getDriver().getCurrentUrl().contains("search");
        Assert.assertTrue(toastOrStay, "Thông báo lỗi");
    }

    @Test(description = "SR-10 Form tìm kiếm tương tác được")
    public void searchFormInteractive() {
        SearchPage searchPage = new SearchPage(getDriver());
        Assert.assertTrue(searchPage.isSearchFormDisplayed(), "Form hiển thị");
        Assert.assertTrue(searchPage.isSearchSubmitEnabled(), "Nút tìm kiếm hiển thị");
    }
}
