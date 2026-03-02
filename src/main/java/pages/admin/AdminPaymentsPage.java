package pages.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

/**
 * Trang Quản lý Thanh toán web-admin (/payments).
 */
public class AdminPaymentsPage extends BasePage {

    @FindBy(tagName = "body")
    private WebElement body;

    public AdminPaymentsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return body != null && body.isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
