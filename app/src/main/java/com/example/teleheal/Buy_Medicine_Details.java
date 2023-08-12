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

public class Buy_Medicine_Details extends AppCompatActivity {
    TextView medicinename,mdetails,mprice;
    Button back,addmedicine;
    String name,price;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference productsRef = database.getReference("products");

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

        name = intent.getStringExtra("name");
        price = intent.getStringExtra("price");
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



        addmedicine=findViewById(R.id.addmedicine);
        addmedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProductToCart();
            }
        });

    }

    private void addProductToCart() {
        final String productname = name;
        final String productPrice = price;
        Query productQuery = productsRef.orderByChild("name").equalTo(productname);
        productQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    Toast.makeText(Buy_Medicine_Details.this,"Product Already Added ", Toast.LENGTH_SHORT).show();
                }
                else{
                    Info product = new Info(name,price);
                    productsRef.push().setValue(product);

                    Toast.makeText(Buy_Medicine_Details.this,"Product Added To Cart",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}