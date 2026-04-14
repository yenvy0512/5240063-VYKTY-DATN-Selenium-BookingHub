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

public class AdminBusCompaniesPage extends BasePage {

    @FindBy(css = "[data-testid='admin-bus-companies-page']")
    private WebElement page;

    @FindBy(css = "[data-testid='admin-bus-companies-btn-add']")
    private WebElement addButton;

    @FindBy(css = "[data-testid='admin-bus-companies-search-input']")
    private WebElement searchInput;

    @FindBy(css = "[data-testid='admin-bus-companies-search-submit']")
    private WebElement searchSubmit;

    @FindBy(xpath = "//th[contains(.,'Tên nhà xe')]")
    private WebElement headerTenNhaXe;

    @FindBy(css = "[data-testid='admin-bus-companies-modal']")
    private WebElement modal;

    @FindBy(css = "[data-testid='admin-bus-companies-form'] input[name='name']")
    private WebElement inputName;

    @FindBy(css = "[data-testid='admin-bus-companies-form'] input[name='email']")
    private WebElement inputEmail;

    @FindBy(css = "[data-testid='admin-bus-companies-form'] input[name='phone']")
    private WebElement inputPhone;

    @FindBy(css = "[data-testid='admin-bus-companies-form'] textarea[name='address']")
    private WebElement inputAddress;

    @FindBy(css = "[data-testid='admin-bus-companies-form'] select[name='status']")
    private WebElement selectStatus;

    @FindBy(css = "[data-testid='admin-bus-companies-form-submit']")
    private WebElement formSubmit;

    @FindBy(css = "[data-testid='admin-bus-companies-form-cancel']")
    private WebElement formCancel;

    private static final int WAIT_SECONDS = 15;

    public AdminBusCompaniesPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageDisplayed() {
        return page != null && page.isDisplayed();
    }

    public boolean isAddButtonDisplayed() {
        return addButton != null && addButton.isDisplayed();
    }

    public boolean hasTableHeaderTenNhaXe() {
        return headerTenNhaXe != null && headerTenNhaXe.isDisplayed();
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
        List<WebElement> els = driver.findElements(By.cssSelector("[data-testid='admin-bus-companies-pagination-info']"));
        return !els.isEmpty() && els.get(0).isDisplayed();
    }

    public boolean headingContainsNhaXe() {
        List<WebElement> h1 = driver.findElements(By.xpath("//h1[contains(.,'Nhà xe')]"));
        return !h1.isEmpty() && h1.get(0).isDisplayed();
    }

    // --- CRUD ---

    public void clickAdd() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bus-companies-modal']")));
    }

    public boolean isModalDisplayed() {
        return modal != null && modal.isDisplayed();
    }

    public void fillForm(String name, String email, String phone, String address, String status) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        wait.until(ExpectedConditions.visibilityOf(inputName));
        if (inputName != null) {
            inputName.clear();
            inputName.sendKeys(name);
        }
        if (inputEmail != null) {
            inputEmail.clear();
            inputEmail.sendKeys(email != null ? email : "");
        }
        if (inputPhone != null) {
            inputPhone.clear();
            inputPhone.sendKeys(phone != null ? phone : "");
        }
        if (inputAddress != null) {
            inputAddress.clear();
            inputAddress.sendKeys(address != null ? address : "");
        }
        if (selectStatus != null && status != null) {
            new org.openqa.selenium.support.ui.Select(selectStatus).selectByValue(status);
        }
    }

    public void submitForm() {
        if (formSubmit != null) {
            formSubmit.click();
        }
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[data-testid='admin-bus-companies-modal']")));
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
                By.cssSelector("[data-testid^='admin-bus-companies-btn-edit-']")));
        btn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-bus-companies-modal']")));
    }

    public void clickDeleteFirstRow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-testid^='admin-bus-companies-btn-delete-']")));
        btn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='confirm-modal']")));
    }

    public void confirmDelete() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']")));
        btn.click();
    }

    public boolean tableContainsName(String name) {
        List<WebElement> cells = driver.findElements(By.xpath("//td[contains(.,'" + name + "')]"));
        return !cells.isEmpty();
    }

    public int getTableRowCount() {
        List<WebElement> rows = driver.findElements(By.cssSelector("[data-testid^='admin-bus-companies-row-']"));
        return rows.size();
    }
}
