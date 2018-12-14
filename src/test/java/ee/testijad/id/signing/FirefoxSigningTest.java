package ee.testijad.id.signing;

import ee.testijad.id.webdriver.WebDrivers;

public class FirefoxSigningTest extends BrowserSigningTest {

    @Override
    void setUpDriver() {
        driver = WebDrivers.getFirefoxWithId();
    }

}