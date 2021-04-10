package com.example.healthapp;

import com.example.healthapp.datatype.Exercise;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExerciseUnitTest {
    @Test
    public void assertId(){
        Exercise e = new Exercise();
        e.setId(1);
        assertEquals(1,e.getId());
    }
    @Test
    public void assertCategory(){
        Exercise e = new Exercise();
        e.setCategory(1);
        assertEquals(1,e.getCategory());
    }
    @Test
    public void assertName(){
        Exercise e = new Exercise();
        e.setName("swim");
        assertEquals("swim",e.getName());
    }
    @Test
    public void assertCalories(){
        Exercise e = new Exercise();
        e.setEnergy_consumption(1.0);
        assertTrue(e.getEnergy_consumption()-1.0 < 0.001);
    }
}
