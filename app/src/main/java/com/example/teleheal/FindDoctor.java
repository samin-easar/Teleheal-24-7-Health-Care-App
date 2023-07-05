package com.example.teleheal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class FindDoctor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_find_doctor);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        CardView dentist = findViewById(R.id.dental);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindDoctor.this, DoctorDetails.class);
                intent.putExtra("title","DENTIST");
                startActivity(intent);
            }
        });

        CardView neurologist = findViewById(R.id.neuro);
        neurologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindDoctor.this, DoctorDetails.class);
                intent.putExtra("title","NEUROLOGIST");
                startActivity(intent);
            }
        });

        CardView nutritionist = findViewById(R.id.nutrician);
        nutritionist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindDoctor.this, DoctorDetails.class);
                intent.putExtra("title","NUTRITIONIST");
                startActivity(intent);
            }
        });

        CardView psychotherapist = findViewById(R.id.pshycotherapy);
        psychotherapist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindDoctor.this, DoctorDetails.class);
                intent.putExtra("title","PSYCHOTHERAPIST");
                startActivity(intent);
            }
        });

        CardView lungs = findViewById(R.id.lungs);
        lungs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindDoctor.this, DoctorDetails.class);
                intent.putExtra("title","PULMONOLOGIST");
                startActivity(intent);
            }
        });

        CardView gynea = findViewById(R.id.gynea);
        gynea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindDoctor.this, DoctorDetails.class);
                intent.putExtra("title","OB/GYN");
                startActivity(intent);
            }
        });

        CardView vet = findViewById(R.id.vet);
        vet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindDoctor.this, DoctorDetails.class);
                intent.putExtra("title","VET");
                startActivity(intent);
            }
        });

        CardView heart = findViewById(R.id.heart);
        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindDoctor.this, DoctorDetails.class);
                intent.putExtra("title","CARDIOLOGIST");
                startActivity(intent);
            }
        });

        CardView back = findViewById(R.id.back);
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(FindDoctor.this, Home.class);

                        startActivity(intent);
                    }
                });
    }
}