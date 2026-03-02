package base;

import config.Config;

/**
 * Base cho test web-admin. Mở URL admin (port 3001).
 */
public abstract class AdminBaseTest extends BaseTest {

    @Override
    protected String getBaseUrl() {
        return Config.getBaseUrlAdmin();
    }
}
