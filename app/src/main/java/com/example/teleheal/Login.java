package com.example.teleheal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Login extends AppCompatActivity {

    EditText username,password;
    TextView registerhere;
    Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registerhere=findViewById(R.id.register);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        loginbtn=findViewById(R.id.loginbtn);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validUsername()  | !validPassword()){
                    Toast.makeText(Login.this,"Username or Password can't be empty.",Toast.LENGTH_LONG).show();

                }else{
                    checkUser();
                }

            }
        });

        registerhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });
    }

    public Boolean validUsername(){
       String val=username.getText().toString();
       if(val.isEmpty()){
           username.setError("Username can't be empty");
           return false;
       }else{
           username.setError(null);
           return true;
       }
    }
    public Boolean validPassword(){
        String val=password.getText().toString();
        if(val.isEmpty()){
            password.setError("Password can't be empty");
            return false;
        }else{
            password.setError(null);
            return true;
        }
    }
    public void checkUser(){
        String userUsername=username.getText().toString();
        String userPassword=password.getText().toString();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase= reference.orderByChild("username").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    username.setError(null);
                    String passwordFromDB= snapshot.child(userUsername).child("password").getValue(String.class);

                    if(Objects.equals(passwordFromDB,userPassword)){
                        username.setError(null);
                        Toast.makeText(Login.this,"Login Successfully",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(Login.this,Home.class);
                        startActivity(intent);
                    }else{
                        password.setError("Invalid Credentials");
                        password.requestFocus();
                    }
                }else{
                    username.setError("User does not exist");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}