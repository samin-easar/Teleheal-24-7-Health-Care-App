package com.example.teleheal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class Book_appointment extends AppCompatActivity {

    EditText ed1, ed2, ed3;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_book_appointment);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tv = findViewById(R.id.titleappreg);
        ed1 = findViewById(R.id.appregfullname);
        ed2 = findViewById(R.id.appregContact);
        ed3 = findViewById(R.id.appregFees);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);

        Intent intent = getIntent();
        String title = intent.getStringExtra("text1");
        String fullname = intent.getStringExtra("text2");
        String contact = intent.getStringExtra("text3");
        String fees = intent.getStringExtra("text4");

        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(contact);
        ed3.setText("Cons Fees: "+fees+"/-");
    }
}