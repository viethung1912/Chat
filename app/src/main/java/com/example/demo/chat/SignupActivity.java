package com.example.demo.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demo.chat.controller.UserController;
import com.example.demo.chat.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    EditText txtName,txtEmail,txtPassword;
    ImageButton imgbtnSignup;
    Button btnSignin;
    UserController userController;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        auth = FirebaseAuth.getInstance();
        txtName=findViewById(R.id.txtName);
        txtEmail=findViewById(R.id.txtEmail);
        txtPassword=findViewById(R.id.txtPassword);
        imgbtnSignup=findViewById(R.id.imgbtnSignup);
        btnSignin=findViewById(R.id.btnSignin);
        userController = new UserController(this);
        user = new User();

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        imgbtnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.createUserWithEmailAndPassword(txtEmail.getText().toString().trim(),
                        txtPassword.getText().toString().trim())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    String id = task.getResult().getUser().getUid();
                                    user.setEmail(txtEmail.getText().toString().trim());
                                    user.setPassword(txtPassword.getText().toString().trim());
                                    user.setName(txtName.getText().toString().trim());
                                    userController.setUsers(id, user);
                                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(getApplicationContext(),"Đăng ký thành công",Toast.LENGTH_LONG).show();
                                }
                                else {
                                    Toast.makeText(getApplicationContext(),"Đăng ký không thành công",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });



    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser == null){
            Toast.makeText(this,"Hiện tại không có tài khoản",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"Hiện tại có tài khoản",Toast.LENGTH_LONG).show();
        }
    }

}
