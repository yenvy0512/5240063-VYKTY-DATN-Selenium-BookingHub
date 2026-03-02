package pages.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

/**
 * Trang Thông báo web-admin (/notifications).
 */
public class AdminNotificationsPage extends BasePage {

    @FindBy(css = "[data-testid='admin-notifications-page']")
    private WebElement page;

    @FindBy(css = "[data-testid='admin-notifications-btn-refresh']")
    private WebElement refreshButton;

    public AdminNotificationsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageDisplayed() {
        return page != null && page.isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
