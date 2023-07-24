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

public class LabTest extends AppCompatActivity {

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;

    Button back;
    private  String [][] tests={
            {"Full Body CheckUp","1999"},
            {"Blood Glucose Test","199"},
            {"Liver Function Test","499"},
            {"Thryoid Check","299"},
            {"Antibody Check","149"},
    };

    private  String[] test_details={
            "You want to live healthy? We have a guideline through all the meaningful service packages.\n"+"Comprehensive physical exams are essential in maintaining good health.\n"+
                    "Regular health check-up can find problems before they start\n. Routine health check-up can help determine problems early with better chances .",
            "A blood glucose test measures the level of glucose (sugar) in your blood. The test can involve a finger prick or a blood draw from your vein. Healthcare providers most commonly use blood glucose tests to screen for Type 2 diabetes, which is a common condition.",
            "Liver function tests (also called LFTs) are blood tests that can provide information about how your liver is working. Your liver is a large organ in your abdomen (tummy) that has many different functions.",
            "Thyroid tests tell your healthcare provider how well your thyroid gland works. These tests can help diagnose conditions like hyperthyroidism, hypothyroidism, Graves’ disease, Hashimoto’s disease and thyroid cancer. Types of thyroid tests include blood tests, imaging tests and nuclear medicine tests.",
            "Antibody tests (serology tests) look for antibodies in your blood. Antibodies are proteins your immune system makes to fight infection. These tests help your provider confirm a diagnosis of a wide range of diseases, disorders and infections, including COVID-19. Talk to your provider about whether you need an antibody test."
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_lab_test);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LabTest.this,Home.class);
                startActivity(intent);
            }
        });

        list = new ArrayList();
        for(int i = 0; i<tests.length; i++)
        {
            item = new HashMap<String, String>();
            item.put("line1", tests[i][0]);
            item.put("line2", "Price : "+tests[i][1]+"/- Tk");

            list.add(item);
        }
        sa = new SimpleAdapter(this, list, R.layout.m_multi_lines,
                new String[]{"line1","line2"},
                new int[]{R.id.line_a, R.id.line_b});
        ListView lst = findViewById(R.id.listViewlt);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it= new Intent(LabTest.this,Lab_Test_Details.class);
                it.putExtra("text1",tests[i][0]);
                it.putExtra("text2",test_details[i]);
                it.putExtra("text3",tests[i][1]);
                startActivity(it);
            }
        });
    }
}