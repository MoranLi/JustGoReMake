package com.example.justgo;

public class globalValue {
    private static int currentUserId = 0;
    private static int currentMaxUserId = 0;
    private static String currentUserName = "";
    public static int getCurrentUserId() {
        return currentUserId;
    }
    public static void setCurrentUserId(int currentUserId) {
        globalValue.currentUserId = currentUserId;
    }

    public static int getCurrentMaxUserId() {
        return currentMaxUserId;
    }

    public static void setCurrentMaxUserId(int currentMaxUserId) {
        globalValue.currentMaxUserId = currentMaxUserId;
    }

    public static String getCurrentUserName() {
        return currentUserName;
    }

    public static void setCurrentUserName(String currentUserName) {
        globalValue.currentUserName = currentUserName;
    }
}
