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
 * Trang Quản lý Người dùng web-admin (/users). CRUD qua modal.
 */
public class AdminUsersPage extends BasePage {

    @FindBy(css = "[data-testid='admin-users-page']")
    private WebElement page;

    @FindBy(css = "[data-testid='admin-users-btn-add']")
    private WebElement addButton;

    @FindBy(css = "[data-testid='admin-users-table']")
    private WebElement table;

    private static final int WAIT_SECONDS = 15;

    public AdminUsersPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageDisplayed() {
        return page != null && page.isDisplayed();
    }

    public boolean isAddButtonDisplayed() {
        return addButton != null && addButton.isDisplayed();
    }

    public boolean isTableDisplayed() {
        return table != null && table.isDisplayed();
    }

    public boolean headingDisplayed() {
        List<WebElement> h1 = driver.findElements(By.cssSelector("[data-testid='admin-users-heading']"));
        return !h1.isEmpty() && h1.get(0).isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public int getTableRowCount() {
        return driver.findElements(By.cssSelector("[data-testid^='admin-users-row-']")).size();
    }

    public void clickAdd() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-modal']")));
    }

    public boolean isModalDisplayed() {
        List<WebElement> modals = driver.findElements(By.cssSelector("[data-testid='admin-users-modal']"));
        return !modals.isEmpty() && modals.get(0).isDisplayed();
    }

    public void fillCreateForm(String username, String email, String name, String roleValue, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-modal']")));

        WebElement userInput = driver.findElement(By.cssSelector("[data-testid='admin-users-input-username']"));
        userInput.clear();
        userInput.sendKeys(username);

        WebElement emailInput = driver.findElement(By.cssSelector("[data-testid='admin-users-input-email']"));
        emailInput.clear();
        emailInput.sendKeys(email != null ? email : "");

        WebElement nameInput = driver.findElement(By.cssSelector("[data-testid='admin-users-input-name']"));
        nameInput.clear();
        nameInput.sendKeys(name != null ? name : "");

        new org.openqa.selenium.support.ui.Select(driver.findElement(By.cssSelector("[data-testid='admin-users-select-role']")))
                .selectByValue(roleValue);

        List<WebElement> companySelects = driver.findElements(By.cssSelector("[data-testid='admin-users-select-company']"));
        if (!companySelects.isEmpty() && companySelects.get(0).isDisplayed()) {
            org.openqa.selenium.support.ui.Select cs = new org.openqa.selenium.support.ui.Select(companySelects.get(0));
            if (cs.getOptions().size() > 1) {
                cs.selectByIndex(1);
            }
        }

        List<WebElement> pwInputs = driver.findElements(By.cssSelector("[data-testid='admin-users-input-password']"));
        if (!pwInputs.isEmpty()) {
            pwInputs.get(0).clear();
            pwInputs.get(0).sendKeys(password);
        }
    }

    public void clickModalSave() {
        driver.findElement(By.cssSelector("[data-testid='admin-users-btn-save']")).click();
    }

    public void clickModalCancel() {
        driver.findElement(By.cssSelector("[data-testid='admin-users-btn-cancel']")).click();
        new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-modal']")));
    }

    public void clickEditFirstRow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-testid^='admin-users-btn-edit-']")));
        btn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-modal']")));
    }

    public void clickEditRowContainingUsername(String username) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        WebElement row = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//tr[.//td[contains(.,\"" + username.replace("\"", "") + "\")]]")));
        WebElement btn = row.findElement(By.cssSelector("[data-testid^='admin-users-btn-edit-']"));
        btn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='admin-users-modal']")));
    }

    public void clickDeleteFirstRow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-testid^='admin-users-btn-delete-']")));
        btn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='confirm-modal']")));
    }

    /** Xóa đúng dòng chứa username (an toàn hơn xóa dòng đầu). */
    public void clickDeleteRowContainingUsername(String username) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        WebElement row = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//tr[.//td[contains(.,\"" + username.replace("\"", "") + "\")]]")));
        WebElement btn = row.findElement(By.cssSelector("[data-testid^='admin-users-btn-delete-']"));
        btn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='confirm-modal']")));
    }

    public void confirmDelete() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_SECONDS));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']")));
        btn.click();
    }

    public boolean tableContainsUsername(String username) {
        return !driver.findElements(By.xpath("//td[contains(.,'" + username + "')]")).isEmpty();
    }

    public void updateNameInModal(String newName) {
        WebElement nameInput = driver.findElement(By.cssSelector("[data-testid='admin-users-input-name']"));
        nameInput.clear();
        nameInput.sendKeys(newName);
    }
}
