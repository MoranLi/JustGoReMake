/**
 * unit test for test the user information
 */
package com.example.healthapp;

import com.example.healthapp.datatype.User;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserUnitTest {
    @Test
    public void assertUserName(){
        User u = new User();
        u.setName("a");
        assertEquals(u.getName(),"a");
    }
    @Test
    public void assertUserGender(){
        User u = new User();
        u.setGender("M");
        assertEquals(u.getGender(),"M");
    }
    @Test
    public void assertUserBrithday(){
        User u = new User();
        u.setBirthday("2011-02-11");
        assertEquals(u.getBirthday(),"2011-02-11");
    }
    @Test
    public void assertUserId(){
        User u = new User();
        u.setId(1);
        assertEquals(u.getId(),1);
    }
    @Test
    public void assertUserHeight(){
        User u = new User();
        u.setHeight(111.3);
        assertTrue(u.getHeight()-111.3 < 0.01);
    }
    @Test
    public void assertUserPassword(){
        User u = new User();
        u.setPassword("asdfg");
        assertEquals(u.getPassword(),"asdfg");
    }
}
