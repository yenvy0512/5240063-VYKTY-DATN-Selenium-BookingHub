package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Trang Thanh toán web-customer (/payment).
 */
public class PaymentPage extends BasePage {

    @FindBy(tagName = "body")
    private WebElement body;

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return body != null && body.isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
