package com.twu.biblioteca;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UsersTest {
    private static Users user1 = new Users("user1", "123456");
    private static Users user2 = new Users("user2", "123456");
    public static List<Users> userList = new ArrayList<Users>();


    @BeforeClass
    public static void initialUserList() {
        userList.add(user1);
        userList.add(user2);
    }

    @Test
    public void loginTest() {
        boolean expected = true;
        boolean actual = Users.login("user1", "123456");
        assertEquals(expected, actual);
    }

    @Test
    public void loginTestWithError() {
        boolean expected = false;
        boolean actual = Users.login("user1", "111111");
        assertEquals(expected, actual);
    }
}
