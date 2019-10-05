package com.example.demo.chat.DAO;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.demo.chat.adapter.UserAdapter;
import com.example.demo.chat.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class UserDAO {
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    public UserDAO() {
         database = FirebaseDatabase.getInstance();
         databaseReference = database.getReference("users");

    }

    public void setUsers(String id,final User user){
        databaseReference.child(id).setValue(user);
    }

    public void getListUser(final List<User> userList, final UserAdapter adapter) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot valueUser : dataSnapshot.getChildren()) {
                    User user = valueUser.getValue(User.class);
                    user.setId(valueUser.getKey());
                    userList.add(user);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
