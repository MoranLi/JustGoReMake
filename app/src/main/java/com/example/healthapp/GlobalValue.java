/**
 * class to store the global value that used to generate id for information
 *
 */
package com.example.healthapp;

public class GlobalValue {
    /**
     * user id for current user
     */
    private static int currentUserId = 0;
    /**
     * user id for the next user
     */
    private static int currentMaxUserId = 0;
    /**
     * user name for current user
     */
    private static String currentUserName = "";
    /**
     * weight id for next weight
     */
    private static int currentWeightId = 0;

    public static int getCurrentWeightId() {
        return currentWeightId;
    }

    public static void setCurrentWeightId(int currentWeightId) {
        GlobalValue.currentWeightId = currentWeightId;
    }

    public static int getCurrentUserId() {
        return currentUserId;
    }
    public static void setCurrentUserId(int currentUserId) {
        GlobalValue.currentUserId = currentUserId;
    }

    public static int getCurrentMaxUserId() {
        return currentMaxUserId;
    }

    public static void setCurrentMaxUserId(int currentMaxUserId) {
        GlobalValue.currentMaxUserId = currentMaxUserId;
    }

    public static String getCurrentUserName() {
        return currentUserName;
    }

    public static void setCurrentUserName(String currentUserName) {
        GlobalValue.currentUserName = currentUserName;
    }
}
