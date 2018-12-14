package ee.testijad.id.signing;

import ee.testijad.id.card.IdCard;
import ee.testijad.id.page.SigningPageReader;
import ee.testijad.id.util.Config;
import ee.testijad.id.util.OperatingSystem;
import ee.testijad.id.webdriver.WebDrivers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

import static ee.testijad.id.resource.Patterns.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public abstract class BrowserSigningTest {

    private static final int TIMEOUT_AFTER_TEST_IN_MILLISECONDS = 0;

    WebDriver driver;
    private IdCard testCard;
    private SigningPageReader signingPageReader;

    abstract void setUpDriver();

    @Before
    public void setUp() {
        OperatingSystem.printSystemData();
        setUpDriver();
        WebDrivers.setWindowLocation(driver);
        testCard = IdCard.getTestCard();
        signingPageReader = new SigningPageReader(driver);
    }

    @Test
    public void browserSignatureTest() {
        try {
            signingPageReader.getToSigning();
        } catch (Exception e) {
            fail(e.getMessage());
        }
        sign();
        WebDrivers.waitUntilAlertNotPresent(driver, Config.WEBDRIVER_WAIT_TIMEOUT_IN_SECONDS);
        assertTrue(signingPageReader.hasSignature());
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

    private void sign() {
        Screen screen = new Screen();
        try {
            screen.wait(getSystemCertificateReady(), Config.WEBDRIVER_WAIT_TIMEOUT_IN_SECONDS);
            screen.click(getSystemOk());
            screen.wait(getSystemPin(), Config.WEBDRIVER_WAIT_TIMEOUT_IN_SECONDS);
            screen.type(testCard.getPin2());
            screen.type(Key.ENTER);
            if (OperatingSystem.isLinux()) {
                screen.wait(getSystemCertificateReady(), Config.WEBDRIVER_WAIT_TIMEOUT_IN_SECONDS);
                screen.click(getSystemOk());
            }
        } catch (FindFailed e) {
            e.printStackTrace();
        }
    }

}
