package com.example.healthapp;

import com.example.healthapp.datatype.Food;

import org.junit.Test;

import static org.junit.Assert.*;


public class FoodUnitTest {
    @Test
    public void assertId(){
        Food f = new Food();
        f.setId(1);
        assertEquals(1,f.getId());
    }
    @Test
    public void assertName(){
        Food f = new Food();
        f.setName("bread");
        assertEquals("bread",f.getName());
    }
    @Test
    public void assertUserId(){
        Food f = new Food();
        f.setUser_id(1);
        assertEquals(1,f.getUser_id());
    }@Test
    public void assertCalories(){
        Food f = new Food();
        f.setCalories(1.0);
        assertTrue(f.getCalories()-1.0 < 0.001);
    }@Test
    public void assertCategory(){
        Food f = new Food();
        f.setCategory(1);
        assertEquals(1,f.getCategory());
    }
    @Test
    public void assertCholesterol(){
        Food f = new Food();
        f.setCholesterol(1.0);
        assertTrue(f.getCholesterol()-1.0 < 0.001);
    }
    @Test
    public void assertProtein(){
        Food f = new Food();
        f.setProtein(1.0);
        assertTrue(f.getProtein()-1.0 < 0.001);
    }
    @Test
    public void assertFat(){
        Food f = new Food();
        f.setFat(1.0);
        assertTrue(f.getFat()-1.0 < 0.001);
    }




}
