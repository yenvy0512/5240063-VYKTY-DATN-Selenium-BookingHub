package base;

import config.Config;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import pages.LoginPage;

import java.time.Duration;

/**
 * Base cho test web-customer cần đăng nhập (Đặt vé, Thanh toán, Vé của tôi).
 * Sau khi mở trang customer, thực hiện login bằng customer.username / customer.password.
 */
public abstract class CustomerAuthBaseTest extends BaseTest {

    @Override
    @BeforeClass(alwaysRun = true)
    public void initDriver() {
        super.initDriver();
        loginIfNeeded();
    }

    /** Đăng nhập customer (config) để test các trang cần auth. */
    protected void loginIfNeeded() {
        getDriver().get(Config.getBaseUrl() + "/login");
        LoginPage loginPage = new LoginPage(getDriver());
        if (loginPage.isLoginFormDisplayed()) {
            loginPage.login(Config.getCustomerUsername(), Config.getCustomerPassword());
            new WebDriverWait(getDriver(), Duration.ofSeconds(Config.getPageLoadTimeoutSeconds()))
                    .until(d -> !d.getCurrentUrl().contains("/login"));
        }
    }
}
