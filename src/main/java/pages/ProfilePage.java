package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Trang Thông tin cá nhân web-customer (/profile).
 */
public class ProfilePage extends BasePage {

    @FindBy(tagName = "body")
    private WebElement body;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return body != null && body.isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
