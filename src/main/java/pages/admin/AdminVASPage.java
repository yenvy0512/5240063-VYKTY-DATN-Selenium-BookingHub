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

/**
 * Trang Quản lý Dịch vụ (VAS) web-admin (/vas). Create, Update, Delete.
 */
public class AdminVASPage extends BasePage {

    @FindBy(css = "[data-testid='admin-vas-page']")
    private WebElement page;

    @FindBy(css = "[data-testid='admin-vas-btn-add']")
    private WebElement addButton;

    @FindBy(css = "[data-testid='admin-vas-table']")
    private WebElement table;

    @FindBy(css = "[data-testid='admin-vas-modal']")
    private WebElement modal;

    @FindBy(css = "[data-testid='admin-vas-form']")
    private WebElement form;

    private static final int WAIT_SECONDS = 15;

    public AdminVASPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageDisplayed() {
        return page != null && page.isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isAddButtonDisplayed() {
        return addButton != null && addButton.isDisplayed();
    }

    public boolean isTableDisplayed() {
        return table != null && table.isDisplayed();
    }

    public void typeSearchKeyword(String keyword) {
        WebElement input = driver.findElement(By.cssSelector("[data-testid='admin-vas-search-input']"));
        input.clear();
        input.sendKeys(keyword != null ? keyword : "");
    }

    public void clickSearchButton() {
        driver.findElement(By.cssSelector("[data-testid='admin-vas-search-submit']")).click();
    }

    public boolean headingDisplayed() {
        List<WebElement> h1 = driver.findElements(By.cssSelector("[data-testid='admin-vas-heading']"));
        return !h1.isEmpty() && h1.get(0).isDisplayed();
    }

    // --- CRUD ---

    public void clickAdd() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vas-modal']")));
    }

    public boolean isModalDisplayed() {
        return modal != null && modal.isDisplayed();
    }

    /** Form VAS: input text (Tên), select (Loại), input number (Giá). */
    public void fillForm(String name, String typeValue, int price) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        By byName = By.cssSelector("[data-testid='admin-vas-form'] input[type='text']");
        wait.until(ExpectedConditions.visibilityOfElementLocated(byName));
        WebElement nameInput = driver.findElement(byName);
        nameInput.clear();
        nameInput.sendKeys(name);
        WebElement typeSelect = driver.findElement(By.cssSelector("[data-testid='admin-vas-form'] select"));
        new org.openqa.selenium.support.ui.Select(typeSelect).selectByValue(typeValue != null ? typeValue : "luggage");
        WebElement priceInput = driver.findElement(By.cssSelector("[data-testid='admin-vas-form'] input[type='number']"));
        priceInput.clear();
        priceInput.sendKeys(String.valueOf(price));
    }

    public void submitForm() {
        WebElement submit = driver.findElement(By.cssSelector("[data-testid='admin-vas-form'] button[type='submit']"));
        submit.click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[data-testid='admin-vas-modal']")));
    }

    /** Chỉ click nút submit (dùng cho test validation). */
    public void clickSubmitOnly() {
        WebElement submit = driver.findElement(By.cssSelector("[data-testid='admin-vas-form'] button[type='submit']"));
        submit.click();
    }

    public void clickEditFirstRow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-testid^='admin-vas-btn-edit-']")));
        btn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-vas-modal']")));
    }

    public void clickDeleteFirstRow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-testid^='admin-vas-btn-delete-']")));
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
        List<WebElement> rows = driver.findElements(By.cssSelector("[data-testid^='admin-vas-row-']"));
        return rows.size();
    }
}
