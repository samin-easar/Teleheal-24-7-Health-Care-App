package com.example.teleheal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView logo,bg;
    TextView title,slogan;
    Button signup,login;
    Animation topAnim, botAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        signup=findViewById(R.id.signup);
        login=findViewById(R.id.login);
        logo=findViewById(R.id.logoimg);
        //bg=findViewById(R.id.btnbg);
        title=findViewById(R.id.title);
        slogan=findViewById(R.id.subtitle);

        //Animation

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        botAnim=AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        logo.setAnimation(topAnim);
        title.setAnimation(topAnim);
        slogan.setAnimation(topAnim);
       //bg.setAnimation(botAnim);
        login.setAnimation(botAnim);
        signup.setAnimation(botAnim);

        //Changing Activity
        //going to SignUp Activity
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
            }
        });

        //going to Login Activity
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });


    }
}