package ee.testijad.id.webdriver;

import ee.testijad.id.resource.Resources;
import ee.testijad.id.util.Config;
import ee.testijad.id.util.OperatingSystem;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.Toolkit;

public class WebDrivers {

    private static String getWebdriverPath(String architecture, String webdriverName) {
        String path = Resources.getFileSeparatorPath(
                String.format(
                        "%swebdrivers/%s/%sbit/%s",
                        Resources.getProjectRootPath(),
                        OperatingSystem.getName(),
                        architecture,
                        webdriverName
                )
        );
        System.out.println("Using WebDriver from: " + path);
        return path;
    }

    public static WebDriver getChromeWithId() {
        setUpChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", true);
        options.addExtensions(Resources.getExtension(Config.CHROME_EXTENSION_FILE_NAME));
        options.addArguments(CapabilityType.ACCEPT_SSL_CERTS);
        return new ChromeDriver(options);
    }

    public static WebDriver getFirefoxDefault() {
        setUpFirefoxDriver();
        FirefoxProfile profile = new ProfilesIni().getProfile("default");
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        return new FirefoxDriver(options);
    }

    public static WebDriver getFirefoxWithId() {
        setUpFirefoxDriver();
        FirefoxProfile profile = new FirefoxProfile();
        profile.addExtension(Resources.getExtension("id_extension.xpi"));
        profile.addExtension(Resources.getExtension("pkcs11.xpi"));
        profile.addExtension(Resources.getExtension("info@ria.ee.xpi"));
        profile.setAcceptUntrustedCertificates(false);
        profile.setAssumeUntrustedCertificateIssuer(true);
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        return new FirefoxDriver(options);
    }

    public static WebDriver getInternetExplorer() {
        setupInternetExplorerDriver();
        return new InternetExplorerDriver();
    }

    private static void setUpChromeDriver() {
        String path = getWebdriverPath(Config.CHROME_DRIVER_ARCHITECTURE, getExecutableName("chromedriver"));
        System.setProperty("webdriver.chrome.driver", path);
    }

    private static void setUpFirefoxDriver() {
        String path = getWebdriverPath(Config.FIREFOX_DRIVER_ARCHITECTURE, getExecutableName("geckodriver"));
        System.setProperty("webdriver.gecko.driver", path);
    }

    private static void setupInternetExplorerDriver() {
        String path = getWebdriverPath(Config.INTERNET_EXPLORER_DRIVER_ARCHITECTURE, "IEDriverServer.exe");
        System.setProperty("webdriver.ie.driver", path);
    }

    public static void setWindowLocation(WebDriver driver) {
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int halfWidth = (int) (screenSize.getWidth() / 2);
        int height = (int) screenSize.getHeight();
        System.out.println(halfWidth + ", " + height);
        driver.manage().window().setSize(new Dimension(halfWidth, height));
        driver.manage().window().setPosition(new Point(halfWidth, 0));
    }

    private static String getExecutableName(String browserName) {
        if (OperatingSystem.isWindows()) {
            return browserName + ".exe";
        }
        return browserName;
    }

    public static boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void waitUntilAlertNotPresent(WebDriver driver, long timeOut) {
        new WebDriverWait(driver, timeOut)
            .until(webDriver -> !isAlertPresent(webDriver));
    }

}
