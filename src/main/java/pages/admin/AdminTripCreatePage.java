package pages.admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

import java.time.Duration;
import java.util.List;

public class AdminTripCreatePage extends BasePage {

    @FindBy(css = "[data-testid='admin-trip-create-page']")
    private WebElement pageRoot;

    @FindBy(css = "[data-testid='admin-trip-create-heading']")
    private WebElement heading;

    @FindBy(css = "[data-testid='admin-trip-create-form']")
    private WebElement form;

    @FindBy(css = "[data-testid='admin-trip-create-submit']")
    private WebElement submitBtn;

    @FindBy(css = "[data-testid='admin-trip-create-back']")
    private WebElement backBtn;

    private static final int WAIT_SECONDS = 15;

    public AdminTripCreatePage(WebDriver driver) {
        super(driver);
    }

    public boolean isHeadingDisplayed() {
        List<WebElement> h = driver.findElements(By.cssSelector("[data-testid='admin-trip-create-heading']"));
        return !h.isEmpty() && h.get(0).isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean hasVehicleSelect() {
        List<WebElement> els = driver.findElements(By.cssSelector("select[name='vehicleId']"));
        return !els.isEmpty() && els.get(0).isDisplayed();
    }

    public boolean hasDepartureSelect() {
        List<WebElement> els = driver.findElements(By.cssSelector("select[name='departureLocationId']"));
        return !els.isEmpty() && els.get(0).isDisplayed();
    }

    public boolean hasArrivalSelect() {
        List<WebElement> els = driver.findElements(By.cssSelector("select[name='arrivalLocationId']"));
        return !els.isEmpty() && els.get(0).isDisplayed();
    }

    public boolean hasBasePriceInput() {
        List<WebElement> els = driver.findElements(By.cssSelector("input[name='basePrice']"));
        return !els.isEmpty() && els.get(0).isDisplayed();
    }

    /** Click nút submit form tạo chuyến (kiểm tra validation). */
    public void clickSubmit() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-testid='admin-trip-create-submit']")));
        btn.click();
    }

    public void clickBackToTripsList() {
        if (backBtn != null) {
            backBtn.click();
        }
    }
}
