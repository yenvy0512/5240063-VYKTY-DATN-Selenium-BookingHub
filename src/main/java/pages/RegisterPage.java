package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Trang Đăng ký web-customer (/register).
 */
public class RegisterPage extends BasePage {

    @FindBy(css = "[data-testid='register-form']")
    private WebElement registerForm;

    @FindBy(css = "[data-testid='register-submit']")
    private WebElement submitButton;

    @FindBy(css = "[data-testid='customer-register-heading']")
    private WebElement headingRegister;

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "name")
    private WebElement nameInput;

    @FindBy(id = "phone")
    private WebElement phoneInput;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public boolean isRegisterFormDisplayed() {
        return registerForm != null && registerForm.isDisplayed();
    }

    public boolean isHeadingDisplayed() {
        return headingRegister != null && headingRegister.isDisplayed();
    }

    public boolean hasUsernameField() {
        return usernameInput != null && usernameInput.isDisplayed();
    }

    public boolean hasEmailField() {
        return emailInput != null && emailInput.isDisplayed();
    }

    public boolean hasPasswordField() {
        return passwordInput != null && passwordInput.isDisplayed();
    }

    public boolean hasNameField() {
        return nameInput != null && nameInput.isDisplayed();
    }

    public boolean hasPhoneField() {
        return phoneInput != null && phoneInput.isDisplayed();
    }

    public boolean isSubmitButtonDisplayed() {
        return submitButton != null && submitButton.isDisplayed();
    }

    /** Click nút Đăng ký (dùng cho test validation). */
    public void clickSubmit() {
        if (submitButton != null) {
            submitButton.click();
        }
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getPasswordFieldType() {
        return passwordInput != null ? passwordInput.getAttribute("type") : "";
    }

    public void fillUsername(String value) {
        if (usernameInput != null) {
            usernameInput.clear();
            usernameInput.sendKeys(value);
        }
    }

    public void fillEmail(String value) {
        if (emailInput != null) {
            emailInput.clear();
            emailInput.sendKeys(value);
        }
    }

    public void fillPassword(String value) {
        if (passwordInput != null) {
            passwordInput.clear();
            passwordInput.sendKeys(value);
        }
    }
}
