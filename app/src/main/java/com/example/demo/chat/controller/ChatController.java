package com.example.demo.chat.controller;

import android.content.Context;

import com.example.demo.chat.DAO.ChatDAO;
import com.example.demo.chat.adapter.MessagerAdapter;
import com.example.demo.chat.model.Chat;

import java.util.List;

public class ChatController {
    Context context;
    ChatDAO chatDAO;

    public ChatController(Context context) {
        this.context = context;
        chatDAO = new ChatDAO();
    }

    public  void sendMessager(String sender, String receiver, String messager){
        chatDAO.sendMessager(sender,receiver,messager);
    }

    public void readMessager(String myid, String uid, List<Chat> chatList, MessagerAdapter adapter) {
        chatDAO.readMessager(myid, uid, chatList, adapter);
    }
}
