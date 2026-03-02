package pages.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

/**
 * Trang Quản lý Địa điểm web-admin (/locations).
 */
public class AdminLocationsPage extends BasePage {

    @FindBy(css = "[data-testid='admin-locations-page']")
    private WebElement page;

    @FindBy(css = "[data-testid='admin-locations-btn-add']")
    private WebElement addButton;

    @FindBy(css = "[data-testid='admin-locations-search-input']")
    private WebElement searchInput;

    @FindBy(xpath = "//th[contains(.,'Thành phố')]")
    private WebElement headerThanhPho;

    @FindBy(xpath = "//th[contains(.,'Địa chỉ')]")
    private WebElement headerDiaChi;

    public AdminLocationsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageDisplayed() {
        return page != null && page.isDisplayed();
    }

    public boolean isAddButtonDisplayed() {
        return addButton != null && addButton.isDisplayed();
    }

    public boolean hasTableHeaderThanhPho() {
        return headerThanhPho != null && headerThanhPho.isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
