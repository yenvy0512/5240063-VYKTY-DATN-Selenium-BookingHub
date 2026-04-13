package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Trang Thanh toán web-customer (/payment).
 */
public class PaymentPage extends BasePage {

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return driver.findElements(By.cssSelector("[data-testid='customer-payment-page']")).stream().anyMatch(WebElement::isDisplayed)
                || driver.findElements(By.cssSelector("[data-testid='customer-payment-loading']")).stream().anyMatch(WebElement::isDisplayed)
                || driver.findElements(By.cssSelector("[data-testid='customer-payment-empty']")).stream().anyMatch(WebElement::isDisplayed);
    }

    public boolean isLoadingState() {
        return !driver.findElements(By.cssSelector("[data-testid='customer-payment-loading']")).isEmpty();
    }

    public boolean isEmptyState() {
        return !driver.findElements(By.cssSelector("[data-testid='customer-payment-empty']")).isEmpty();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isHeadingThanhToanDisplayed() {
        List<WebElement> h = driver.findElements(By.cssSelector("[data-testid='customer-payment-heading']"));
        return !h.isEmpty() && h.get(0).isDisplayed();
    }

    public boolean isNoBookingMessageDisplayed() {
        return !driver.findElements(By.cssSelector("[data-testid='customer-payment-empty']")).isEmpty();
    }

    public boolean isLoadingSpinnerDisplayed() {
        return isLoadingState();
    }

    public boolean hasPaymentMethodSection() {
        List<WebElement> els = driver.findElements(By.cssSelector("[data-testid='customer-payment-methods']"));
        return !els.isEmpty() && els.get(0).isDisplayed();
    }

    public boolean hasBookingSummarySection() {
        List<WebElement> els = driver.findElements(By.cssSelector("[data-testid='customer-payment-booking-summary']"));
        return !els.isEmpty() && els.get(0).isDisplayed();
    }
}
