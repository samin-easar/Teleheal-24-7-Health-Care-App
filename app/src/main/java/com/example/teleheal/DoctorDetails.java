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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetails extends AppCompatActivity {
    private String[][] dentist =
            {
                    {"NAME : Dr.Md.Imran Hossain", "HOSPITAL : Dental View Orthodontics & Implant Center", "Chief Consultant (Dental and Maxillofacial Surgery)", "CONTACT:+8801789280929", "500"},
                    {"NAME : Dr.Md.Haider Ali Khan", "HOSPITAL : Dhaka Medical College & Hospital", "Senior Consultant and In Charge (Dentistry)", "CONTACT:+8801611957515", "500"},
                    {"NAME : Dr Kamrun Nahar Sumi", "HOSPITAL : Ibn Sina Diagnostic Center, Uttara", "Oral, Dental & Maxillofacial Specialist Surgeon", "CONTACT: +8809610009612", "500"},
                    {"NAME : Prof. Dr. B.A.K Azad", "HOSPITAL : Professor’s Dental Surgery", "Prosthodontist & Implantologist", "CONTACT: 01715157722", "500"},
                    {"NAME : Assoc. Prof. Dr. Aslam Almehdi", "HOSPITAL : Royal Dental & Maxillofacial Surgery", "Dental & Orofacial Pain", "CONTACT: 01311863611", "500"},
                    {"NAME : Dr. Priyanka Suma", "HOSPITAL : Dr. Priyanka's Dental Clinic", "Dental Surgeon", "CONTACT: 01629331999", "500"},
                    {"NAME : Dr. Roksana Begum", "HOSPITAL : Ibn Sina Diagnostic Center, Dhanmondi", "Fillings, Root Canals, Extractions", "CONTACT: +8809610010615", "500"},
                    {"NAME : Dr. Farzana Anar", "HOSPITAL : Ibn Sina Diagnostic Center, Uttara", "Conservative Dentistry & Endodotics Specialist", "CONTACT:  +8809610009612", "500"},
            };
    private String[][] neurologist =
            {
                    {"NAME : Prof. Dr. Quazi Deen Mohammad", "HOSPITAL : SPRC & Neurology Hospital", "Neurology & Medicine Specialist", "CONTACT: +8801765660811", "1000"},
                    {"NAME : Prof. Dr. Md. Badrul Alam", "HOSPITAL : Central Hospital, Dhanmondi", "Brain, Stroke, Nerve & Migraine Specialist", "CONTACT: +88029660015", "1000"},
                    {"NAME : Dr. Alim Akhtar Bhuiyan", "HOSPITAL : United Hospital, Dhaka", "Brain, Stroke, Epilepsy, Headache Specialist", "CONTACT: 10666", "1000"},
                    {"NAME : Dr. Imran Sarker", "HOSPITAL : Islami Bank Hospital, Motijheel", "Parkinson's & Movement Disorder Specialist", "CONTACT: +8801727666741", "1000"},
                    {"NAME : Dr. Rumana Habib", "HOSPITAL : Popular Diagnostic Center, Dhanmondi", "Brain, Nerve, Headache, Migraine & Medicine Specialist", "CONTACT:  +8809613787801", "1000"},
                    {"NAME : Dr. Shamim Rashid", "HOSPITAL : Labaid Diagnostic, Malibagh", "Neurology & Medicine Specialist", "CONTACT: +8801766662555", "1000"},
                    {"NAME : Dr. Sayeda Shabnam Malik", "HOSPITAL : Enam Medical College & Hospital", "Neurology Specialist", "CONTACT: +8801716358146", "1000"},
                    {"NAME : Dr. Snigdha Sarker", "HOSPITAL : Lakecity Medical Limited", "Neurology Specialist", "CONTACT: +8801910020092", "1000"},
            };
    private String[][] nutritionist =
            {
                    {"NAME : A", "HOSPITAL : Sapporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
                    {"NAME : A", "HOSPITAL : Sapporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
                    {"NAME : A", "HOSPITAL : Sapporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
                    {"NAME : A", "HOSPITAL : Saporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
                    {"NAME : A", "HOSPITAL : Saporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
            };
    private String[][] psychotherapist =
            {
                    {"NAME : A", "HOSPITAL : Saporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
                    {"NAME : A", "HOSPITAL : Saporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
                    {"NAME : A", "HOSPITAL : Saporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
                    {"NAME : A", "HOSPITAL : Saporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
                    {"NAME : A", "HOSPITAL : Saporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
            };
    private String[][] pulmonologist =
            {
                    {"NAME : A", "HOSPITAL : Saporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
                    {"NAME : A", "HOSPITAL : Saporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
                    {"NAME : A", "HOSPITAL : Saporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
                    {"NAME : A", "HOSPITAL : Saporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
                    {"NAME : A", "HOSPITAL : Saporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
            };
    private String[][] gyn =
            {
                    {"NAME : A", "HOSPITAL : Saporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
                    {"NAME : A", "HOSPITAL : Saporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
                    {"NAME : A", "HOSPITAL : Saporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
                    {"NAME : A", "HOSPITAL : Saporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
                    {"NAME : A", "HOSPITAL : Saporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
            };
    private String[][] cardiologist =
            {
                    {"NAME : A", "HOSPITAL : Saporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
                    {"NAME : A", "HOSPITAL : Saporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
                    {"NAME : A", "HOSPITAL : Saporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
                    {"NAME : A", "HOSPITAL : Saporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
                    {"NAME : A", "HOSPITAL : Saporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
            };
    private String[][] vet =
            {
                    {"NAME : A", "HOSPITAL : Saporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
                    {"NAME : A", "HOSPITAL : Saporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
                    {"NAME : A", "HOSPITAL : Saporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
                    {"NAME : A", "HOSPITAL : Saporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
                    {"NAME : A", "HOSPITAL : Saporo", "Experience: 5 yrs", "CONTACT:01718880690", "1000"},
            };
    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_doctor_details);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tv = findViewById(R.id.titledd);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("DENTIST")==0)
            doctor_details = dentist;
        else if(title.compareTo("NEUROLOGIST")==0)
            doctor_details = neurologist;
        else if(title.compareTo("NUTRITIONIST")==0)
            doctor_details = nutritionist;
        else if(title.compareTo("PSYCHOTHERAPIST")==0)
            doctor_details = psychotherapist;
        else if(title.compareTo("PULMONOLOGIST")==0)
            doctor_details = pulmonologist;
        else if(title.compareTo("OB/GYN")==0)
            doctor_details = gyn;
        else if(title.compareTo("CARDIOLOGIST")==0)
            doctor_details = cardiologist;
        else if(title.compareTo("VET")==0)
            doctor_details = vet;

        btn = findViewById(R.id.back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetails.this, FindDoctor.class));
            }
        });

        list = new ArrayList();
        for(int i = 0; i<doctor_details.length; i++)
        {
            item = new HashMap<String, String>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "Cons fees : "+doctor_details[i][4]+"/-");

            list.add(item);
        }
        sa = new SimpleAdapter(this, list, R.layout.dd_multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        ListView lst = findViewById(R.id.listViewdd);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetails.this,Book_appointment.class);

                it.putExtra("text1", title);
                it.putExtra("text2", doctor_details[i][0]);
                it.putExtra("text3", doctor_details[i][3]);
                it.putExtra("text4", doctor_details[i][4]);

                startActivity(it);
            }
        });

    }
}