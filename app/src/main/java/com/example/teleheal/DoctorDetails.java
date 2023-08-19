package com.example.teleheal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class DoctorAdapter extends ArrayAdapter<Info> {

    public DoctorAdapter(Context context, List<Info> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            //convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.dd_multi_lines, parent, false);
        }

        Info currentItem = getItem(position);

        TextView name = convertView.findViewById(R.id.line_a);
        TextView designation = convertView.findViewById(R.id.line_b);
        //TextView chember = convertView.findViewById(R.id.line_c);
        TextView contact = convertView.findViewById(R.id.line_c);
        //TextView fee = convertView.findViewById(R.id.line_e);

        name.setText(currentItem.getDname());
        designation.setText(currentItem.getDesignation());
        //chember.setText(currentItem.getChember());
        contact.setText("Contact : "+currentItem.getContact());
        //fee.setText("Fee : "+currentItem.getFee());

        return convertView;
    }
}
public class DoctorDetails extends AppCompatActivity {


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
    String doctor_details ;
    ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> dataList = new ArrayList<>();
    private DatabaseReference databaseReference;
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
            doctor_details = "dentist";
        else if(title.compareTo("NEUROLOGIST")==0)
            doctor_details = "neurologist";
        else if(title.compareTo("NUTRITIONIST")==0)
            doctor_details = "nutritionist";
        else if(title.compareTo("PSYCHOTHERAPIST")==0)
            doctor_details = "psychotherapist";
        else if(title.compareTo("PULMONOLOGIST")==0)
            doctor_details = "pulmonologist";
        else if(title.compareTo("OB/GYN")==0)
            doctor_details = "gyn";
        else if(title.compareTo("CARDIOLOGIST")==0)
            doctor_details = "cardiologist";
        else if(title.compareTo("VET")==0)
            doctor_details = "vet";

        btn = findViewById(R.id.back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetails.this, FindDoctor.class));
            }
        });

        List<Info> itemlist;
        DoctorAdapter adapter1;
        itemlist=new ArrayList<>();
        adapter1= new DoctorAdapter(this,itemlist);
        listView=findViewById(R.id.listViewdd);
        listView.setAdapter(adapter1);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("doctors").child(doctor_details);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                itemlist.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    Info info=snapshot.getValue(Info.class);
                    if(info!=null){
                        itemlist.add(info);
                    }
                }
                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("TAG", "Database Error: " + databaseError.getMessage());
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Info selectedItem= adapter1.getItem(i);
                Intent it = new Intent(DoctorDetails.this,Book_appointment.class);
                it.putExtra("name",selectedItem.getDname());
                //it.putExtra("designation",selectedItem.getDesignation());
                it.putExtra("chember",selectedItem.getChember());
                it.putExtra("contact",selectedItem.getContact());
                it.putExtra("fee",selectedItem.getFee());
                it.putExtra("desig",selectedItem.getDesignation());

                startActivity(it);
            }
        });



    }
}