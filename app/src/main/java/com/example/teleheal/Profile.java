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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    TextView fname,uname,uemail,upassword,ageF,BG;
    Button homebtn,editProfile;
    FirebaseUser user;
    DatabaseReference ref;
    RelativeLayout appointment;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        fname=findViewById(R.id.name);
        uname=findViewById(R.id.dashboardusername);
        uemail=findViewById(R.id.dashboardemail);
        upassword=findViewById(R.id.dashboardpassword);
        homebtn=findViewById(R.id.home);
        editProfile=findViewById(R.id.button);
        ageF=findViewById(R.id.age);
        BG=findViewById(R.id.bg);

        appointment= findViewById(R.id.appointments);
        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this,Appointments.class);
                startActivity(intent);
            }
        });

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Profile.this,Edit_Profile.class);
                startActivity(intent);
                //Toast.makeText(Profile.this, "Edit button Clicked", Toast.LENGTH_LONG).show();
            }
        });

        //Show All data
        showuserdata();

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Profile.this,Home.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void showuserdata(){

        String userUsername = HelperClass.stringToPass;   //receive a string from Login class

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase= reference.orderByChild("username").equalTo(userUsername);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String fullnameFromDB = snapshot.child(userUsername).child("fullname").getValue(String.class);
                    String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
                    String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);
                    String age = snapshot.child(userUsername).child("age").getValue(String.class);
                    String Bg = snapshot.child(userUsername).child("blood group").getValue(String.class);

                    fname.setText(fullnameFromDB);
                    uemail.setText(emailFromDB);
                    uname.setText(usernameFromDB);
                    upassword.setText(passwordFromDB);
                    ageF.setText(age);
                    BG.setText(Bg);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}