package com.example.healthapp;

import com.example.healthapp.datatype.Weight;

import org.junit.Test;

import static org.junit.Assert.*;

public class WeightUnitTest {
    @Test
    public void assertId(){
        Weight w = new Weight();
        w.setId(1);
        assertEquals(1,w.getId());
    }

    @Test
    public void assertUserId(){
        Weight w = new Weight();
        w.setUserId(1);
        assertEquals(1,w.getUserId());
    }

    @Test
    public void assertDate(){
        Weight w = new Weight();
        w.setDate("2021-03-22");
        assertEquals("2021-03-22",w.getDate());
    }

    @Test
    public void assertWeight(){
        Weight w = new Weight();
        w.setWeight(13.2);
        assertTrue(w.getWeight() - 13.2 < 0.001);
    }

}
