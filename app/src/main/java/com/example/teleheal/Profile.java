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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    TextView fname,uname,uemail,upassword;
    Button homebtn;
    FirebaseUser user;
    DatabaseReference ref;
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

        //user= FirebaseAuth.getInstance().getCurrentUser();
       // ref= FirebaseDatabase.getInstance().getReference("Users");
        //userID=user.getUid();

        /*ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //User userprofile= snapshot.getValue(User.class);
                    String fullname=snapshot.child(userID).child("fullname").getValue(String.class);
                    String email=snapshot.child(userID).child("email").getValue(String.class);
                    String username=snapshot.child(userID).child("username").getValue(String.class);
                    String password=snapshot.child(userID).child("password").getValue(String.class);

                    fname.setText(fullname);
                    uname.setText(username);
                    uemail.setText(email);
                    upassword.setText(password);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile.this,"Something Went Wrong !",Toast.LENGTH_SHORT).show();
            }
        });*/

        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Profile.this,Home.class);
                startActivity(intent);
            }
        });


    }
}