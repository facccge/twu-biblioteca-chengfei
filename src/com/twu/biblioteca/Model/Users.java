package com.twu.biblioteca.Model;

import java.util.ArrayList;
import java.util.List;

public class Users {
    public static List<Users> userList = new ArrayList<Users>();

    static {
        userList.add(new Users("abc-1234", "123456"));
        userList.add(new Users("abc-1235", "654321"));
    }

    private String userName;
    private String password;

    public Users(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public static boolean login(String userName, String password) {
        for (Users user : userList) {
            if (user.userName.equals(userName) && user.password.equals(password)) {
                return true;
            }
        }
        return false;
    }
}
