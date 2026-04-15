package tests.customer;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import config.Config;
import tests.BaseWebTest;

public class CustomerBaseTest extends BaseWebTest {

	protected void openCustomerHome() {
		driver.get(Config.getBaseUrl() + "/");
	}

	protected void logoutCustomerIfNeeded() {
		boolean isLoggedIn = !driver.findElements(By.cssSelector(".user-avatar")).isEmpty();

		if (isLoggedIn) {
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".user-avatar"))).click();
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(.,'Đăng xuất')]"))).click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-testid='confirm-modal']")));
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='confirm-modal-confirm']")))
					.click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn-login")));
		}
	}

	protected void loginCustomer() {
		openCustomerHome();

		WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-login")));
		loginBtn.click();

		wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Đăng nhập"))).click();

		WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.id("usernameOrEmail")));
		username.clear();
		username.sendKeys(Config.getCustomerUsername());

		WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.id("password")));
		password.clear();
		password.sendKeys(Config.getCustomerPassword());

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Đăng nhập')]"))).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".btn-login")));
	}
}
