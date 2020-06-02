package com.qa.cineverse.domain;

import com.qa.cineverse.domain.User;
import com.qa.cineverse.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTest {

    private User user;
    private User other;


    @Before
    public void setUp() {
        user = new User (1L, "Boyman", "root",
                "root", "Chris", "Christianson", "test@aol.com",
                true, "ROLE_USER");
        other = new User (1L, "Boyman", "root",
                "root", "Chris", "Christianson", "test@aol.com",
                true, "ROLE_USER");
    }

    @Test
    public void equalsWithNull() { assertFalse(user.equals(null)); }

    @Test
    public void equalsWithDifferentObject() { assertFalse(user.equals (new Object())); }

    @Test
    public void checkEquality() {
        assertTrue(user.equals(user));
    }

    @Test
    public void checkEqualityBetweenDifferentObjects() {
        assertFalse(user.equals(other));
    }

    @Test(expected=NullPointerException.class)
    public void userNameNullButOtherNameNotNull() {
        user.setUsername (null);
        other.setUserId (1L);
        assertFalse(user.equals(other));
    }

    @Test
    public void userNamesNotEqual() {
        other.setUsername("Braff");
        assertFalse(user.equals(other));
    }





}
