package com.example.demo.chat.controller;

import android.content.Context;

import com.example.demo.chat.DAO.UserDAO;
import com.example.demo.chat.adapter.UserAdapter;
import com.example.demo.chat.model.User;

import java.util.List;

public class UserController {
    Context context;
    UserDAO userDAO;

    public UserController(Context context) {
        this.context = context;
        userDAO = new UserDAO();
    }

    public void setUsers(String id, User user) {
        userDAO.setUsers(id, user);
    }

    public void getListUser(List<User> userList, UserAdapter adapter) {
        userDAO.getListUser(userList, adapter);
    }
}
