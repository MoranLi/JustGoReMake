/**
 * Unit tests for test global values
 */
package com.example.healthapp;


import org.junit.Test;

import static org.junit.Assert.*;

public class DifferentIdsAndUtilitiesTest {
    @Test
    public void assertGlobalId(){
        assertEquals(DifferentIdsAndUtilities.getCurrentUserId(),0);
        DifferentIdsAndUtilities.setCurrentUserId(1);
        assertEquals(DifferentIdsAndUtilities.getCurrentUserId(),1);
    }

    @Test
    public void assertGlobalName(){
        assertEquals(DifferentIdsAndUtilities.getCurrentUserName(),"");
        DifferentIdsAndUtilities.setCurrentUserName("admin");
        assertEquals(DifferentIdsAndUtilities.getCurrentUserName(),"admin");
    }

    @Test
    public void assertGlobalMaxId(){
        assertEquals(DifferentIdsAndUtilities.getCurrentMaxUserId(),1);
    }

}
