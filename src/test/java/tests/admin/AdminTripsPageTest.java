package tests.admin;

import base.AdminAuthBaseTest;
import config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.AdminTripCreatePage;
import pages.admin.AdminTripsPage;
import utils.ValidationTestHelper;

public class AdminTripsPageTest extends AdminAuthBaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrlAdmin() + "/trips");
    }

    @Test(description = "TR-01 Trang Quản lý Chuyến xe hiển thị")
    public void tripsPageDisplayed() {
        AdminTripsPage page = new AdminTripsPage(getDriver());
        Assert.assertTrue(page.isPageDisplayed(), "Trang Quản lý Chuyến xe phải hiển thị");
    }

    @Test(description = "TR-02 Heading Quản lý Chuyến xe hiển thị")
    public void tripsHeadingDisplayed() {
        AdminTripsPage page = new AdminTripsPage(getDriver());
        Assert.assertTrue(page.headingContains("Chuyến"), "Heading phải chứa Chuyến");
    }

    @Test(description = "TR-03 Nút tạo chuyến hiển thị")
    public void tripsCreateButtonDisplayed() {
        AdminTripsPage page = new AdminTripsPage(getDriver());
        Assert.assertTrue(page.isCreateButtonDisplayed(), "Nút tạo chuyến phải hiển thị");
    }

    @Test(description = "TR-04 Ấn Tạo chuyến mới chuyển sang trang tạo chuyến")
    public void clickCreateNavigatesToCreatePage() {
        AdminTripsPage page = new AdminTripsPage(getDriver());
        page.clickCreate();
        String url = getDriver().getCurrentUrl();
        Assert.assertTrue(url.contains("/trips/create"), "Phải mở /trips/create");
    }

    @Test(description = "TR-05 Tiêu đề trang chuyến xe đúng")
    public void tripsPageTitle() {
        AdminTripsPage page = new AdminTripsPage(getDriver());
        String title = page.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Chuyến") || title.contains("BookingHub"),
                "Tiêu đề phải chứa Chuyến/BookingHub");
    }

    @Test(description = "TR-06 Bảng chuyến xe có cột Điểm đi và Điểm đến")
    public void tripsTableHasExpectedHeaders() {
        AdminTripsPage page = new AdminTripsPage(getDriver());
        Assert.assertTrue(page.hasTableHeaderDiemDi(), "Bảng phải có cột Điểm đi");
        Assert.assertTrue(page.hasTableHeaderDiemDen(), "Bảng phải có cột Điểm đến");
    }

    @Test(description = "TR-07 Ô tìm kiếm chuyến hiển thị")
    public void tripsSearchInputDisplayed() {
        AdminTripsPage page = new AdminTripsPage(getDriver());
        Assert.assertTrue(page.isSearchInputDisplayed(), "Ô tìm kiếm phải hiển thị");
        Assert.assertFalse(page.getSearchPlaceholder().isEmpty(), "Placeholder không rỗng");
    }

    @Test(description = "TR-08 Tìm kiếm chuyến với từ khóa")
    public void tripsSearchByKeyword() {
        AdminTripsPage page = new AdminTripsPage(getDriver());
        page.typeSearchKeyword("Hà Nội");
        page.clickSearchSubmit();
        Assert.assertTrue(page.isPageDisplayed(), "Trang vẫn hiển thị sau tìm kiếm");
    }

    @Test(description = "TR-09 Tìm kiếm chuỗi rỗng")
    public void tripsSearchEmpty() {
        AdminTripsPage page = new AdminTripsPage(getDriver());
        page.typeSearchKeyword("");
        page.clickSearchSubmit();
        Assert.assertTrue(page.isPageDisplayed(), "Trang ổn định");
    }

    @Test(description = "TR-10 Thông tin phân trang khi có dữ liệu")
    public void tripsPaginationWhenHasRows() {
        AdminTripsPage page = new AdminTripsPage(getDriver());
        if (page.getTableRowCount() > 0) {
            Assert.assertTrue(page.isPaginationInfoDisplayed(), "Hiển thị phân trang");
            Assert.assertFalse(page.getPaginationInfoText().isEmpty(), "Text phân trang không rỗng");
        }
    }

    @Test(description = "TR-11 Trang tạo chuyến hiển thị form cơ bản")
    public void tripCreatePageForm() {
        getDriver().get(Config.getBaseUrlAdmin() + "/trips/create");
        AdminTripCreatePage createPage = new AdminTripCreatePage(getDriver());
        Assert.assertTrue(createPage.isHeadingDisplayed(), "Heading Tạo Chuyến hiển thị");
        Assert.assertTrue(createPage.hasVehicleSelect(), "Có chọn phương tiện");
        Assert.assertTrue(createPage.hasDepartureSelect(), "Có điểm đi");
        Assert.assertTrue(createPage.hasArrivalSelect(), "Có điểm đến");
        Assert.assertTrue(createPage.hasBasePriceInput(), "Có giá vé");
    }

    @Test(description = "TR-12 Trang tạo chuyến tiêu đề đúng")
    public void tripCreatePageTitle() {
        getDriver().get(Config.getBaseUrlAdmin() + "/trips/create");
        AdminTripCreatePage createPage = new AdminTripCreatePage(getDriver());
        String t = createPage.getPageTitle();
        Assert.assertTrue(t.contains("Chuyến") || t.contains("BookingHub"), "Tiêu đề hợp lệ");
    }

    @Test(description = "TR-13 Gửi thông tin tạo chuyến khi chưa chọn phương tiện hiện thông báo lỗi")
    public void tripCreateValidationNoVehicle() {
        getDriver().get(Config.getBaseUrlAdmin() + "/trips/create");
        AdminTripCreatePage createPage = new AdminTripCreatePage(getDriver());
        createPage.clickSubmit();
        boolean toast = ValidationTestHelper.waitForToastContainingText(getDriver(), "phương tiện");
        Assert.assertTrue(toast, "Phải có thông báo nhắc chọn phương tiện");
    }

    @Test(description = "TR-14 Mở sửa chuyến khi có ít nhất một dòng")
    public void openEditModalWhenRowExists() {
        AdminTripsPage page = new AdminTripsPage(getDriver());
        if (page.getTableRowCount() == 0) {
            return;
        }
        page.clickEditFirstRow();
        Assert.assertTrue(page.isModalDisplayed(), "Modal sửa chuyến mở");
        page.clickModalCancel();
    }

    @Test(description = "TR-15 Xóa chuyến khi có dữ liệu (xác nhận hủy)")
    public void deleteTripCancelConfirm() {
        AdminTripsPage page = new AdminTripsPage(getDriver());
        if (page.getTableRowCount() == 0) {
            return;
        }
        page.clickDeleteFirstRow();
        getDriver().findElement(org.openqa.selenium.By.cssSelector("[data-testid='confirm-modal-cancel']")).click();
        Assert.assertTrue(page.isPageDisplayed(), "Vẫn ở trang danh sách");
    }
}
