package utils;

import config.Config;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Xóa phiên trình duyệt (cookie + storage) trên một origin để test trang login/register
 * luôn thấy form, không bị redirect do token còn từ test trước trong cùng class.
 */
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
