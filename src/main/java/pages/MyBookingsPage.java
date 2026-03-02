package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Trang Vé của tôi web-customer (/my-bookings).
 */
public class MyBookingsPage extends BasePage {

    @FindBy(tagName = "body")
    private WebElement body;

    public MyBookingsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return body != null && body.isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
