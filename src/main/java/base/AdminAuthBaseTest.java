package base;

import config.Config;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import pages.admin.AdminLoginPage;

import java.time.Duration;

public abstract class AdminAuthBaseTest extends AdminBaseTest {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        super.setUp();
        loginIfNeeded();
    }

    /** Nếu đang ở trang login thì đăng nhập bằng tài khoản config. */
    protected void loginIfNeeded() {
        String currentUrl = getDriver().getCurrentUrl();
        if (currentUrl.contains("/login") || currentUrl.contains("login")) {
            AdminLoginPage loginPage = new AdminLoginPage(getDriver());
            if (loginPage.isLoginFormDisplayed()) {
                loginPage.login(Config.getAdminUsername(), Config.getAdminPassword());
                new WebDriverWait(getDriver(), Duration.ofSeconds(Config.getPageLoadTimeoutSeconds()))
                        .until(d -> !d.getCurrentUrl().contains("/login"));
            }
        }
    }
}
