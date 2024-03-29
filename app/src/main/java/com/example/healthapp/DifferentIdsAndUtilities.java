/**
 * class to store the global value that used to generate id for information
 *
 */
package com.example.healthapp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DifferentIdsAndUtilities {
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
    /**
     * weight id for next weight
     */
    private static int currentDietId = 0;
    /**
     * exericse daily id for next exercise record
     */
    private static int currentExerciseDailyId = 0;

    /**
     * id for experience
     */
    private static int currentExperienceId = 0;

    /**
     * getters and increment by 1
     */
    public static int getCurrentWeightId() {
        return currentWeightId+=1;
    }

    public static int getCurrentUserId() {
        return currentUserId;
    }

    public static void setCurrentUserId(int currentUserId) {
        DifferentIdsAndUtilities.currentUserId = currentUserId;
    }

    public static int getCurrentMaxUserId() {
        return currentMaxUserId+=1;
    }

    public static String getCurrentUserName() {
        return currentUserName;
    }

    public static void setCurrentUserName(String currentUserName) {
        DifferentIdsAndUtilities.currentUserName = currentUserName;
    }

    public static int getCurrentDietId() {
        return currentDietId+=1;
    }

    public static int getCurrentExerciseDailyId() {
        return currentExerciseDailyId+=1;
    }

    public static int getCurrentExperienceId() {
        return currentExperienceId+=1;
    }

    /**
     * today in string
     * @return
     */
    public static String currentDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        return date;
    }
}
