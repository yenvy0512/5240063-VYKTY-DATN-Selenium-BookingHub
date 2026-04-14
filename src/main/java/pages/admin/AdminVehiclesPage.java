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

public class AdminVehiclesPage extends BasePage {

    @FindBy(css = "[data-testid='admin-vehicles-page']")
    private WebElement page;

    @FindBy(css = "[data-testid='admin-vehicles-btn-add']")
    private WebElement addButton;

    @FindBy(css = "[data-testid='admin-vehicles-search-input']")
    private WebElement searchInput;

    @FindBy(css = "[data-testid='admin-vehicles-search-submit']")
    private WebElement searchSubmit;

    @FindBy(xpath = "//th[contains(.,'Tên xe')]")
    private WebElement headerTenXe;

    @FindBy(xpath = "//th[contains(.,'Biển số')]")
    private WebElement headerBienSo;

    @FindBy(css = "[data-testid='admin-vehicles-modal']")
    private WebElement modal;

    @FindBy(css = "[data-testid='admin-vehicles-form'] input[name='busName']")
    private WebElement inputBusName;

    @FindBy(css = "[data-testid='admin-vehicles-form'] select[name='busType']")
    private WebElement selectBusType;

    @FindBy(css = "[data-testid='admin-vehicles-form'] input[name='licensePlate']")
    private WebElement inputLicensePlate;

    @FindBy(css = "[data-testid='admin-vehicles-form'] input[name='totalSeats']")
    private WebElement inputTotalSeats;

    @FindBy(css = "[data-testid='admin-vehicles-form'] select[name='status']")
    private WebElement selectStatus;

    @FindBy(css = "[data-testid='admin-vehicles-form-submit']")
    private WebElement formSubmit;

    @FindBy(css = "[data-testid='admin-vehicles-form-cancel']")
    private WebElement formCancel;

    private static final int WAIT_SECONDS = 15;

    public AdminVehiclesPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageDisplayed() {
        return page != null && page.isDisplayed();
    }

    public boolean isAddButtonDisplayed() {
        return addButton != null && addButton.isDisplayed();
    }

    public boolean hasTableHeaderTenXe() {
        return headerTenXe != null && headerTenXe.isDisplayed();
    }

    public boolean hasTableHeaderBienSo() {
        return headerBienSo != null && headerBienSo.isDisplayed();
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
        List<WebElement> els = driver.findElements(By.cssSelector("[data-testid='admin-vehicles-pagination-info']"));
        return !els.isEmpty() && els.get(0).isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    // --- CRUD ---

    public void clickAdd() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vehicles-modal']")));
    }

    public boolean isModalDisplayed() {
        return modal != null && modal.isDisplayed();
    }

    /**
     * Điền form. Nếu super_admin: chọn company trước (index), rồi chọn layout (index).
     * companyIndex/layoutIndex: null = bỏ qua (company admin).
     */
    public void fillForm(String busName, String busTypeLabel, String licensePlate, int totalSeats, String status,
                         Integer companySelectIndex, Integer layoutSelectIndex) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        wait.until(ExpectedConditions.visibilityOf(inputBusName));

        List<WebElement> companySelects = driver.findElements(By.cssSelector("[data-testid='admin-vehicles-form'] select[name='companyId']"));
        if (!companySelects.isEmpty()) {
            org.openqa.selenium.support.ui.Select cs = new org.openqa.selenium.support.ui.Select(companySelects.get(0));
            int idx = companySelectIndex != null ? companySelectIndex : 1;
            if (cs.getOptions().size() > idx) {
                cs.selectByIndex(idx);
            }
        }

        List<WebElement> layoutSelects = driver.findElements(By.cssSelector("[data-testid='admin-vehicles-form'] select[name='layoutTemplateId']"));
        if (!layoutSelects.isEmpty()) {
            org.openqa.selenium.support.ui.Select ls = new org.openqa.selenium.support.ui.Select(layoutSelects.get(0));
            int idx = layoutSelectIndex != null ? layoutSelectIndex : 1;
            if (ls.getOptions().size() > idx) {
                ls.selectByIndex(idx);
            }
        }

        if (inputBusName != null) {
            inputBusName.clear();
            inputBusName.sendKeys(busName);
        }
        if (selectBusType != null && busTypeLabel != null) {
            new org.openqa.selenium.support.ui.Select(selectBusType).selectByVisibleText(busTypeLabel);
        }
        if (inputLicensePlate != null) {
            inputLicensePlate.clear();
            inputLicensePlate.sendKeys(licensePlate);
        }
        if (inputTotalSeats != null) {
            inputTotalSeats.clear();
            inputTotalSeats.sendKeys(String.valueOf(totalSeats));
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
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[data-testid='admin-vehicles-modal']")));
    }

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
                By.cssSelector("[data-testid^='admin-vehicles-btn-edit-']")));
        btn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vehicles-modal']")));
    }

    public void clickDeleteFirstRow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-testid^='admin-vehicles-btn-delete-']")));
        btn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='confirm-modal']")));
    }

    public void confirmDelete() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']")));
        btn.click();
    }

    public boolean tableContainsBusName(String busName) {
        if (busName == null) {
            return false;
        }
        String safe = busName.replace("\"", "");
        List<WebElement> cells = driver.findElements(By.xpath("//td[contains(.,\"" + safe + "\")]"));
        return !cells.isEmpty();
    }

    public int getTableRowCount() {
        List<WebElement> rows = driver.findElements(By.cssSelector("[data-testid^='admin-vehicles-row-']"));
        return rows.size();
    }
}
