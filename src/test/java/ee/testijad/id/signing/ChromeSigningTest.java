package ee.testijad.id.signing;

import ee.testijad.id.webdriver.WebDrivers;

public class ChromeSigningTest extends BrowserSigningTest {

    @Override
    void setUpDriver() {
        driver = WebDrivers.getChromeWithId();
    }

}
