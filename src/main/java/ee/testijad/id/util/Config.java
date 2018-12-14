package ee.testijad.id.util;

import com.typesafe.config.ConfigFactory;

import java.io.File;

public class Config {

    private static final String CONFIG_FILE_PATH = "properties.conf";

    private static final com.typesafe.config.Config CONFIG =
            ConfigFactory.parseFile(new File(CONFIG_FILE_PATH));

    public static final String PIN1 = CONFIG.getString("pin1");
    public static final String PIN2 = CONFIG.getString("pin2");

    public static final int WEBDRIVER_WAIT_TIMEOUT_IN_SECONDS = CONFIG.getInt("webdriverWaitTimeoutInSeconds");

    public static final String CHROME_DRIVER_ARCHITECTURE = CONFIG.getString("chromeDriverArchitecture");
    public static final String FIREFOX_DRIVER_ARCHITECTURE = CONFIG.getString("firefoxDriverArchitecture");
    public static final String INTERNET_EXPLORER_DRIVER_ARCHITECTURE = CONFIG.getString("internetExplorerDriverArchitecture");

    public static final String CHROME_EXTENSION_FILE_NAME = CONFIG.getString("chromeExtensionFileName");

    public static final String AUTHENTICATION_PAGE_URL = CONFIG.getString("authenticationPageUrl");
    public static final String SIGNING_PAGE_URL = CONFIG.getString("signingPageUrl");

}
