package com.example.demo.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    FirebaseUser firebaseUser;

    EditText txtEmail,txtPassword;
    ImageButton imgbtnLogin;
    Button btnSignup;

    @Override
    protected void onStart() {
        super.onStart();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser != null){
            startActivity(new Intent(this,HomeActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtEmail=findViewById(R.id.txtEmail);
        txtPassword=findViewById(R.id.txtPassword);
        imgbtnLogin=findViewById(R.id.imgbtnLogin);
        btnSignup=findViewById(R.id.btnSignup);

        auth = FirebaseAuth.getInstance();
        imgbtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               auth.signInWithEmailAndPassword(txtEmail.getText().toString().trim(), txtPassword.getText().toString().trim())
                   .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()){
                               Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                               startActivity(intent);
                               Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                           }else {
                               Toast.makeText(getApplicationContext(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(i);
            }
        });
    }
}
