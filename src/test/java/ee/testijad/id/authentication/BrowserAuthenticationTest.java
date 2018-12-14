package ee.testijad.id.authentication;

import ee.testijad.id.card.IdCard;
import ee.testijad.id.page.AuthenticationPageReader;
import ee.testijad.id.util.Config;
import ee.testijad.id.util.OperatingSystem;
import ee.testijad.id.webdriver.WebDrivers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import static org.junit.Assert.assertTrue;

public abstract class BrowserAuthenticationTest {

    private static final int TIMEOUT_AFTER_TEST_IN_MILLISECONDS = 0;

    Pattern certificateReady;
    Pattern certificateOkButton;
    Pattern pinFieldReady;

    WebDriver driver;
    Screen screen;
    IdCard testCard;
    private AuthenticationPageReader authenticationPage;

    abstract void setUpImagePatterns();

    abstract void setUpDriver();

    @Before
    public void setUp() {
        OperatingSystem.printSystemData();
        setUpImagePatterns();
        setUpDriver();
        testCard = IdCard.getTestCard();
        authenticationPage = new AuthenticationPageReader(driver);
    }

    @Test
    public void browserAuthenticationTest() {
        getAuthenticationPage();
        authenticate();
        WebDrivers.waitUntilAlertNotPresent(driver, Config.WEBDRIVER_WAIT_TIMEOUT_IN_SECONDS);
        authenticationPage.waitUntilSuccessPage();
        assertTrue(authenticationPage.hasSuccessMessage());
    }

    void authenticate() {
        screen = new Screen();
        try {
            selectCertificate();
            enterPin();
        } catch (FindFailed e) {
            e.printStackTrace();
        }
    }

    void selectCertificate() throws FindFailed {
        screen.wait(certificateReady, Config.WEBDRIVER_WAIT_TIMEOUT_IN_SECONDS);
        screen.click(certificateOkButton);
    }

    void enterPin() throws FindFailed {
        screen.wait(pinFieldReady, Config.WEBDRIVER_WAIT_TIMEOUT_IN_SECONDS);
        screen.click(pinFieldReady);
        screen.type(testCard.getPin1());
        screen.type(Key.ENTER);
    }

    private void getAuthenticationPage() {
        new Thread(() -> {
            authenticationPage.open();
        }).start();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            try {
                Thread.sleep(TIMEOUT_AFTER_TEST_IN_MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.quit();
        }
    }
}
