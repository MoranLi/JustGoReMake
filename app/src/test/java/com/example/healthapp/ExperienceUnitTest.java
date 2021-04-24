package com.example.healthapp;

import com.example.healthapp.datatype.Diet;
import com.example.healthapp.datatype.Experience;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExperienceUnitTest {

    @Test
    public void testId() {
        Experience e = new Experience();
        e.setId(1);
        assertEquals(e.getId(),1);
    }

    @Test
    public void testDate() {
        Experience e = new Experience();
        e.setDate("2020-03-04");
        assertEquals(e.getDate(),"2020-03-04");
    }

    @Test
    public void testExperience() {
        Experience e = new Experience();
        e.setExperience("good");
        assertEquals(e.getExperience(),"good");
    }


}
