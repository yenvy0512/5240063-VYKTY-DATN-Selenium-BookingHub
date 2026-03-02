package pages.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

/**
 * Trang Quản lý Nhà xe web-admin (/bus-companies).
 */
public class AdminBusCompaniesPage extends BasePage {

    @FindBy(css = "[data-testid='admin-bus-companies-page']")
    private WebElement page;

    @FindBy(css = "[data-testid='admin-bus-companies-btn-add']")
    private WebElement addButton;

    @FindBy(css = "[data-testid='admin-bus-companies-search-input']")
    private WebElement searchInput;

    @FindBy(xpath = "//th[contains(.,'Tên nhà xe')]")
    private WebElement headerTenNhaXe;

    @FindBy(xpath = "//th[contains(.,'Email')]")
    private WebElement headerEmail;

    public AdminBusCompaniesPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageDisplayed() {
        return page != null && page.isDisplayed();
    }

    public boolean isAddButtonDisplayed() {
        return addButton != null && addButton.isDisplayed();
    }

    public boolean hasTableHeaderTenNhaXe() {
        return headerTenNhaXe != null && headerTenNhaXe.isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
