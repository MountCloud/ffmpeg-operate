package org.mountcloud.ffmepg.util;

public class OSUtils {

    public enum OSType {
        OS_TYPE_LINUX, OS_TYPE_WIN, OS_TYPE_SOLARIS, OS_TYPE_MAC, OS_TYPE_FREEBSD, OS_TYPE_OTHER
    }

    static private OSType getOSType() {
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Windows")) {
            return OSType.OS_TYPE_WIN;
        } else if (osName.contains("SunOS") || osName.contains("Solaris")) {
            return OSType.OS_TYPE_SOLARIS;
        } else if (osName.contains("Mac")) {
            return OSType.OS_TYPE_MAC;
        } else if (osName.contains("FreeBSD")) {
            return OSType.OS_TYPE_FREEBSD;
        } else if (osName.startsWith("Linux")) {
            return OSType.OS_TYPE_LINUX;
        } else {
            // Some other form of Unix
            return OSType.OS_TYPE_OTHER;
        }
    }

    public static final OSType osType = getOSType();
    // Helper static vars for each platform
    public static final boolean WINDOWS = (osType == OSType.OS_TYPE_WIN);
    public static final boolean SOLARIS = (osType == OSType.OS_TYPE_SOLARIS);
    public static final boolean MAC = (osType == OSType.OS_TYPE_MAC);
    public static final boolean FREEBSD = (osType == OSType.OS_TYPE_FREEBSD);
    public static final boolean LINUX = (osType == OSType.OS_TYPE_LINUX);
    public static final boolean OTHER = (osType == OSType.OS_TYPE_OTHER);

    public static void main(String[] args) {
        System.out.println(OSUtils.WINDOWS);
        System.out.println(OSUtils.LINUX);
        System.out.println(OSUtils.MAC);
        System.out.println("OSUtils.main()");
    }

}
