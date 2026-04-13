package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.BasePage;

import java.util.List;

/**
 * Trang Thông tin cá nhân web-customer (/profile).
 */
public class ProfilePage extends BasePage {

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return driver.findElements(By.cssSelector("[data-testid='customer-profile-page']")).stream().anyMatch(WebElement::isDisplayed)
                || driver.findElements(By.cssSelector("[data-testid='customer-profile-loading']")).stream().anyMatch(WebElement::isDisplayed);
    }

    public boolean isLoadingState() {
        return !driver.findElements(By.cssSelector("[data-testid='customer-profile-loading']")).isEmpty();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isHeadingDisplayed() {
        List<WebElement> h = driver.findElements(By.cssSelector("[data-testid='customer-profile-heading']"));
        return !h.isEmpty() && h.get(0).isDisplayed();
    }

    public boolean hasNameField() {
        List<WebElement> els = driver.findElements(By.cssSelector("[data-testid='customer-profile-input-name']"));
        return !els.isEmpty() && els.get(0).isDisplayed();
    }

    public boolean hasEmailField() {
        List<WebElement> els = driver.findElements(By.cssSelector("[data-testid='customer-profile-input-email']"));
        return !els.isEmpty() && els.get(0).isDisplayed();
    }

    public boolean hasPhoneField() {
        List<WebElement> els = driver.findElements(By.cssSelector("[data-testid='customer-profile-input-phone']"));
        return !els.isEmpty() && els.get(0).isDisplayed();
    }

    public void fillName(String name) {
        WebElement el = driver.findElement(By.cssSelector("[data-testid='customer-profile-input-name']"));
        el.clear();
        el.sendKeys(name);
    }

    public void clickSaveProfile() {
        driver.findElement(By.cssSelector("[data-testid='customer-profile-submit']")).click();
    }

    public boolean hasSubmitSaveButton() {
        List<WebElement> els = driver.findElements(By.cssSelector("[data-testid='customer-profile-submit']"));
        return !els.isEmpty() && els.get(0).isDisplayed();
    }
}
