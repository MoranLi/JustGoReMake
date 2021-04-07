/**
 * unit test for test the user information
 */
package com.example.healthapp;

import com.example.healthapp.datatype.user;

import org.junit.Test;

import static org.junit.Assert.*;

public class userUnitTest {
    @Test
    public void assertUserName(){
        user u = new user();
        u.setName("a");
        assertEquals(u.getName(),"a");
    }
    @Test
    public void assertUserGender(){
        user u = new user();
        u.setGender("M");
        assertEquals(u.getGender(),"M");
    }
    @Test
    public void assertUserBrithday(){
        user u = new user();
        u.setBirthday("2011-02-11");
        assertEquals(u.getBirthday(),"2011-02-11");
    }
    @Test
    public void assertUserId(){
        user u = new user();
        u.setId(1);
        assertEquals(u.getId(),1);
    }
    @Test
    public void assertUserHeight(){
        user u = new user();
        u.setHeight(111.3);
        assertTrue(u.getHeight()-111.3 < 0.01);
    }
    @Test
    public void assertUserPassword(){
        user u = new user();
        u.setPassword("asdfg");
        assertEquals(u.getPassword(),"asdfg");
    }
}
