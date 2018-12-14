package ee.testijad.id.resource;

import ee.testijad.id.util.OperatingSystem;
import org.sikuli.script.Pattern;

public class Patterns {

    public static Pattern getSystemOk() {
        return new Pattern(Resources.getSystemImageFolder() + "system_ok.png");
    }

    public static Pattern getSystemPin() {
        return new Pattern(Resources.getSystemImageFolder() + "system_pin.png");
    }

    public static Pattern getSystemCertificateReady() {
        if (OperatingSystem.isWindows7() || OperatingSystem.isWindows8() || OperatingSystem.isWindows81()) {
            return new Pattern(Resources.getSystemImageFolder() + "system_certificate_ready.png");
        }
        return getSystemOk();
    }

    public static Pattern getChromeOk() {
        return new Pattern(Resources.getChromeImageFolder() + OperatingSystem.getName() + "_auth_ok.png");
    }

    public static Pattern getChromePin() {
        if (OperatingSystem.isWindows()) {
            return getSystemPin();
        }
        return new Pattern(Resources.getChromeImageFolder() + OperatingSystem.getName() + "_auth_pin.png");
    }

    public static Pattern getFirefoxOk() {
        return new Pattern(Resources.getFirefoxImageFolder() + OperatingSystem.getName() + "_auth_ok.png");
    }

    public static Pattern getFirefoxPin() {
        return new Pattern(Resources.getFirefoxImageFolder() + OperatingSystem.getName() + "_auth_pin.png");
    }

}
