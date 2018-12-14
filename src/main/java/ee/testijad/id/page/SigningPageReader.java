package ee.testijad.id.page;

import ee.testijad.id.util.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SigningPageReader {

    public static final String SIGN_BUTTON_ID = "click1";
    static final String CERTIFICATE_INFORMATION_XPATH = "//*[@id=\"log\"]/div[4]";
    static final String SIGNATURE_INFORMATION_XPATH = "//*[@id=\"log\"]/div[5]";
    private WebDriver driver;
    private String signature;

    public SigningPageReader(WebDriver driver) {
        this.driver = driver;
    }

    private String getLogText() {
        WebElement log = driver.findElement(By.id("log"));
        return log.getText();
    }

    private void readCertificateInformation() {
        waitUntilElementIsPresentByXpath(SIGNATURE_INFORMATION_XPATH);
        WebElement signatureInformationDiv = driver.findElement(By.xpath(SIGNATURE_INFORMATION_XPATH));
        signature = signatureInformationDiv.getText().split("Generated signature:\n")[1];
        System.out.println("Generated signature:\n" + signature);
    }

    public boolean hasSignature() {
        if (signature == null) {
            readCertificateInformation();
        }
        return signature != null;
    }

    public boolean hasError() {
        return getLogText().contains("Error:");
    }

    private void waitUntilElementIsPresentByXpath(String xpath) {
        WebDriverWait wait = new WebDriverWait(driver, Config.WEBDRIVER_WAIT_TIMEOUT_IN_SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    public void getToSigning() {
        driver.get(Config.SIGNING_PAGE_URL);
        WebElement signButton = driver.findElement(By.id(SigningPageReader.SIGN_BUTTON_ID));
        signButton.click();
        try {
            if (hasError()) {
                throw new RuntimeException(getLogText());
            }
        } catch (UnhandledAlertException e) {
            // When the signing dialogue is already open
        }
    }

}
