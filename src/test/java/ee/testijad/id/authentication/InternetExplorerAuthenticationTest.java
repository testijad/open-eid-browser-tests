package ee.testijad.id.authentication;

import ee.testijad.id.resource.Patterns;
import ee.testijad.id.util.OperatingSystem;
import ee.testijad.id.webdriver.WebDrivers;
import org.junit.Assume;
import org.junit.BeforeClass;

public class InternetExplorerAuthenticationTest extends BrowserAuthenticationTest {

    @BeforeClass
    public static void checkOperatingSystem() {
        Assume.assumeTrue(
                "Internet Explorer test can only work on Windows",
                OperatingSystem.isWindows()
        );
    }

    @Override
    void setUpDriver() {
        driver = WebDrivers.getInternetExplorer();
    }

    @Override
    void setUpImagePatterns() {
        certificateReady = Patterns.getSystemCertificateReady();
        certificateOkButton = Patterns.getSystemOk();
        pinFieldReady = Patterns.getSystemPin();
    }

}
