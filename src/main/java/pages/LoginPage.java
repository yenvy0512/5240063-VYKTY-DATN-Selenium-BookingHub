package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Trang Đăng nhập web-customer (/login).
 */
public class LoginPage extends BasePage {

    @FindBy(css = "[data-testid='login-form']")
    private WebElement loginForm;

    @FindBy(id = "usernameOrEmail")
    private WebElement usernameOrEmailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(css = "[data-testid='login-submit']")
    private WebElement submitButton;

    @FindBy(css = "[data-testid='link-register']")
    private WebElement linkRegister;

    @FindBy(xpath = "//h1[contains(.,'Đăng nhập')]")
    private WebElement headingLogin;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoginFormDisplayed() {
        return loginForm != null && loginForm.isDisplayed();
    }

    public boolean isUsernameInputDisplayed() {
        return usernameOrEmailInput != null && usernameOrEmailInput.isDisplayed();
    }

    public boolean isPasswordInputDisplayed() {
        return passwordInput != null && passwordInput.isDisplayed();
    }

    public boolean isLinkRegisterDisplayed() {
        return linkRegister != null && linkRegister.isDisplayed();
    }

    public void clickLinkRegister() {
        if (linkRegister != null) {
            linkRegister.click();
        }
    }

    public String getUsernamePlaceholder() {
        return usernameOrEmailInput != null ? usernameOrEmailInput.getAttribute("placeholder") : "";
    }

    public void login(String usernameOrEmail, String password) {
        if (usernameOrEmailInput != null) {
            usernameOrEmailInput.clear();
            usernameOrEmailInput.sendKeys(usernameOrEmail);
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
