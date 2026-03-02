package tests.customer;

import base.BaseTest;
import config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RegisterPage;

/**
 * Test case trang Đăng ký web-customer.
 */
public class RegisterPageTest extends BaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrl() + "/register");
    }

    @Test(description = "TC-C09: Trang đăng ký hiển thị form")
    public void registerPageShowsForm() {
        RegisterPage registerPage = new RegisterPage(getDriver());
        Assert.assertTrue(registerPage.isRegisterFormDisplayed(), "Form đăng ký phải hiển thị");
    }

    @Test(description = "TC-C10: Title trang đăng ký chứa Đăng ký hoặc BookingHub")
    public void registerPageTitle() {
        RegisterPage registerPage = new RegisterPage(getDriver());
        String title = registerPage.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Đăng ký") || title.contains("BookingHub"),
                "Title phải chứa 'Đăng ký' hoặc 'BookingHub'");
    }

    @Test(description = "TC-C09b: Form đăng ký có đủ: Username, Email, Mật khẩu, Họ tên, SĐT")
    public void registerFormHasAllFields() {
        RegisterPage registerPage = new RegisterPage(getDriver());
        Assert.assertTrue(registerPage.hasUsernameField(), "Form phải có ô Username");
        Assert.assertTrue(registerPage.hasEmailField(), "Form phải có ô Email");
        Assert.assertTrue(registerPage.hasPasswordField(), "Form phải có ô Mật khẩu");
        Assert.assertTrue(registerPage.hasNameField(), "Form phải có ô Họ tên");
        Assert.assertTrue(registerPage.hasPhoneField(), "Form phải có ô Số điện thoại");
    }

    @Test(description = "TC-C09c: Nút Đăng ký hiển thị")
    public void registerSubmitButtonDisplayed() {
        RegisterPage registerPage = new RegisterPage(getDriver());
        Assert.assertTrue(registerPage.isSubmitButtonDisplayed(), "Nút Đăng ký phải hiển thị");
    }
}
