package net.johnnyconsole.cp630.project.client.util;

public class ApplicationSession {

    public static String username = null, name = null;
    public static int accessLevel;

    public static void clear() {
        username = name = null;
        accessLevel = AccessLevel.ACCESS_STANDARD;
    }

}
