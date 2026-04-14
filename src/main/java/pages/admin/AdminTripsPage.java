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

public class AdminTripsPage extends BasePage {

    @FindBy(css = "[data-testid='admin-trips-page']")
    private WebElement page;

    @FindBy(css = "[data-testid='admin-trips-btn-create']")
    private WebElement createButton;

    @FindBy(css = "[data-testid='admin-trips-search-input']")
    private WebElement searchInput;

    @FindBy(css = "[data-testid='admin-trips-search-submit']")
    private WebElement searchSubmit;

    @FindBy(xpath = "//th[contains(.,'Điểm đi')]")
    private WebElement headerDiemDi;

    @FindBy(xpath = "//th[contains(.,'Điểm đến')]")
    private WebElement headerDiemDen;

    @FindBy(css = "[data-testid='admin-trips-modal']")
    private WebElement modal;

    private static final int WAIT_SECONDS = 15;

    public AdminTripsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageDisplayed() {
        return page != null && page.isDisplayed();
    }

    public boolean isCreateButtonDisplayed() {
        return createButton != null && createButton.isDisplayed();
    }

    public void clickCreate() {
        if (createButton != null) {
            createButton.click();
        }
    }

    public boolean hasTableHeaderDiemDi() {
        return headerDiemDi != null && headerDiemDi.isDisplayed();
    }

    public boolean hasTableHeaderDiemDen() {
        return headerDiemDen != null && headerDiemDen.isDisplayed();
    }

    public boolean isSearchInputDisplayed() {
        return searchInput != null && searchInput.isDisplayed();
    }

    public String getSearchPlaceholder() {
        return searchInput != null ? searchInput.getAttribute("placeholder") : "";
    }

    public void typeSearchKeyword(String keyword) {
        if (searchInput != null) {
            searchInput.clear();
            searchInput.sendKeys(keyword != null ? keyword : "");
        }
    }

    public void clickSearchSubmit() {
        if (searchSubmit != null) {
            searchSubmit.click();
        }
    }

    public boolean isPaginationInfoDisplayed() {
        List<WebElement> els = driver.findElements(By.cssSelector("[data-testid='admin-trips-pagination-info']"));
        return !els.isEmpty() && els.get(0).isDisplayed();
    }

    public String getPaginationInfoText() {
        List<WebElement> els = driver.findElements(By.cssSelector("[data-testid='admin-trips-pagination-info']"));
        return els.isEmpty() ? "" : els.get(0).getText();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public int getTableRowCount() {
        List<WebElement> rows = driver.findElements(By.cssSelector("[data-testid^='admin-trips-row-']"));
        return rows.size();
    }

    public boolean isModalDisplayed() {
        List<WebElement> modals = driver.findElements(By.cssSelector("[data-testid='admin-trips-modal']"));
        return !modals.isEmpty() && modals.get(0).isDisplayed();
    }

    public void clickEditFirstRow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("[data-testid^='admin-trips-btn-edit-']")));
        btn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-trips-modal']")));
    }

    public void clickDeleteFirstRow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-testid^='admin-trips-btn-delete-']")));
        btn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='confirm-modal']")));
    }

    public void confirmDelete() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']")));
        btn.click();
    }

    public void clickModalCancel() {
        WebElement btn = driver.findElement(By.cssSelector("[data-testid='admin-trips-form-cancel']"));
        btn.click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[data-testid='admin-trips-modal']")));
    }

    public boolean headingContains(String text) {
        List<WebElement> h1 = driver.findElements(By.xpath("//h1[contains(.,'" + text + "')]"));
        return !h1.isEmpty() && h1.get(0).isDisplayed();
    }
}
