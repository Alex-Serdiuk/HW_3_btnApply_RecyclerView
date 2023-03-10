package com.example.lesson_03;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDB{
    private static List<User> users = new ArrayList<>();

    public static void setUsers(List<User> users) {
        UserDB.users = users;
    }

    public static List<User> getAll() {
        users.add(new User(R.drawable.android, "Ivan", "Ivanov", "0666", "ivan@gmail.com"));
        users.add(new User(R.drawable.android, "Petr", "Petrov", "0777", "petr@gmail.com"));
        users.add(new User(R.drawable.android, "Stepan", "Stepanov", "0888", "stepan@gmail.com"));
        return users;
    }

    public static void add(User user) {
        users.add(user);
    }

    public static void setUserById(int id, User user) {
        UserDB.users.set(id, user);
    }
}
