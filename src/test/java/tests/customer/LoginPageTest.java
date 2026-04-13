package tests.customer;

import base.BaseTest;
import config.Config;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BrowserStorageHelper;

import java.time.Duration;

/**
 * Test trang Đăng nhập web-customer.
 */
public class LoginPageTest extends BaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        BrowserStorageHelper.clearCustomerGuestState(getDriver());
        getDriver().get(Config.getBaseUrl() + "/login");
    }

    @Test(description = "LG-01 Trang login hiển thị form đăng nhập")
    public void loginPageShowsForm() {
        LoginPage loginPage = new LoginPage(getDriver());
        Assert.assertTrue(loginPage.isLoginFormDisplayed(), "Form đăng nhập phải hiển thị");
    }

    @Test(description = "LG-02 Tiêu đề trang login đúng")
    public void loginPageTitle() {
        LoginPage loginPage = new LoginPage(getDriver());
        String title = loginPage.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Đăng nhập") || title.contains("BookingHub"),
                "Tiêu đề phải chứa Đăng nhập hoặc BookingHub");
    }

    @Test(description = "LG-03 URL trang login chứa login")
    public void loginPageUrl() {
        Assert.assertTrue(getDriver().getCurrentUrl().contains("login"), "URL phải chứa login");
    }

    @Test(description = "LG-04 Ô mật khẩu có type password")
    public void passwordFieldIsMasked() {
        LoginPage loginPage = new LoginPage(getDriver());
        Assert.assertEquals(loginPage.getPasswordInputType(), "password", "Ô mật khẩu phải type=password");
    }

    @Test(description = "LG-05 Placeholder ô tài khoản")
    public void usernamePlaceholder() {
        LoginPage loginPage = new LoginPage(getDriver());
        String ph = loginPage.getUsernamePlaceholder();
        Assert.assertFalse(ph == null || ph.isEmpty(), "Ô tài khoản có placeholder");
    }

    @Test(description = "LG-06 Đăng nhập sai vẫn ở trang login")
    public void loginWithInvalidCredentialsStaysOnPage() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("invalid_user_xyz", "wrongpassword");
        String url = getDriver().getCurrentUrl();
        Assert.assertTrue(url.contains("/login") || url.contains("login"),
                "Đăng nhập sai phải vẫn ở trang login");
    }

    @Test(description = "LG-07 Form có ô tài khoàn và Mật khẩu")
    public void loginFormHasUsernameAndPasswordFields() {
        LoginPage loginPage = new LoginPage(getDriver());
        Assert.assertTrue(loginPage.isUsernameInputDisplayed(), "Ô username/email hiển thị");
        Assert.assertTrue(loginPage.isPasswordInputDisplayed(), "Ô mật khẩu hiển thị");
    }

    @Test(description = "LG-08 Có nút Đăng ký")
    public void loginPageHasLinkRegister() {
        LoginPage loginPage = new LoginPage(getDriver());
        Assert.assertTrue(loginPage.isLinkRegisterDisplayed(), "Nút Đăng ký hiển thị");
    }

    @Test(description = "LG-09 Ấn Đăng ký chuyển sang trang đăng ký")
    public void clickRegisterLinkNavigatesToRegister() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.clickLinkRegister();
        String url = getDriver().getCurrentUrl();
        Assert.assertTrue(url.contains("/register"), "Ấn Đăng ký chuyển sang trang đăng ký");
    }

    @Test(description = "LG-10 Gửi thông tin form trống vẫn ở trang login")
    public void validation_submitEmptyForm_staysOnLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("", "");
        String url = getDriver().getCurrentUrl();
        Assert.assertTrue(url.contains("/login") || url.contains("login"), "Vẫn ở trang login");
        Assert.assertTrue(loginPage.isLoginFormDisplayed(), "Form vẫn hiển thị");
    }

    @Test(description = "LG-11 Đăng nhập đúng tài khoản rời khỏi trang login")
    public void loginWithValidCredentialsLeavesLoginPage() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(Config.getCustomerUsername(), Config.getCustomerPassword());
        new WebDriverWait(getDriver(), Duration.ofSeconds(Config.getPageLoadTimeoutSeconds()))
                .until(d -> !d.getCurrentUrl().contains("/login"));
        Assert.assertFalse(getDriver().getCurrentUrl().contains("/login"),
                "Sau đăng nhập thành công không còn ở /login");
    }
}
