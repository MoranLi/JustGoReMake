package com.example.healthapp;

import com.example.healthapp.datatype.ExerciseDaily;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExerciseDailyUnitTest {
    @Test
    public void testId() {
        ExerciseDaily e = new ExerciseDaily();
        e.setId(1);
        assertEquals(1,e.getId());
    }
    @Test
    public void testUserId() {
        ExerciseDaily e = new ExerciseDaily();
        e.setUserId(1);
        assertEquals(1,e.getUserId());
    }
    @Test
    public void testExerciseId() {
        ExerciseDaily e = new ExerciseDaily();
        e.setExerciseId(1);
        assertEquals(1,e.getExerciseId());
    }
    @Test
    public void testDate() {
        ExerciseDaily e = new ExerciseDaily();
        e.setDate("2021-01-11");
        assertEquals("2021-01-11",e.getDate());
    }
}
