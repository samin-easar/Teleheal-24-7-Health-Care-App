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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Lab_Test_Details extends AppCompatActivity {
    TextView testdetails,testprice,testname;
    String testName,testPrice;
    Button back,addTest;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference productsRef = database.getReference("products");

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
        testName=intent.getStringExtra("name");
        testPrice=intent.getStringExtra("price");
        testname.setText(intent.getStringExtra("name"));
        testdetails.setText(intent.getStringExtra("details"));
        testprice.setText("Price : "+intent.getStringExtra("price")+"/- Tk");

        addTest=findViewById(R.id.addtest);
        addTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProductToCart();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Lab_Test_Details.this,LabTest.class);
                startActivity(intent);
            }
        });

    }

    private void addProductToCart() {
        final String productname = testName;
        final String productPrice = testPrice;
        Query productQuery = productsRef.orderByChild("name").equalTo(productname);
        productQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    Toast.makeText(Lab_Test_Details.this,"Product Already Added ", Toast.LENGTH_SHORT).show();
                }
                else{
                    Info product = new Info(testName,testPrice);
                    productsRef.push().setValue(product);

                    Toast.makeText(Lab_Test_Details.this,"Product Added To Cart",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
