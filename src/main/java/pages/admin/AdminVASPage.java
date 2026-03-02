package pages.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

/**
 * Trang Quản lý Dịch vụ (VAS) web-admin (/vas).
 */
public class AdminVASPage extends BasePage {

    @FindBy(css = "[data-testid='admin-vas-page']")
    private WebElement page;

    @FindBy(css = "[data-testid='admin-vas-btn-add']")
    private WebElement addButton;

    @FindBy(css = "[data-testid='admin-vas-table']")
    private WebElement table;

    public AdminVASPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageDisplayed() {
        return page != null && page.isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
