package pages.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

/**
 * Trang Quản lý Đặt vé web-admin (/bookings).
 */
public class AdminBookingsPage extends BasePage {

    @FindBy(css = "[data-testid='admin-bookings-page']")
    private WebElement page;

    @FindBy(css = "[data-testid='admin-bookings-search-input']")
    private WebElement searchInput;

    @FindBy(css = "[data-testid='admin-bookings-search-submit']")
    private WebElement searchSubmit;

    @FindBy(xpath = "//th[contains(.,'Mã đặt vé')]")
    private WebElement headerMaDatVe;

    @FindBy(xpath = "//th[contains(.,'Khách hàng')]")
    private WebElement headerKhachHang;

    @FindBy(xpath = "//th[contains(.,'Trạng thái')]")
    private WebElement headerTrangThai;

    @FindBy(css = "[data-testid='admin-bookings-pagination-info']")
    private WebElement paginationInfo;

    @FindBy(css = "[data-testid='admin-bookings-pagination-prev']")
    private WebElement paginationPrev;

    @FindBy(css = "[data-testid='admin-bookings-pagination-next']")
    private WebElement paginationNext;

    public AdminBookingsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageDisplayed() {
        return page != null && page.isDisplayed();
    }

    public boolean isSearchDisplayed() {
        return searchInput != null && searchInput.isDisplayed();
    }

    public String getSearchPlaceholder() {
        return searchInput != null ? searchInput.getAttribute("placeholder") : "";
    }

    public boolean hasTableHeaderMaDatVe() {
        return headerMaDatVe != null && headerMaDatVe.isDisplayed();
    }

    public boolean hasTableHeaderKhachHang() {
        return headerKhachHang != null && headerKhachHang.isDisplayed();
    }

    public boolean hasTableHeaderTrangThai() {
        return headerTrangThai != null && headerTrangThai.isDisplayed();
    }

    public boolean isPaginationDisplayed() {
        return paginationInfo != null && paginationInfo.isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
