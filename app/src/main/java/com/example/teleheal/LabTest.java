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

class ItemAdapter2 extends ArrayAdapter<Info> {

    public ItemAdapter2(Context context, List<Info> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            //convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.m_multi_lines, parent, false);
        }

        Info currentItem = getItem(position);

        TextView titleTextView = convertView.findViewById(R.id.line_a);
        TextView descriptionTextView = convertView.findViewById(R.id.line_b);

        titleTextView.setText(currentItem.getName());
        descriptionTextView.setText(currentItem.getPrice());

        return convertView;
    }
}
public class LabTest extends AppCompatActivity {

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
        List<Info> itemlist;
        ItemAdapter2 adapter1;
        itemlist=new ArrayList<>();
        adapter1= new ItemAdapter2(this,itemlist);


        listView=findViewById(R.id.listViewlt);
        listView.setAdapter(adapter1);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("labtest");

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
                Intent it = new Intent(LabTest.this,Lab_Test_Details.class);
                it.putExtra("name",selectedItem.getName());
                it.putExtra("price",selectedItem.getPrice());
                it.putExtra("details",selectedItem.getDetails());

                startActivity(it);
            }
        });


    }
}