package tests.customer;

import base.BaseTest;
import config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SearchPage;
import utils.ValidationTestHelper;

/**
 * Test case trang Tìm chuyến web-customer.
 */
public class SearchPageTest extends BaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrl() + "/search");
    }

    @Test(description = "Trang tìm chuyến load và hiển thị form tìm kiếm")
    public void searchPageLoads() {
        SearchPage searchPage = new SearchPage(getDriver());
        Assert.assertTrue(searchPage.isSearchFormDisplayed(), "Form tìm kiếm phải hiển thị");
    }

    @Test(description = "Title trang tìm chuyến đúng")
    public void searchPageTitle() {
        SearchPage searchPage = new SearchPage(getDriver());
        String title = searchPage.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Tìm chuyến") || title.contains("BookingHub"),
                "Title phải chứa 'Tìm chuyến' hoặc 'BookingHub'");
    }

    @Test(description = "Heading Tìm chuyến xe hiển thị")
    public void searchPageHeadingDisplayed() {
        SearchPage searchPage = new SearchPage(getDriver());
        Assert.assertTrue(searchPage.isHeadingTìmChuyếnDisplayed(), "Heading 'Tìm chuyến xe' phải hiển thị");
        Assert.assertTrue(searchPage.getHeadingText().contains("Tìm chuyến xe"),
                "Nội dung heading phải là 'Tìm chuyến xe'");
    }

    @Test(description = "Form có Điểm đi, Điểm đến, Ngày đi")
    public void searchFormHasDepartureArrivalDate() {
        SearchPage searchPage = new SearchPage(getDriver());
        Assert.assertTrue(searchPage.hasDepartureSelect(), "Phải có select Điểm đi");
        Assert.assertTrue(searchPage.hasArrivalSelect(), "Phải có select Điểm đến");
        Assert.assertTrue(searchPage.hasDepartureDateInput(), "Phải có ô Ngày đi");
    }

    @Test(description = "Bấm tìm kiếm khi chưa chọn đủ hiện toast validation")
    public void validation_searchWithoutSelection_showsToast() {
        SearchPage searchPage = new SearchPage(getDriver());
        searchPage.clickSearchSubmit();
        boolean toastShown = ValidationTestHelper.waitForToastContainingText(getDriver(), "Vui lòng điền đầy đủ thông tin tìm kiếm");
        Assert.assertTrue(toastShown, "Phải hiển thị toast validation khi chưa chọn đủ điều kiện tìm kiếm");
    }
}
