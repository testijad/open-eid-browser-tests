package ee.testijad.id.signing;

import ee.testijad.id.util.OperatingSystem;
import ee.testijad.id.webdriver.WebDrivers;
import org.junit.Assume;
import org.junit.BeforeClass;

public class InternetExplorerSigningTest extends BrowserSigningTest {

    @BeforeClass
    public static void checkOperatingSystem() {
        Assume.assumeTrue(
                "Internet Explorer test can only work on Windows",
                OperatingSystem.isWindows()
        );
    }

    @Override
    void setUpDriver() {
        driver = WebDrivers.getChromeWithId();
    }

}
