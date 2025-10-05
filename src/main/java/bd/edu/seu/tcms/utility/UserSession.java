package bd.edu.seu.tcms.utility;

public class UserSession {
    private static String loggedInUserName;

    public static void loginUser(String userName) {
        loggedInUserName = userName;
    }

    public static void logoutUser() {
        loggedInUserName = null;
    }

    public static String getLoggedInUserName() {
        return loggedInUserName;
    }
}