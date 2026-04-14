package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public final class ValidationTestHelper {

    private static final int TOAST_WAIT_SECONDS = 5;

    private ValidationTestHelper() {
    }

    public static boolean waitForToastContainingText(WebDriver driver, String partialText) {
        return waitForTextPresent(driver, partialText, TOAST_WAIT_SECONDS);
    }

    public static boolean isTextPresentOnPage(WebDriver driver, String partialText) {
        String escaped = partialText.replace("\"", "\\\"");
        List<WebElement> elements = driver.findElements(By.xpath("//*[contains(.,\"" + escaped + "\")]"));
        for (WebElement el : elements) {
            try {
                if (el.isDisplayed() && el.getText().contains(partialText)) {
                    return true;
                }
            } catch (Exception ignored) {
            }
        }
        return false;
    }

    public static boolean waitForTextPresent(WebDriver driver, String partialText, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        try {
            wait.until(d -> isTextPresentOnPage(d, partialText));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
