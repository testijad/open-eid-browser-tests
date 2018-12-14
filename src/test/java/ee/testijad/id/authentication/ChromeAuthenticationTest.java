package ee.testijad.id.authentication;

import ee.testijad.id.resource.Patterns;
import ee.testijad.id.webdriver.WebDrivers;

public class ChromeAuthenticationTest extends BrowserAuthenticationTest {

    @Override
    void setUpDriver() {
        driver = WebDrivers.getChromeWithId();
    }

    @Override
    void setUpImagePatterns() {
        certificateReady = Patterns.getChromeOk();
        certificateOkButton = Patterns.getChromeOk();
        pinFieldReady = Patterns.getChromePin();
    }

}
