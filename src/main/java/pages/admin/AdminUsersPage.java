package pages.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

/**
 * Trang Quản lý Người dùng web-admin (/users).
 */
public class AdminUsersPage extends BasePage {

    @FindBy(css = "[data-testid='admin-users-page']")
    private WebElement page;

    @FindBy(css = "[data-testid='admin-users-btn-add']")
    private WebElement addButton;

    @FindBy(css = "[data-testid='admin-users-table']")
    private WebElement table;

    public AdminUsersPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageDisplayed() {
        return page != null && page.isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
