package com.example.demo.chat.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.chat.R;
import com.example.demo.chat.model.Chat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MessagerAdapter extends RecyclerView.Adapter<MessagerAdapter.ViewHolerMess> {

    private FirebaseUser firebaseUser;
    public static  final int MSG_TYPE_LEFT = 0;
    public static  final int MSG_TYPE_RIGHT = 1;

    Context context;
    List<Chat> chatList;

    public MessagerAdapter(Context context, List<Chat> chatList) {
        this.context = context;
        this.chatList = chatList;
    }

    @NonNull
    @Override
    public MessagerAdapter.ViewHolerMess onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_right, parent, false);
            return new ViewHolerMess(view);
        }
        else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_left, parent, false);
            return new ViewHolerMess(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MessagerAdapter.ViewHolerMess holder, int position) {
        Chat chat = chatList.get(position);
        holder.txtMess.setText(chat.getMessager());
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public class ViewHolerMess extends RecyclerView.ViewHolder {

        TextView txtMess;

        public ViewHolerMess(@NonNull View itemView) {
            super(itemView);
            txtMess = itemView.findViewById(R.id.txtMess);
        }
    }

    @Override
    public int getItemViewType(int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(chatList.get(position).getSender().equals(firebaseUser.getUid())) {
            return MSG_TYPE_RIGHT;
        }
        else return MSG_TYPE_LEFT;
    }
}
