package com.example.teleheal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Buy_Medicine extends AppCompatActivity {

    private  String[][] medicine=
            {
                    {"Uprise-03 100IU Capsule","50"},
                    {"Vitamin B Complex Capsules ","305"},
                    {"Inlife Vitamin E ","120"},
                    {"Dolo 650 Tablet","30"},
                    {"Strepsils Medicated Lozenges for sore thorat","240"},
                    {"Feronia -XT Tablet","130"},
                    {"Crocin 650 Advance Tablet","30"}
            };

    private  String[]medicine_details={
            "Building and Keeping the bones & teeth string\n"+"Reducing Fatigues/Stress and musculer pains.\n",
                    "Boosting Immunity and increasing resistance against infection","Chromium is an essential trace mineral that plays an important role in helping insulin regularly",
            "Provides relief from vitamin b deficencies\n"+"Helps in formation of red blood.\n",
                    "Maintains healthy nervous system"+"It promotes health as well as skin benefit.\n",
            "It helps reduce skin blemish and pigmentation.\n",
            "It acts as safegurd the skin from the harsh UVA and UVB sun rays.","Helps blocking fever and reliving pains of certain chemical mese.\n"
    };
    Button  back;
    ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> dataList = new ArrayList<>();
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_buy_medicine);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Buy_Medicine.this,Home.class);
                startActivity(intent);
            }
        });

        listView=findViewById(R.id.listViewbm);
        //adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        adapter = new ArrayAdapter<>(this, R.layout.m_multi_lines, dataList);
        listView.setAdapter(adapter);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("medicines");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Assuming your data is a string, you can change the class accordingly
                    //dataList.add(snapshot.getValue().toString());
                    Info data= snapshot.getValue(Info.class);
                    String txt = "Name : "+data.getName()+"\n"+"Price : "+data.getPrice();
                    dataList.add(txt);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("TAG", "Database Error: " + databaseError.getMessage());
            }
        });




    }
}