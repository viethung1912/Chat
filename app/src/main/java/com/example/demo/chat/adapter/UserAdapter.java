package com.example.demo.chat.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.chat.MessagerActivity;
import com.example.demo.chat.R;
import com.example.demo.chat.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolderUser> {
    Context context;
    int layout;
    List<User> userList;

    public UserAdapter(Context context, int layout, List<User> userList) {
        this.context = context;
        this.layout = layout;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolderUser onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        ViewHolderUser viewHolderUser = new ViewHolderUser(view);
        return viewHolderUser;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderUser holder, int position) {
        final User user = userList.get(position);
        holder.txtUserName.setText(user.getName());
        holder.cardviewContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MessagerActivity.class);
                intent.putExtra("user", user);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolderUser extends RecyclerView.ViewHolder {
        CardView cardviewContainer;
        TextView txtUserName;
        public ViewHolderUser(@NonNull View itemView) {
            super(itemView);
            txtUserName = itemView.findViewById(R.id.txtUserName);
            cardviewContainer = itemView.findViewById(R.id.cardviewContainer);
        }
    }
}
