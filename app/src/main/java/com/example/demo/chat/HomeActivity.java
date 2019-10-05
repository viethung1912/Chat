package com.example.demo.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.demo.chat.adapter.ViewPageAdapter;
import com.example.demo.chat.fragment.ChatFragment;
import com.example.demo.chat.fragment.UserFragment;
import com.example.demo.chat.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {
    ImageView imgProfile;
    TextView txtProfileName;

    FirebaseAuth auth;

    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPageAdapter viewPageAdapter;
    FloatingActionButton floatingbtnLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();

//        reference = FirebaseDatabase.getInstance().getReference("users").child(firebaseUser.getUid());

        tabLayout = findViewById(R.id.tablayout);
        floatingbtnLogout = findViewById(R.id.floatingbtnLogout);
        viewPager = findViewById(R.id.viewpage);

        viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());
        viewPageAdapter.addFragment(new ChatFragment(), "Chat");
        viewPageAdapter.addFragment(new UserFragment(), "User");
        viewPager.setAdapter(viewPageAdapter);

        tabLayout.setupWithViewPager(viewPager);

        floatingbtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(getApplicationContext(), "Đăng xuất thành công!!!", Toast.LENGTH_LONG).show();
            }
        });
    }

}
