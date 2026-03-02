package pages.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

/**
 * Dashboard web-admin (/).
 */
public class AdminDashboardPage extends BasePage {

    @FindBy(css = "[data-testid='admin-dashboard']")
    private WebElement dashboard;

    public AdminDashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDashboardDisplayed() {
        return dashboard != null && dashboard.isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
