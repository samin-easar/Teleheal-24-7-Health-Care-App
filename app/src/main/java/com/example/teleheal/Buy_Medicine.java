package com.example.teleheal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class Buy_Medicine extends AppCompatActivity {

    private  String[][] medicine=
            {
                    {"Uprise-03 100IU Capsule","","","","50"},
                    {"Vitamin B Complex Capsules ","","","","305"},
                    {"Inlife Vitamin E ","","","","120"},
                    {"Dolo 650 Tablet","","","","30"},
                    {"Strepsils Medicated Lozenges for sore thorat","","","","240"},
                    {"Feronia -XT Tablet","","","","130"},
                    {"Crocin 650 Advance Tablet","","","","30"}
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

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        //lst=findViewById(R.id.listViewbm);

        list = new ArrayList();
        for(int i = 0; i<medicine.length; i++)
        {
            item = new HashMap<String, String>();
            item.put("line1", medicine[i][0]);
            item.put("line2", medicine[i][1]);
            item.put("line3", medicine[i][2]);
            item.put("line4", medicine[i][3]);
            item.put("line5", "Price : "+medicine[i][4]+"/-");

            list.add(item);
        }
        sa = new SimpleAdapter(this, list, R.layout.dd_multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        ListView lst = findViewById(R.id.listViewbm);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it= new Intent(Buy_Medicine.this,Buy_Medicine_Details.class);
                it.putExtra("text1",medicine[i][0]);
                it.putExtra("text2",medicine_details[i]);
                it.putExtra("text3",medicine[i][4]);
                startActivity(it);
            }
        });

    }
}