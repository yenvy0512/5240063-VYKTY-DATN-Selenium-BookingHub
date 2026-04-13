package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Trang Vé của tôi web-customer (/my-bookings).
 */
public class MyBookingsPage extends BasePage {

    public MyBookingsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return driver.findElements(By.cssSelector("[data-testid='customer-my-bookings-page']")).stream().anyMatch(WebElement::isDisplayed);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isHeadingVeCuaToiDisplayed() {
        List<WebElement> h = driver.findElements(By.cssSelector("[data-testid='customer-my-bookings-heading']"));
        return !h.isEmpty() && h.get(0).isDisplayed();
    }

    public boolean hasLinkToSearchTrips() {
        return driver.findElements(By.cssSelector("[data-testid='customer-my-bookings-btn-search-trips']")).stream()
                .anyMatch(WebElement::isDisplayed);
    }

    public boolean isLoadingState() {
        return !driver.findElements(By.cssSelector("[data-testid='customer-my-bookings-loading']")).isEmpty();
    }

    public boolean isEmptyState() {
        return !driver.findElements(By.cssSelector("[data-testid='customer-my-bookings-empty']")).isEmpty();
    }
}
