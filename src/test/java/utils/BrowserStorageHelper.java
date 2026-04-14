package utils;

import config.Config;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public final class BrowserStorageHelper {

    private BrowserStorageHelper() {
    }

    public static void clearCustomerGuestState(WebDriver driver) {
        clearOrigin(driver, Config.getBaseUrl());
    }

    public static void clearAdminGuestState(WebDriver driver) {
        clearOrigin(driver, Config.getBaseUrlAdmin());
    }

    private static void clearOrigin(WebDriver driver, String baseUrl) {
        String root = baseUrl.replaceAll("/+$", "") + "/";
        driver.get(root);
        driver.manage().deleteAllCookies();
        if (driver instanceof JavascriptExecutor js) {
            js.executeScript("try{localStorage.clear();sessionStorage.clear();}catch(e){}");
        }
        driver.navigate().refresh();
    }
}
