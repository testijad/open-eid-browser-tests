package ee.testijad.id.authentication;

import ee.testijad.id.resource.Patterns;
import ee.testijad.id.webdriver.WebDrivers;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

public class FirefoxAuthenticationTest extends BrowserAuthenticationTest {

    @Override
    protected void setUpDriver() {
        driver = WebDrivers.getFirefoxDefault();
    }

    @Override
    protected void setUpImagePatterns() {
        certificateReady = Patterns.getFirefoxOk();
        certificateOkButton = Patterns.getFirefoxOk();
        pinFieldReady = Patterns.getFirefoxPin();
    }

    @Override
    void authenticate() {
        screen = new Screen();
        try {
            try {
                enterPin();
            } catch (FindFailed e) {
                selectCertificate();
                enterPin();
            }
            selectCertificate();
        } catch (FindFailed e) {
            e.printStackTrace();
        }
    }

}
