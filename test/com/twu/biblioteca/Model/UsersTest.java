package com.twu.biblioteca.Model;

import com.twu.biblioteca.Model.Users;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UsersTest {
    private static Users user1 = new Users("abc-1234", "123456", "小明", "xiaoming@123.com", "13012345678");
    private static Users user2 = new Users("abc-1235", "654321", "小红", "xiaohong@123.com", "18712345678");
    public static List<Users> userList = new ArrayList<Users>();


    @BeforeClass
    public static void initialUserList() {
        userList.add(user1);
        userList.add(user2);
    }

    @Test
    public void loginTest() {
        boolean expected = true;
        boolean actual = Users.login("abc-1234", "123456");
        assertEquals(expected, actual);
    }

    @Test
    public void loginTestWithError() {
        boolean expected = false;
        boolean actual = Users.login("abc-1234", "111111");
        assertEquals(expected, actual);
    }

    @Test
    public void listUserInfoTest() {
        String expected = "abc-1234 | 小明 | xiaoming@123.com | 13012345678";
        String actual = Users.listUserInfo("abc-1234");
        assertEquals(expected, actual);
    }
}
