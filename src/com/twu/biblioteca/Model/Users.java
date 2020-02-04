package com.twu.biblioteca.Model;

import java.util.ArrayList;
import java.util.List;

public class Users {
    public static List<Users> userList = new ArrayList<Users>();

    static {
        userList.add(new Users("abc-1234", "123456", "小明", "xiaoming@123.com", "13012345678"));
        userList.add(new Users("abc-1235", "654321", "小红", "xiaohong@123.com", "18712345678"));
    }

    private String userName;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;

    public Users(String userName, String password, String name, String email, String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static boolean login(String userName, String password) {
        for (Users user : userList) {
            if (user.userName.equals(userName) && user.password.equals(password)) {
                return true;
            }
        }
        return false;
    }

    public static String listUserInfo(String userName){
        for (Users user : userList) {
            if (user.userName.equals(userName)) {
                return user.toString();
            }
        }
        return "";
    }

    @Override
    public String toString() {
        return userName
                + " | " + name
                + " | " + email
                + " | " + phoneNumber;
    }
}
