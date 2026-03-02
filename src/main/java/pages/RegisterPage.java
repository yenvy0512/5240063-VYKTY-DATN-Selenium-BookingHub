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

    public String getPageTitle() {
        return driver.getTitle();
    }
}
