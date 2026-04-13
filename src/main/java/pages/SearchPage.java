package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * Trang Tìm chuyến xe web-customer (/search).
 */
public class SearchPage extends BasePage {

    @FindBy(css = "[data-testid='search-submit']")
    private WebElement searchSubmitButton;

    @FindBy(css = "[data-testid='customer-search-heading']")
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

    public void clickSearchSubmit() {
        if (searchSubmitButton != null) {
            searchSubmitButton.click();
        }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getSearchButtonTextOrTag() {
        return searchSubmitButton != null ? searchSubmitButton.getText() : "";
    }

    public boolean isSearchSubmitEnabled() {
        return searchSubmitButton != null && searchSubmitButton.isEnabled();
    }

    /** Chọn điểm đi/đến theo index (0 = placeholder). */
    public void selectDepartureByIndex(int index) {
        if (departureSelect != null) {
            new Select(departureSelect).selectByIndex(index);
        }
    }

    public void selectArrivalByIndex(int index) {
        if (arrivalSelect != null) {
            new Select(arrivalSelect).selectByIndex(index);
        }
    }

    public int getDepartureOptionCount() {
        if (departureSelect == null) {
            return 0;
        }
        return new Select(departureSelect).getOptions().size();
    }
}
