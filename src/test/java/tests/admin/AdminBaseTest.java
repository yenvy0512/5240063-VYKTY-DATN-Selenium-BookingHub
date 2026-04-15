package tests.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import config.Config;
import tests.BaseWebTest;

public class AdminBaseTest extends BaseWebTest {

	protected void openAdmin() {
		driver.get(Config.getBaseUrlAdmin());
	}

	protected void logoutAdminIfNeeded() {
		List<WebElement> logoutBtn = driver.findElements(By.cssSelector("[data-testid='admin-header-logout']"));

		if (!logoutBtn.isEmpty()) {
			wait.until(ExpectedConditions.elementToBeClickable(logoutBtn.get(0))).click();

			WebElement confirm = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(normalize-space(.),'Đăng xuất')]")));
			confirm.click();
		}
	}

	protected void loginSuperAdmin() {
		openAdmin();
		logoutAdminIfNeeded();

		WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.id("admin-usernameOrEmail")));
		username.clear();
		username.sendKeys(Config.getSuperAdminUsername());

		WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
		password.clear();
		password.sendKeys(Config.getSuperAdminPassword());

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-login-submit']"))).click();
		wait.until(ExpectedConditions.titleContains("Admin"));
	}

	protected void loginAdmin() {
		openAdmin();
		logoutAdminIfNeeded();

		WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.id("admin-usernameOrEmail")));
		username.clear();
		username.sendKeys(Config.getAdminUsername());

		WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
		password.clear();
		password.sendKeys(Config.getAdminPassword());

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-login-submit']"))).click();
		wait.until(ExpectedConditions.titleContains("Admin"));
	}
}
