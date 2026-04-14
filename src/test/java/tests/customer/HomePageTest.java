package tests.customer;

import base.BaseTest;
import config.Config;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.ValidationTestHelper;

public class HomePageTest extends BaseTest {

    @Test(description = "HM-01 Trang chủ hiển thị thành công")
    public void homePageLoads() {
        HomePage homePage = new HomePage(getDriver());
        Assert.assertTrue(homePage.isPageLoaded(), "Trang chủ phải hiển thị được");
    }

    @Test(description = "HM-02 URL trang chủ đúng")
    public void homePageUrl() {
        String url = getDriver().getCurrentUrl();
        Assert.assertTrue(url.startsWith(Config.getBaseUrl().replaceAll("/$", "")),
                "URL trang chủ đúng");
    }

    @Test(description = "HM-03 Tiêu đề trang chủ đúng")
    public void homePageTitle() {
        HomePage homePage = new HomePage(getDriver());
        String title = homePage.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("BookingHub") || title.contains("Đặt vé"),
                "Tiêu đề phải chứa BookingHub hoặc Đặt vé");
    }

    @Test(description = "HM-04 Heading chính hiển thị")
    public void homePageShowsMainHeading() {
        HomePage homePage = new HomePage(getDriver());
        Assert.assertTrue(homePage.isMainHeadingDisplayed(),
                "Trang chủ phải hiển thị heading 'Đặt vé xe khách trực tuyến'");
    }

    @Test(description = "HM-05 Nút tìm kiếm hiển thị và bấm được")
    public void homePageShowsSearchButton() {
        HomePage homePage = new HomePage(getDriver());
        Assert.assertTrue(homePage.isSearchButtonDisplayed(), "Nút tìm kiếm phải hiển thị");
        Assert.assertTrue(homePage.isSearchSubmitEnabled(), "Nút tìm kiếm phải enabled");
    }

    @Test(description = "HM-06 Form có Điểm đi Điểm đến Ngày đi")
    public void homePageSearchFormHasAllFields() {
        HomePage homePage = new HomePage(getDriver());
        Assert.assertTrue(homePage.isSearchFormDisplayed(),
                "Form tìm chuyến phải có Điểm đi, Điểm đến và ô Ngày đi");
        Assert.assertTrue(homePage.isDepartureSelectDisplayed(), "Điểm đi hiển thị");
        Assert.assertTrue(homePage.isArrivalSelectDisplayed(), "Điểm đến hiển thị");
        Assert.assertTrue(homePage.isDepartureDateDisplayed(), "Ô ngày đi hiển thị");
    }

    @Test(description = "HM-07 Ấn tìm kiếm chuyển sang trang tìm kiếm")
    public void clickSearchNavigatesToSearchPage() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickSearchSubmit();
        String url = getDriver().getCurrentUrl();
        Assert.assertTrue(url.contains("/search") || url.contains("search"),
                "Sau khi ấn tìm kiếm phải chuyển sang trang tìm kiếm");
    }

    @Test(description = "HM-08 Tiêu đề con hiển thị đúng")
    public void homePageSubtitleDisplayed() {
        HomePage homePage = new HomePage(getDriver());
        Assert.assertTrue(homePage.isSubtitleDisplayed(), "Tiêu đề con phải hiển thị");
        Assert.assertTrue(homePage.getSubtitleText().contains("Nhanh chóng"),
                "Tiêu đề con phải chứa 'Nhanh chóng'");
    }

    @Test(description = "HM-09 Hiển thị đủ 3 khối")
    public void homePageFeaturesBlockDisplayed() {
        HomePage homePage = new HomePage(getDriver());
        Assert.assertTrue(homePage.areFeaturesDisplayed(),
                "Hiển thị đủ 3 khối");
    }

    @Test(description = "HM-10 Bấm tìm kiếm khi chưa chọn thông tin")
    public void validation_searchWithoutSelection_showsToast() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickSearchSubmit();
        boolean toastShown = ValidationTestHelper.waitForToastContainingText(getDriver(), "Vui lòng điền đầy đủ thông tin");
        Assert.assertTrue(toastShown, "Phải hiển thị thông báo lỗi khi chưa chọn đủ");
    }

    @Test(description = "HM-11 Thông báo lỗi có thể hiện lần hai nếu bấm lại tìm kiếm")
    public void validation_toastOnRepeatedSearchClick() {
        getDriver().get(Config.getBaseUrl());
        HomePage homePage = new HomePage(getDriver());
        homePage.clickSearchSubmit();
        ValidationTestHelper.waitForToastContainingText(getDriver(), "Vui lòng");
        getDriver().get(Config.getBaseUrl());
        homePage = new HomePage(getDriver());
        homePage.clickSearchSubmit();
        boolean again = ValidationTestHelper.waitForToastContainingText(getDriver(), "Vui lòng");
        Assert.assertTrue(again, "Thông báo lỗi vẫn hiển thị khi lặp lại thao tác");
    }
}
