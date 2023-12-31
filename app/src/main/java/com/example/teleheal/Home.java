package com.example.teleheal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {

    String userUsername = HelperClass.stringToPass;
    Button logout;

    TextView username;
    RelativeLayout profile, findDoctor, reminder, article, medicine, labtest,order,emergency;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        emergency= findViewById(R.id.emergency);
        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,Emergency.class);
                startActivity(intent);
            }
        });

        order=findViewById(R.id.order);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,Cart.class);
                startActivity(intent);
            }
        });

        username = findViewById(R.id.username);
        username.setText(userUsername);

        labtest=findViewById(R.id.labtest);
        labtest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,LabTest.class);
                startActivity(intent);
            }
        });

        medicine=findViewById(R.id.medicine);
        medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Home.this,Buy_Medicine.class);
                startActivity(intent);
            }
        });

        profile=findViewById(R.id.profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Home.this,Profile.class);
                startActivity(intent);
                finish();
            }
        });

        findDoctor=findViewById(R.id.appointment);
        findDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(new Intent(Home.this,FindDoctor.class));
                startActivity(intent);
            }
        });

        logout=(Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                signoutUser();
               //startActivity(new Intent(Home.this,Login.class));
            }
        });

        reminder = findViewById(R.id.reminder);
        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(new Intent(Home.this,Alerter.class));
                startActivity(intent);
            }
        });

        article = findViewById(R.id.article);
        article.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(new Intent(Home.this,HealthArticle.class));
                startActivity(intent);
            }
        });


    }

    private void signoutUser() {
        Intent intent=new Intent(Home.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}