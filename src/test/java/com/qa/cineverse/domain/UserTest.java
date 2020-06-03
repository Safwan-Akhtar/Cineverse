package com.qa.cineverse.domain;

import com.qa.cineverse.domain.User;
import com.qa.cineverse.dto.UserDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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

    @Test
    public void userNameNullButOtherNameNotNull() {
        user.setUsername(null);
        other.setUserId(1L);
        assertFalse(user.equals(other));
    }

    @Test
    public void userNamesNotEqual() {
        other.setUsername("Braff");
        assertFalse(user.equals(other));
    }

    @Test
    public void userPasswordNullButOtherNameNotNull() {
        user.setPassword(null);
        other.setUserId (1L);
        assertFalse(user.equals(other));
    }

    @Test
    public void userPasswordNotEqual() {
        other.setPassword("Braff");
        assertFalse(user.equals(other));
    }

    @Test
    public void userMatchingPasswordNullButOtherNameNotNull() {
        user.setMatchingPassword(null);
        other.setUserId (1L);
        assertFalse(user.equals(other));
    }

    @Test
    public void userMatchingPasswordNotEqual() {
        other.setPassword("Braff");
        assertFalse(user.equals(other));
    }

    @Test
    public void userForenameNullButOtherNameNotNull() {
        user.setForename(null);
        other.setUserId (1L);
        assertFalse(user.equals(other));
    }

    @Test
    public void userForenameNotEqual() {
        other.setForename("Braff");
        assertFalse(user.equals(other));
    }

    @Test
    public void userSurnameNullButOtherNameNotNull() {
        user.setSurname(null);
        other.setUserId (1L);
        assertFalse(user.equals(other));
    }

    @Test
    public void userSurnameNotEqual() {
        other.setSurname("Braff");
        assertFalse(user.equals(other));
    }

    @Test
    public void userEmailNullButOtherNameNotNull() {
        user.setSurname(null);
        other.setUserId (1L);
        assertFalse(user.equals(other));
    }

    @Test
    public void userEmailNotEqual() {
        other.setSurname("Braff@aol.com");
        assertFalse(user.equals(other));
    }

    @Test
    public void userRolesNullButOtherNameNotNull() {
        user.setRoles(null);
        other.setUserId (1L);
        assertFalse(user.equals(other));
    }

    @Test
    public void userRolesNotEqual() {
        other.setRoles("ROLE_USER");
        assertFalse(user.equals(other));
    }

    @Test
    public void userActiveNotEqual() {
        other.setActive(true);
        assertFalse(user.equals(other));
    }

    @Test
    public void nullId() {
        user.setUserId(null);
        assertFalse(user.equals(other));
    }

    @Test
    public void userIDDifferent() {
        other.setUserId(1L);
        assertFalse(user.equals(other));
    }

    @Test
    public void otherIdDifferent() {
        other.setUserId(2L);
        assertFalse(user.equals(other));
    }

    @Test
    public void constructorWithoutId() {
        User user = new User(1L, "Boyman", "root",
                "root", "Chris", "Christianson", "test@aol.com",
                true, "ROLE_USER");
        assertNotNull(user.getUserId ());
        assertNotNull(user.getUsername());
    }
}
