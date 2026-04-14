package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Config {

    private static final String CONFIG_FILE = "config.properties";
    private static Properties props;

    static {
        props = new Properties();
        try (InputStream is = Config.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (is != null) {
                props.load(is);
            }
        } catch (IOException e) {
            throw new RuntimeException("Không đọc được " + CONFIG_FILE, e);
        }
    }

    public static String getBaseUrl() {
        return props.getProperty("base.url", "http://localhost:3000");
    }

    public static String getBaseUrlAdmin() {
        return props.getProperty("base.url.admin", "http://localhost:3001");
    }

    public static String getAdminUsername() {
        return props.getProperty("admin.username", "admin");
    }

    public static String getAdminPassword() {
        return props.getProperty("admin.password", "admin123");
    }

    public static String getCustomerUsername() {
        return props.getProperty("customer.username", "customer");
    }

    public static String getCustomerPassword() {
        return props.getProperty("customer.password", "customer123");
    }

    public static String getBrowser() {
        return props.getProperty("browser", "chrome");
    }

    public static int getImplicitWaitSeconds() {
        return Integer.parseInt(props.getProperty("implicit.wait.seconds", "10"));
    }

    public static int getPageLoadTimeoutSeconds() {
        return Integer.parseInt(props.getProperty("page.load.timeout.seconds", "30"));
    }

    // Delay (giây) sau mỗi test method để dễ quan sát. 0 = không delay.
    public static int getDelayAfterTestSeconds() {
        return Integer.parseInt(props.getProperty("delay.after.test.seconds", "0"));
    }

    private Config() {
    }
}
