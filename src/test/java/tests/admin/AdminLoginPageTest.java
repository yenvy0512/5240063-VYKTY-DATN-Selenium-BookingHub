package tests.admin;

import base.AdminBaseTest;
import config.Config;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.admin.AdminLoginPage;

/**
 * Test case trang Đăng nhập web-admin.
 */
public class AdminLoginPageTest extends AdminBaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        getDriver().get(Config.getBaseUrlAdmin() + "/login");
    }

    @Test(description = "TC-A01: Trang admin login hiển thị form")
    public void adminLoginPageShowsForm() {
        AdminLoginPage loginPage = new AdminLoginPage(getDriver());
        Assert.assertTrue(loginPage.isLoginFormDisplayed(), "Form đăng nhập admin phải hiển thị");
    }

    @Test(description = "TC-A02: Title trang admin login đúng")
    public void adminLoginPageTitle() {
        AdminLoginPage loginPage = new AdminLoginPage(getDriver());
        String title = loginPage.getPageTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.contains("Đăng nhập") || title.contains("Admin") || title.contains("BookingHub"),
                "Title phải chứa Đăng nhập/Admin/BookingHub");
    }

    @Test(description = "TC-A03: Đăng nhập sai vẫn ở trang login")
    public void adminLoginInvalidStaysOnPage() {
        AdminLoginPage loginPage = new AdminLoginPage(getDriver());
        loginPage.login("wrong_admin", "wrong_pass");
        String url = getDriver().getCurrentUrl();
        Assert.assertTrue(url.contains("login"), "Đăng nhập sai phải vẫn ở trang login");
    }
}
