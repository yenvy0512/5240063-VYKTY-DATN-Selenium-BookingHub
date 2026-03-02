package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Trang Đặt vé web-customer (/booking).
 */
public class BookingPage extends BasePage {

    @FindBy(css = "[data-testid='booking-form']")
    private WebElement bookingForm;

    @FindBy(css = "[data-testid='booking-customer-name']")
    private WebElement customerNameInput;

    @FindBy(css = "[data-testid='booking-customer-phone']")
    private WebElement customerPhoneInput;

    @FindBy(css = "[data-testid='booking-customer-email']")
    private WebElement customerEmailInput;

    @FindBy(css = "[data-testid='booking-payment-method']")
    private WebElement paymentMethodSelect;

    @FindBy(css = "[data-testid='booking-submit']")
    private WebElement submitButton;

    public BookingPage(WebDriver driver) {
        super(driver);
    }

    public boolean isBookingFormDisplayed() {
        return bookingForm != null && bookingForm.isDisplayed();
    }

    public boolean hasCustomerNameField() {
        return customerNameInput != null && customerNameInput.isDisplayed();
    }

    public boolean hasCustomerPhoneField() {
        return customerPhoneInput != null && customerPhoneInput.isDisplayed();
    }

    public boolean hasCustomerEmailField() {
        return customerEmailInput != null && customerEmailInput.isDisplayed();
    }

    public boolean hasPaymentMethodField() {
        return paymentMethodSelect != null && paymentMethodSelect.isDisplayed();
    }

    public boolean isSubmitButtonDisplayed() {
        return submitButton != null && submitButton.isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
