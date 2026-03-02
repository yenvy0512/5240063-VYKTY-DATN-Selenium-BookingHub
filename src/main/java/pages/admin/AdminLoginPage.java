package pages.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

/**
 * Trang Đăng nhập web-admin (/login).
 */
public class AdminLoginPage extends BasePage {

    @FindBy(css = "[data-testid='admin-login-form']")
    private WebElement loginForm;

    @FindBy(id = "admin-usernameOrEmail")
    private WebElement usernameInput;

    @FindBy(id = "admin-password")
    private WebElement passwordInput;

    @FindBy(css = "[data-testid='admin-login-submit']")
    private WebElement submitButton;

    public AdminLoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoginFormDisplayed() {
        return loginForm != null && loginForm.isDisplayed();
    }

    public void login(String username, String password) {
        if (usernameInput != null) {
            usernameInput.clear();
            usernameInput.sendKeys(username);
        }
        if (passwordInput != null) {
            passwordInput.clear();
            passwordInput.sendKeys(password);
        }
        if (submitButton != null) {
            submitButton.click();
        }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
