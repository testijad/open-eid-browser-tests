package ee.testijad.id.resource;

import ee.testijad.id.util.OperatingSystem;

import java.io.File;
import java.net.URL;
import java.util.regex.Matcher;

public class Resources {

    public static String getProjectRootPath() {
        URL url = Resources.class.getProtectionDomain().getCodeSource().getLocation();
        File file = new File(String.valueOf(url));
        String path = "";
        if (OperatingSystem.isLinux() || OperatingSystem.isMac()) {
            path += "/";
        }
        path += file.getParentFile().getParentFile().getPath().replace(getFileSeparatorPath("file:/"), "");
        return getFileSeparatorPath(path + "/");
    }

    public static File getExtension(String extensionName) {
        return new File(getFileSeparatorPath(String.format("%swebdrivers/extensions/%s", getProjectRootPath(), extensionName)));
    }

    public static String getSystemImageFolder() {
        return getFileSeparatorPath("src/main/resources/images/" + OperatingSystem.getString() + "/");
    }

    public static String getChromeImageFolder() {
        return getFileSeparatorPath("src/main/resources/images/chrome/");
    }

    public static String getFirefoxImageFolder() {
        return getFileSeparatorPath("src/main/resources/images/firefox/");
    }

    public static String getFileSeparatorPath(String path) {
        return path.replaceAll("/", Matcher.quoteReplacement(File.separator));
    }

}
