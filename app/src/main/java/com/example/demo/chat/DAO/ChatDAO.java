package com.example.demo.chat.DAO;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.chat.MessagerActivity;
import com.example.demo.chat.adapter.MessagerAdapter;
import com.example.demo.chat.model.Chat;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatDAO {

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public ChatDAO() {
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("chats");

    }

    public void sendMessager(String sender, String receiver, String messager){

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("receiver", receiver);
        hashMap.put("messager", messager);

        databaseReference.push().setValue(hashMap);

    }

    public void readMessager(final String myid, final String uid, final List<Chat> chatList, final MessagerAdapter adapter) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chatList.clear();
                for (DataSnapshot valueChat : dataSnapshot.getChildren()) {
                    Chat chat = valueChat.getValue(Chat.class);
                    if (chat.getReceiver().equals(myid) && chat.getSender().equals(uid) ||
                            chat.getReceiver().equals(uid) && chat.getSender().equals(myid)) {
                        chatList.add(chat);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
