package pages.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

/**
 * Trang Quản lý Chuyến xe web-admin (/trips).
 */
public class AdminTripsPage extends BasePage {

    @FindBy(css = "[data-testid='admin-trips-page']")
    private WebElement page;

    @FindBy(css = "[data-testid='admin-trips-btn-create']")
    private WebElement createButton;

    @FindBy(css = "[data-testid='admin-trips-search-input']")
    private WebElement searchInput;

    @FindBy(xpath = "//th[contains(.,'Điểm đi')]")
    private WebElement headerDiemDi;

    @FindBy(xpath = "//th[contains(.,'Điểm đến')]")
    private WebElement headerDiemDen;

    @FindBy(css = "[data-testid='admin-trips-modal']")
    private WebElement modal;

    public AdminTripsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageDisplayed() {
        return page != null && page.isDisplayed();
    }

    public boolean isCreateButtonDisplayed() {
        return createButton != null && createButton.isDisplayed();
    }

    public void clickCreate() {
        if (createButton != null) {
            createButton.click();
        }
    }

    public boolean hasTableHeaderDiemDi() {
        return headerDiemDi != null && headerDiemDi.isDisplayed();
    }

    public boolean hasTableHeaderDiemDen() {
        return headerDiemDen != null && headerDiemDen.isDisplayed();
    }

    public boolean isSearchInputDisplayed() {
        return searchInput != null && searchInput.isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
