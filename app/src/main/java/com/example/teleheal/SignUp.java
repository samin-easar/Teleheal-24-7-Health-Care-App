package com.example.teleheal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    TextView loginhere;
    EditText email,fullname,username,password,repass;
    Button signupbtn;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        loginhere=findViewById(R.id.loginhere);
        email=findViewById(R.id.email);
        fullname=findViewById(R.id.fullname);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        signupbtn=findViewById(R.id.signupbtn);
        repass=findViewById(R.id.repassword);
        auth=FirebaseAuth.getInstance();

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database=FirebaseDatabase.getInstance();
                reference=database.getReference("users");

                String Email=email.getText().toString();
                String Fullname=fullname.getText().toString();
                String Username=username.getText().toString();
                String Password=password.getText().toString();
                String RePAssword = repass.getText().toString();
                    auth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                HelperClass helperClass=new HelperClass(Email,Fullname,Username,Password);
                                reference.child(Username).setValue(helperClass);

                                Toast.makeText(SignUp.this,"SignUp Successfully",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(SignUp.this,Login.class));
                            }
                        }
                    });
                }
               // HelperClass helperClass=new HelperClass(Email,Fullname,Username,Password);
                //reference.child(Username).setValue(helperClass);

                //Toast.makeText(SignUp.this,"SignUp Successfully",Toast.LENGTH_LONG).show();

        });

        loginhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUp.this,Login.class);
                startActivity(intent);
            }
        });


    }
}