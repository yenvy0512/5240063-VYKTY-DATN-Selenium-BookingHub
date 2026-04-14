package base;

import config.Config;

public abstract class AdminBaseTest extends BaseTest {

    @Override
    protected String getBaseUrl() {
        return Config.getBaseUrlAdmin();
    }
}
