package tests.admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import config.Config;
import tests.BaseWebTest;

public class AdminBaseTest extends BaseWebTest {

	public void openAdmin() {
		driver.get(Config.getBaseUrlAdmin());
	}

	public void logoutAdminIfNeeded() {
		List<WebElement> logoutBtn = driver.findElements(By.cssSelector("[data-testid='admin-header-logout']"));

		if (!logoutBtn.isEmpty()) {
			wait.until(ExpectedConditions.elementToBeClickable(logoutBtn.get(0))).click();

			WebElement confirm = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//button[contains(normalize-space(.),'Đăng xuất')]")));
			confirm.click();
		}
	}

	public void loginSuperAdmin() {
		openAdmin();
		logoutAdminIfNeeded();

		WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.id("admin-usernameOrEmail")));
		username.clear();
		username.sendKeys(Config.getSuperAdminUsername());

		WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
		password.clear();
		password.sendKeys(Config.getSuperAdminPassword());

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-login-submit']")))
				.click();
		wait.until(ExpectedConditions.titleContains("Admin"));
	}

	public void loginAdmin() {
		openAdmin();
		logoutAdminIfNeeded();

		WebElement username = wait.until(ExpectedConditions.elementToBeClickable(By.id("admin-usernameOrEmail")));
		username.clear();
		username.sendKeys(Config.getAdminUsername());

		WebElement password = wait.until(ExpectedConditions.elementToBeClickable(By.name("password")));
		password.clear();
		password.sendKeys(Config.getAdminPassword());

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='admin-login-submit']")))
				.click();
		wait.until(ExpectedConditions.titleContains("Admin"));
	}

	public void selectDate(By inputLocator, int day, int month, int year) {
		WebElement input = wait.until(ExpectedConditions.elementToBeClickable(inputLocator));
		input.click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("react-datepicker")));

		Select monthSelect = new Select(wait
				.until(ExpectedConditions.presenceOfElementLocated(By.className("react-datepicker__month-select"))));
		monthSelect.selectByValue(String.valueOf(month - 1));

		Select yearSelect = new Select(
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className("react-datepicker__year-select"))));
		yearSelect.selectByValue(String.valueOf(year));

		String dayStr = String.format("%02d", day);

		By dayLocator = By.xpath("//div[contains(@class,'react-datepicker__day') "
				+ "and not(contains(@class,'outside-month')) " + "and text()='" + dayStr + "']");

		wait.until(ExpectedConditions.elementToBeClickable(dayLocator)).click();
	}
}
