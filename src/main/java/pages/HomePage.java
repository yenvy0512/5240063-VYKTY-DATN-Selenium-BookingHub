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

    @FindBy(xpath = "//h1[contains(.,'Đặt vé xe khách trực tuyến')]")
    private WebElement mainHeading;

    @FindBy(xpath = "//p[contains(.,'Nhanh chóng - An toàn - Tiện lợi')]")
    private WebElement subtitle;

    @FindBy(css = "[data-testid='home-search-submit']")
    private WebElement searchSubmitButton;

    @FindBy(id = "departureLocationId")
    private WebElement departureSelect;

    @FindBy(id = "arrivalLocationId")
    private WebElement arrivalSelect;

    @FindBy(id = "departureDate")
    private WebElement departureDateInput;

    @FindBy(xpath = "//h3[contains(.,'Nhiều nhà xe uy tín')]")
    private WebElement feature1;

    @FindBy(xpath = "//h3[contains(.,'Đặt vé nhanh chóng')]")
    private WebElement feature2;

    @FindBy(xpath = "//h3[contains(.,'An toàn & bảo mật')]")
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
