package ee.testijad.id.page;

import ee.testijad.id.util.Config;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthenticationPageReader {

    private final WebDriver driver;

    public AuthenticationPageReader(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        try {
            driver.get(Config.AUTHENTICATION_PAGE_URL);
        } catch (NoAlertPresentException e) {
            System.out.println("Caught NoAlertPresentException");
        } catch (Exception e) {
            System.out.println("Caught exception after authentication");
        }
    }

    public void waitUntilSuccessPage() {
        new WebDriverWait(driver, Config.WEBDRIVER_WAIT_TIMEOUT_IN_SECONDS)
            .until(webDriver -> hasSuccessMessage());
    }

    public boolean hasSuccessMessage() {
        try {
            return driver.getPageSource().contains("Auth successful!");
        } catch (NullPointerException e) {
            return false;
        }
    }

}
