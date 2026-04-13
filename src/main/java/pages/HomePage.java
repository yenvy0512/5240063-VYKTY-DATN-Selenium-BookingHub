package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Trang chủ web-customer. Có form tìm chuyến và nút tìm kiếm.
 */
public class HomePage extends BasePage {

    @FindBy(tagName = "body")
    private WebElement body;

    @FindBy(css = "[data-testid='customer-home-heading']")
    private WebElement mainHeading;

    @FindBy(css = "[data-testid='customer-home-subtitle']")
    private WebElement subtitle;

    @FindBy(css = "[data-testid='home-search-submit']")
    private WebElement searchSubmitButton;

    @FindBy(id = "departureLocationId")
    private WebElement departureSelect;

    @FindBy(id = "arrivalLocationId")
    private WebElement arrivalSelect;

    @FindBy(id = "departureDate")
    private WebElement departureDateInput;

    @FindBy(css = "[data-testid='customer-home-feature-0']")
    private WebElement feature1;

    @FindBy(css = "[data-testid='customer-home-feature-1']")
    private WebElement feature2;

    @FindBy(css = "[data-testid='customer-home-feature-2']")
    private WebElement feature3;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return body != null && body.isDisplayed();
    }

    public boolean isMainHeadingDisplayed() {
        return mainHeading != null && mainHeading.isDisplayed();
    }

    public boolean isSubtitleDisplayed() {
        return subtitle != null && subtitle.isDisplayed();
    }

    public String getSubtitleText() {
        return subtitle != null ? subtitle.getText() : "";
    }

    public boolean isSearchFormDisplayed() {
        return departureSelect != null && arrivalSelect != null && departureDateInput != null;
    }

    public boolean isDepartureSelectDisplayed() {
        return departureSelect != null && departureSelect.isDisplayed();
    }

    public boolean isArrivalSelectDisplayed() {
        return arrivalSelect != null && arrivalSelect.isDisplayed();
    }

    public boolean isDepartureDateDisplayed() {
        return departureDateInput != null && departureDateInput.isDisplayed();
    }

    public boolean isSearchSubmitEnabled() {
        return searchSubmitButton != null && searchSubmitButton.isEnabled();
    }

    public boolean isSearchButtonDisplayed() {
        return searchSubmitButton != null && searchSubmitButton.isDisplayed();
    }

    public void clickSearchSubmit() {
        if (searchSubmitButton != null) {
            searchSubmitButton.click();
        }
    }

    public boolean areFeaturesDisplayed() {
        return (feature1 != null && feature1.isDisplayed())
                && (feature2 != null && feature2.isDisplayed())
                && (feature3 != null && feature3.isDisplayed());
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
