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

public class Buy_Medicine_Details extends AppCompatActivity {
    TextView medicinename,mdetails,mprice;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_buy_medicine_details);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        medicinename=findViewById(R.id.medicinename);
        mdetails=findViewById(R.id.mdetails);
        mprice=findViewById(R.id.mprice);
        back=findViewById(R.id.back);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        String details = intent.getStringExtra("details");

        medicinename.setText(name);
        mprice.setText("Price : "+price+"/- Tk");
        mdetails.setText("Description : \n"+details);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Buy_Medicine_Details.this,Buy_Medicine.class);
                startActivity(intent);
            }
        });
    }
}