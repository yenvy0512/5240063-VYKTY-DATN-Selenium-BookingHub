package tests.customer;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.ValidationTestHelper;

/**
 * Test case trang chủ web-customer.
 */
public class HomePageTest extends BaseTest {

    @Test(description = "Trang chủ load thành công")
    public void homePageLoads() {
        HomePage homePage = new HomePage(getDriver());
        Assert.assertTrue(homePage.isPageLoaded(), "Trang chủ phải load được");
    }

    @Test(description = "Title trang chủ đúng")
    public void homePageTitle() {
        HomePage homePage = new HomePage(getDriver());
        String title = homePage.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("BookingHub") || title.contains("Đặt vé"),
                "Title phải chứa BookingHub hoặc Đặt vé");
    }

    @Test(description = "Heading chính hiển thị")
    public void homePageShowsMainHeading() {
        HomePage homePage = new HomePage(getDriver());
        Assert.assertTrue(homePage.isMainHeadingDisplayed(),
                "Trang chủ phải hiển thị heading 'Đặt vé xe khách trực tuyến'");
    }

    @Test(description = "Nút tìm kiếm hiển thị")
    public void homePageShowsSearchButton() {
        HomePage homePage = new HomePage(getDriver());
        Assert.assertTrue(homePage.isSearchButtonDisplayed(), "Nút tìm kiếm phải hiển thị");
    }

    @Test(description = "Click tìm kiếm chuyển sang trang search")
    public void clickSearchNavigatesToSearchPage() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickSearchSubmit();
        String url = getDriver().getCurrentUrl();
        Assert.assertTrue(url.contains("/search") || url.contains("search"),
                "Sau khi click tìm kiếm phải chuyển sang trang search");
    }

    @Test(description = "Form tìm chuyến có đủ Điểm đi, Điểm đến, Ngày đi")
    public void homePageSearchFormHasAllFields() {
        HomePage homePage = new HomePage(getDriver());
        Assert.assertTrue(homePage.isSearchFormDisplayed(),
                "Form tìm chuyến phải có select Điểm đi, Điểm đến và ô Ngày đi");
    }

    @Test(description = "Subtitle hiển thị đúng 'Nhanh chóng - An toàn - Tiện lợi'")
    public void homePageSubtitleDisplayed() {
        HomePage homePage = new HomePage(getDriver());
        Assert.assertTrue(homePage.isSubtitleDisplayed(), "Subtitle phải hiển thị");
        Assert.assertTrue(homePage.getSubtitleText().contains("Nhanh chóng"),
                "Subtitle phải chứa 'Nhanh chóng'");
    }

    @Test(description = "Khối 3 tính năng (Nhiều nhà xe, Đặt vé nhanh, An toàn) hiển thị")
    public void homePageFeaturesBlockDisplayed() {
        HomePage homePage = new HomePage(getDriver());
        Assert.assertTrue(homePage.areFeaturesDisplayed(),
                "Phải hiển thị 3 tính năng: Nhiều nhà xe uy tín, Đặt vé nhanh chóng, An toàn & bảo mật");
    }

    @Test(description = "Bấm tìm kiếm khi chưa chọn đủ thông tin hiện toast validation")
    public void validation_searchWithoutSelection_showsToast() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickSearchSubmit();
        boolean toastShown = ValidationTestHelper.waitForToastContainingText(getDriver(), "Vui lòng điền đầy đủ thông tin");
        Assert.assertTrue(toastShown, "Phải hiển thị toast validation khi chưa chọn điểm đi/đến/ngày");
    }
}
