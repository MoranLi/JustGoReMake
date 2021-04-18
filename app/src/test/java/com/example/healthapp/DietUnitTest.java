package com.example.healthapp;


import com.example.healthapp.datatype.Diet;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class DietUnitTest {
    @Test
    public void testId() {
        Diet d = new Diet();
        d.setId(1);
        assertEquals(1, d.getId());
    }
    @Test
    public void testFoodId() {
        Diet d = new Diet();
        d.setFoodId(1);
        assertEquals(1, d.getFoodId());
    }
    @Test
    public void testUserId() {
        Diet d = new Diet();
        d.setUserId(1);
        assertEquals(1, d.getUserId());
    }
    @Test
    public void testDate() {
        Diet d = new Diet();
        d.setDate("a");
        assertEquals("a",d.getDate());
    }
}
