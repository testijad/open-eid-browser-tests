package ee.testijad.id.util;

public enum OperatingSystem {

    WINDOWS, LINUX, MAC;

    public static void printSystemData() {
        System.out.println("\nSystem data: \n");
        System.out.println("System architecture: " + OperatingSystem.getArchitecture());
        System.out.println("OS version: " + OperatingSystem.getVersion());
        System.out.println("Raw OS name: " + System.getProperty("os.name"));
        System.out.println("Modified OS name: " + OperatingSystem.get());
        System.out.println("OS type: " + OperatingSystem.getName());
    }

    public static OperatingSystem get() {
        String os = getString();
        if (os.contains("windows")) {
            return WINDOWS;
        }
        if (os.contains("linux")) {
            return LINUX;
        }
        if (os.contains("mac")) {
            return MAC;
        }
        throw new RuntimeException("Operating system could not be resolved: " + os);
    }

    public static String getString() {
        return System.getProperty("os.name").replaceAll("\\s+", "_").toLowerCase();
    }

    public static String getArchitecture() {
        return System.getProperty("os.arch");
    }

    public static String getVersion() {
        return System.getProperty("os.version");
    }

    public static String getName() {
        return get().toString().toLowerCase();
    }

    public static boolean isWindows() {
        return get() == WINDOWS;
    }

    public static boolean isLinux() {
        return get() == LINUX;
    }

    public static boolean isMac() {
        return get() == MAC;
    }

    public static boolean isWindows7() {
        return getString().equals("windows_7");
    }

    public static boolean isWindows8() {
        return getString().equals("windows_8");
    }

    public static boolean isWindows81() {
        return getString().equals("windows_8.1");
    }

}
