package pages.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

/**
 * Trang Quản lý Đánh giá web-admin (/reviews).
 */
public class AdminReviewsPage extends BasePage {

    @FindBy(tagName = "body")
    private WebElement body;

    public AdminReviewsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return body != null && body.isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
