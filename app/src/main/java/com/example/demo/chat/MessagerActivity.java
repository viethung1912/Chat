package com.example.demo.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.chat.adapter.MessagerAdapter;
import com.example.demo.chat.controller.ChatController;
import com.example.demo.chat.model.Chat;
import com.example.demo.chat.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessagerActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView txtNameUser;

    ImageButton btnSend;
    EditText edtMessager;
    ChatController chatController;
    FirebaseUser firebaseUser;

    RecyclerView recyclerMess;
    ArrayList<Chat> chatList;

    String id = "";

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messager);

        final User user = getIntent().getParcelableExtra("user");
        id = user.getId();

        toolbar = findViewById(R.id.toolbar);
        txtNameUser = findViewById(R.id.txtNameUser);
        btnSend = findViewById(R.id.btnSend);
        edtMessager = findViewById(R.id.edtMessager);
        recyclerMess = findViewById(R.id.recyclerMess);

        chatController = new ChatController(getApplicationContext());

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerMess.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setStackFromEnd(true);
        recyclerMess.setLayoutManager(layoutManager);

        txtNameUser.setText(user.getName());

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg =edtMessager.getText().toString().trim();
                if(!msg.equals("")){
                    sendMessager(firebaseUser.getUid(), user.getId(), msg);
                    readMessager(firebaseUser.getUid(), user.getId());

                }else {
                    Toast.makeText(getApplicationContext(),"Don't Messeger", Toast.LENGTH_SHORT).show();
                }
                edtMessager.setText("");
            }
        });

        readMessager(firebaseUser.getUid(), user.getId());
    }

    public void readMessager(final String myid, final String uid) {
        chatList = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("chats");
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
                    MessagerAdapter adapter = new MessagerAdapter(MessagerActivity.this, chatList);
                    recyclerMess.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void sendMessager(String sender, String receiver, String messager){
        databaseReference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("receiver", receiver);
        hashMap.put("messager", messager);

        databaseReference.child("chats").push().updateChildren(hashMap);
    }

    @Override
    protected void onStart() {
        super.onStart();
        readMessager(firebaseUser.getUid(), id);
    }
}
