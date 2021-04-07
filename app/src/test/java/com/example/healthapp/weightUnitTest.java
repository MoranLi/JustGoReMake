package com.example.healthapp;

import com.example.healthapp.datatype.weight;

import org.junit.Test;

import static org.junit.Assert.*;

public class weightUnitTest {
    @Test
    public void assertId(){
        weight w = new weight();
        w.setId(1);
        assertEquals(1,w.getId());
    }

    @Test
    public void assertUserId(){
        weight w = new weight();
        w.setUser_id(1);
        assertEquals(1,w.getUser_id());
    }

    @Test
    public void assertDate(){
        weight w = new weight();
        w.setDate("2021-03-22");
        assertEquals("2021-03-22",w.getDate());
    }

    @Test
    public void assertWeight(){
        weight w = new weight();
        w.setWeight(13.2);
        assertTrue(w.getWeight() - 13.2 < 0.001);
    }

}
