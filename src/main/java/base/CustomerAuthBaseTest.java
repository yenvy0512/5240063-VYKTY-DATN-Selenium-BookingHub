package base;

import config.Config;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import pages.LoginPage;

import java.time.Duration;

public abstract class CustomerAuthBaseTest extends BaseTest {

    @Override
    @BeforeClass(alwaysRun = true)
    public void initDriver() {
        super.initDriver();
        loginIfNeeded();
    }

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
