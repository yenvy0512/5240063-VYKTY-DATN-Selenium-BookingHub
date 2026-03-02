package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Trang Tìm chuyến xe web-customer (/search).
 */
public class SearchPage extends BasePage {

    @FindBy(css = "[data-testid='search-submit']")
    private WebElement searchSubmitButton;

    @FindBy(xpath = "//h1[contains(.,'Tìm chuyến xe')]")
    private WebElement headingSearch;

    @FindBy(id = "search-departureLocationId")
    private WebElement departureSelect;

    @FindBy(id = "search-arrivalLocationId")
    private WebElement arrivalSelect;

    @FindBy(id = "search-departureDate")
    private WebElement departureDateInput;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSearchFormDisplayed() {
        return searchSubmitButton != null && searchSubmitButton.isDisplayed();
    }

    public boolean isHeadingTìmChuyếnDisplayed() {
        return headingSearch != null && headingSearch.isDisplayed();
    }

    public String getHeadingText() {
        return headingSearch != null ? headingSearch.getText() : "";
    }

    public boolean hasDepartureSelect() {
        return departureSelect != null && departureSelect.isDisplayed();
    }

    public boolean hasArrivalSelect() {
        return arrivalSelect != null && arrivalSelect.isDisplayed();
    }

    public boolean hasDepartureDateInput() {
        return departureDateInput != null && departureDateInput.isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
