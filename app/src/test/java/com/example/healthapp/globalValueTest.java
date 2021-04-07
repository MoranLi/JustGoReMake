/**
 * Unit tests for test global values
 */
package com.example.healthapp;


import org.junit.Test;

import static org.junit.Assert.*;

public class globalValueTest {
    @Test
    public void assertGlobalId(){
        assertEquals(globalValue.getCurrentUserId(),0);
        globalValue.setCurrentUserId(1);
        assertEquals(globalValue.getCurrentUserId(),1);
    }

    @Test
    public void assertGlobalName(){
        assertEquals(globalValue.getCurrentUserName(),"");
        globalValue.setCurrentUserName("admin");
        assertEquals(globalValue.getCurrentUserName(),"admin");
    }

    @Test
    public void assertGlobalMaxId(){
        assertEquals(globalValue.getCurrentMaxUserId(),0);
        globalValue.setCurrentMaxUserId(2);
        assertEquals(globalValue.getCurrentMaxUserId(),2);
    }

}
