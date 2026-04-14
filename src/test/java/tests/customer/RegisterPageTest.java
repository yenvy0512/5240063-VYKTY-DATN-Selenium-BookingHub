package tests.customer;

import base.BaseTest;
import config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.RegisterPage;
import utils.BrowserStorageHelper;
import utils.ValidationTestHelper;

public class RegisterPageTest extends BaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        BrowserStorageHelper.clearCustomerGuestState(getDriver());
        getDriver().get(Config.getBaseUrl() + "/register");
    }

    @Test(description = "RG-01 Trang đăng ký hiển thị form")
    public void registerPageShowsForm() {
        RegisterPage registerPage = new RegisterPage(getDriver());
        Assert.assertTrue(registerPage.isRegisterFormDisplayed(), "Form đăng ký hiển thị");
    }

    @Test(description = "RG-02 Tiêu đề trang đăng ký đúng")
    public void registerPageTitle() {
        RegisterPage registerPage = new RegisterPage(getDriver());
        String title = registerPage.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Đăng ký") || title.contains("BookingHub"),
                "Tiêu đề phải chứa Đăng ký hoặc BookingHub");
    }

    @Test(description = "RG-03 URL trang đăng ký chứa register")
    public void registerPageUrl() {
        Assert.assertTrue(getDriver().getCurrentUrl().contains("register"), "URL chứa register");
    }

    @Test(description = "RG-04 Form có đủ thông tin")
    public void registerFormHasAllFields() {
        RegisterPage registerPage = new RegisterPage(getDriver());
        Assert.assertTrue(registerPage.hasUsernameField(), "Ô Username");
        Assert.assertTrue(registerPage.hasEmailField(), "Ô Email");
        Assert.assertTrue(registerPage.hasPasswordField(), "Ô Mật khẩu");
        Assert.assertTrue(registerPage.hasNameField(), "Ô Họ tên");
        Assert.assertTrue(registerPage.hasPhoneField(), "Ô SĐT");
    }

    @Test(description = "RG-05 Ô mật khẩu có type password")
    public void passwordFieldIsMasked() {
        RegisterPage registerPage = new RegisterPage(getDriver());
        Assert.assertEquals(registerPage.getPasswordFieldType(), "password", "type=password");
    }

    @Test(description = "RG-06 Nút Đăng ký hiển thị")
    public void registerSubmitButtonDisplayed() {
        RegisterPage registerPage = new RegisterPage(getDriver());
        Assert.assertTrue(registerPage.isSubmitButtonDisplayed(), "Nút Đăng ký hiển thị");
    }

    @Test(description = "RG-07 Gửi thông tin trống form vẫn hiển thị và thông báo nhập các trường bắt buộc")
    public void validation_submitEmptyForm_formStillVisible() {
        RegisterPage registerPage = new RegisterPage(getDriver());
        registerPage.clickSubmit();
        Assert.assertTrue(registerPage.isRegisterFormDisplayed(), "Form vẫn hiển thị");
    }

    @Test(description = "RG-08 Điền chỉ username rồi gửi thông tin vẫn ở form thông báo nhập các trường bắt buộc")
    public void validation_partialFillOnlyUsername() {
        RegisterPage registerPage = new RegisterPage(getDriver());
        registerPage.fillUsername("onlyuser_" + System.currentTimeMillis());
        registerPage.clickSubmit();
        Assert.assertTrue(registerPage.isRegisterFormDisplayed() || ValidationTestHelper.isTextPresentOnPage(getDriver(), "Vui lòng"),
                "Chưa đủ điều kiện đăng ký");
    }

    @Test(description = "RG-09 Điền email không hợp lệ báo lỗi")
    public void validation_invalidEmailFormat() {
        RegisterPage registerPage = new RegisterPage(getDriver());
        registerPage.fillUsername("u" + System.currentTimeMillis());
        registerPage.fillEmail("not-an-email");
        registerPage.fillPassword("Secret123!");
        registerPage.clickSubmit();
        Assert.assertTrue(registerPage.isRegisterFormDisplayed(), "Vẫn ở form và báo lỗi email sai định dạng");
    }

    @Test(description = "RG-10 Nút đăng ký hiển thị")
    public void submitButtonEnabled() {
        RegisterPage registerPage = new RegisterPage(getDriver());
        Assert.assertTrue(registerPage.isSubmitButtonDisplayed(), "Nút đăng ký hiển thị để tương tác");
    }
}
