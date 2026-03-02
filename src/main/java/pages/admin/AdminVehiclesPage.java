package pages.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

/**
 * Trang Quản lý Xe web-admin (/vehicles).
 */
public class AdminVehiclesPage extends BasePage {

    @FindBy(css = "[data-testid='admin-vehicles-page']")
    private WebElement page;

    @FindBy(css = "[data-testid='admin-vehicles-btn-add']")
    private WebElement addButton;

    @FindBy(css = "[data-testid='admin-vehicles-search-input']")
    private WebElement searchInput;

    @FindBy(xpath = "//th[contains(.,'Tên xe')]")
    private WebElement headerTenXe;

    @FindBy(xpath = "//th[contains(.,'Biển số')]")
    private WebElement headerBienSo;

    public AdminVehiclesPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageDisplayed() {
        return page != null && page.isDisplayed();
    }

    public boolean isAddButtonDisplayed() {
        return addButton != null && addButton.isDisplayed();
    }

    public boolean hasTableHeaderTenXe() {
        return headerTenXe != null && headerTenXe.isDisplayed();
    }

    public boolean hasTableHeaderBienSo() {
        return headerBienSo != null && headerBienSo.isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
