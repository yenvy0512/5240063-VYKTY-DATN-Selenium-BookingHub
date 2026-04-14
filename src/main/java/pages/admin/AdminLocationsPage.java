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

public class AdminLocationsPage extends BasePage {

    @FindBy(css = "[data-testid='admin-locations-page']")
    private WebElement page;

    @FindBy(css = "[data-testid='admin-locations-btn-add']")
    private WebElement addButton;

    @FindBy(css = "[data-testid='admin-locations-search-input']")
    private WebElement searchInput;

    @FindBy(css = "[data-testid='admin-locations-search-submit']")
    private WebElement searchSubmit;

    @FindBy(xpath = "//th[contains(.,'Thành phố')]")
    private WebElement headerThanhPho;

    @FindBy(css = "[data-testid='admin-locations-modal']")
    private WebElement modal;

    @FindBy(css = "[data-testid='admin-locations-form']")
    private WebElement form;

    @FindBy(css = "[data-testid='admin-locations-form'] input[name='city']")
    private WebElement inputCity;

    @FindBy(css = "[data-testid='admin-locations-form'] input[name='district']")
    private WebElement inputDistrict;

    @FindBy(css = "[data-testid='admin-locations-form'] textarea[name='streetAddress']")
    private WebElement inputStreetAddress;

    @FindBy(css = "[data-testid='admin-locations-form'] select[name='type']")
    private WebElement selectType;

    @FindBy(css = "[data-testid='admin-locations-form-submit']")
    private WebElement formSubmit;

    @FindBy(css = "[data-testid='admin-locations-form-cancel']")
    private WebElement formCancel;

    @FindBy(css = "[data-testid='confirm-modal-confirm']")
    private WebElement confirmDeleteButton;

    @FindBy(css = "[data-testid='confirm-modal-cancel']")
    private WebElement confirmCancelButton;

    private static final int WAIT_SECONDS = 15;

    public AdminLocationsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageDisplayed() {
        return page != null && page.isDisplayed();
    }

    public boolean isAddButtonDisplayed() {
        return addButton != null && addButton.isDisplayed();
    }

    public boolean hasTableHeaderThanhPho() {
        return headerThanhPho != null && headerThanhPho.isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isSearchInputDisplayed() {
        return searchInput != null && searchInput.isDisplayed();
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
        List<WebElement> els = driver.findElements(By.cssSelector("[data-testid='admin-locations-pagination-info']"));
        return !els.isEmpty() && els.get(0).isDisplayed();
    }

    public boolean headingContainsQuanLyDiaDiem() {
        List<WebElement> h1 = driver.findElements(By.xpath("//h1[contains(.,'Địa điểm')]"));
        return !h1.isEmpty() && h1.get(0).isDisplayed();
    }

    // --- CRUD ---

    public void clickAdd() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-locations-modal']")));
    }

    public boolean isModalDisplayed() {
        return modal != null && modal.isDisplayed();
    }

    public void fillForm(String city, String district, String streetAddress, String type) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        wait.until(ExpectedConditions.visibilityOf(inputCity));
        if (inputCity != null) {
            inputCity.clear();
            inputCity.sendKeys(city);
        }
        if (inputDistrict != null) {
            inputDistrict.clear();
            inputDistrict.sendKeys(district);
        }
        if (inputStreetAddress != null) {
            inputStreetAddress.clear();
            inputStreetAddress.sendKeys(streetAddress != null ? streetAddress : "");
        }
        if (selectType != null && type != null) {
            org.openqa.selenium.support.ui.Select select = new org.openqa.selenium.support.ui.Select(selectType);
            select.selectByValue(type);
        }
    }

    public void submitForm() {
        if (formSubmit != null) {
            formSubmit.click();
        }
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[data-testid='admin-locations-modal']")));
    }

    /** Chỉ click nút Lưu (dùng cho test validation: form trống thì modal vẫn mở). */
    public void clickSubmitOnly() {
        if (formSubmit != null) {
            formSubmit.click();
        }
    }

    public void cancelForm() {
        if (formCancel != null) {
            formCancel.click();
        }
    }

    public void clickEditFirstRow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        WebElement btn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("[data-testid^='admin-locations-btn-edit-']")));
        btn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-locations-modal']")));
    }

    public void clickDeleteFirstRow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-testid^='admin-locations-btn-delete-']")));
        btn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='confirm-modal']")));
    }

    public void confirmDelete() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']")));
        btn.click();
    }

    public void cancelConfirmDelete() {
        if (confirmCancelButton != null && confirmCancelButton.isDisplayed()) {
            confirmCancelButton.click();
        }
    }

    public boolean tableContainsCity(String city) {
        List<WebElement> cells = driver.findElements(By.xpath("//td[contains(.,'" + city + "')]"));
        return !cells.isEmpty();
    }

    public int getTableRowCount() {
        List<WebElement> rows = driver.findElements(By.cssSelector("[data-testid^='admin-locations-row-']"));
        return rows.size();
    }
}
