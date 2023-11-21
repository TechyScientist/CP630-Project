package net.johnnyconsole.cp630.project.web.util;

public class ApplicationSession {

    public static String username, name;
    public static int accessLevel;

    public static void set(String username, String name, int accessLevel) {
        ApplicationSession.username = username;
        ApplicationSession.name = name;
        ApplicationSession.accessLevel = accessLevel;
    }

    public static void clear() {
        username = name = null;
        accessLevel = 0;
    }
}
