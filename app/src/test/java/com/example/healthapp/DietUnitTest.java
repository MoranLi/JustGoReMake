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
        d.setFood_id(1);
        assertEquals(1, d.getFood_id());
    }
    @Test
    public void testUserId() {
        Diet d = new Diet();
        d.setUser_id(1);
        assertEquals(1, d.getUser_id());
    }
    @Test
    public void testMealType() {
        Diet d = new Diet();
        d.setMeal_type('b');
        assertTrue('b'== d.getMeal_type());
    }
    @Test
    public void testDate() {
        Diet d = new Diet();
        d.setDate("a");
        assertEquals("a",d.getDate());
    }
}
