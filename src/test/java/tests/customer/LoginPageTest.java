package tests.customer;

import base.BaseTest;
import config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

/**
 * Test case trang Đăng nhập web-customer.
 */
public class LoginPageTest extends BaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrl() + "/login");
    }

    @Test(description = "TC-C06: Trang login hiển thị form đăng nhập")
    public void loginPageShowsForm() {
        LoginPage loginPage = new LoginPage(getDriver());
        Assert.assertTrue(loginPage.isLoginFormDisplayed(), "Form đăng nhập phải hiển thị");
    }

    @Test(description = "TC-C07: Title trang login chứa Đăng nhập hoặc BookingHub")
    public void loginPageTitle() {
        LoginPage loginPage = new LoginPage(getDriver());
        String title = loginPage.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Đăng nhập") || title.contains("BookingHub"),
                "Title phải chứa 'Đăng nhập' hoặc 'BookingHub'");
    }

    @Test(description = "TC-C08: Đăng nhập sai thông tin vẫn ở trang login")
    public void loginWithInvalidCredentialsStaysOnPage() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("invalid_user_xyz", "wrongpassword");
        String url = getDriver().getCurrentUrl();
        Assert.assertTrue(url.contains("/login") || url.contains("login"),
                "Đăng nhập sai phải vẫn ở trang login");
    }

    @Test(description = "TC-C06b: Form có ô Username/Email và Mật khẩu")
    public void loginFormHasUsernameAndPasswordFields() {
        LoginPage loginPage = new LoginPage(getDriver());
        Assert.assertTrue(loginPage.isUsernameInputDisplayed(), "Ô username/email phải hiển thị");
        Assert.assertTrue(loginPage.isPasswordInputDisplayed(), "Ô mật khẩu phải hiển thị");
    }

    @Test(description = "TC-C06c: Có link Đăng ký")
    public void loginPageHasLinkRegister() {
        LoginPage loginPage = new LoginPage(getDriver());
        Assert.assertTrue(loginPage.isLinkRegisterDisplayed(), "Link Đăng ký phải hiển thị");
    }

    @Test(description = "TC-C06d: Click link Đăng ký chuyển sang /register")
    public void clickRegisterLinkNavigatesToRegister() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.clickLinkRegister();
        String url = getDriver().getCurrentUrl();
        Assert.assertTrue(url.contains("/register"), "Click Đăng ký phải chuyển sang trang register");
    }
}
