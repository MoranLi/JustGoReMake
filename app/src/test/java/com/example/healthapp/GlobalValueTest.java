/**
 * Unit tests for test global values
 */
package com.example.healthapp;


import org.junit.Test;

import static org.junit.Assert.*;

public class GlobalValueTest {
    @Test
    public void assertGlobalId(){
        assertEquals(GlobalValue.getCurrentUserId(),0);
        GlobalValue.setCurrentUserId(1);
        assertEquals(GlobalValue.getCurrentUserId(),1);
    }

    @Test
    public void assertGlobalName(){
        assertEquals(GlobalValue.getCurrentUserName(),"");
        GlobalValue.setCurrentUserName("admin");
        assertEquals(GlobalValue.getCurrentUserName(),"admin");
    }

    @Test
    public void assertGlobalMaxId(){
        assertEquals(GlobalValue.getCurrentMaxUserId(),0);
        GlobalValue.setCurrentMaxUserId(2);
        assertEquals(GlobalValue.getCurrentMaxUserId(),2);
    }

}
