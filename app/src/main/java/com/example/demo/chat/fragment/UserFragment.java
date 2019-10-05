package com.example.demo.chat.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.demo.chat.R;
import com.example.demo.chat.adapter.UserAdapter;
import com.example.demo.chat.controller.UserController;
import com.example.demo.chat.model.User;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends Fragment {

    RecyclerView recyclerUser;
    List<User> userList;
    UserAdapter adapter;
    UserController userController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user, container, false);
        userList = new ArrayList<>();
        userController = new UserController(getContext());

        recyclerUser= view.findViewById(R.id.recyclerUser);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerUser.setLayoutManager(layoutManager);
        adapter = new UserAdapter(getContext(), R.layout.item_user, userList);
        recyclerUser.setAdapter(adapter);
        userController.getListUser(userList, adapter);
        return view;
    }
}
