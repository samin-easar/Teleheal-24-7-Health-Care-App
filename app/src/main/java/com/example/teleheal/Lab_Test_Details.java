package com.example.teleheal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Lab_Test_Details extends AppCompatActivity {
    TextView testdetails,testprice,testname;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_lab_test_details);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        back=findViewById(R.id.back);
        testdetails=findViewById(R.id.testdetails);
        testprice=findViewById(R.id.testprice);
        testname=findViewById(R.id.testname);

        Intent intent=getIntent();
        testname.setText(intent.getStringExtra("text1"));
        testdetails.setText(intent.getStringExtra("text2"));
        testprice.setText("Price : "+intent.getStringExtra("text3")+"/- Tk");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Lab_Test_Details.this,LabTest.class);
                startActivity(intent);
            }
        });
    }
}