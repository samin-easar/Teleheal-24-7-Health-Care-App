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
        descriptionTextView.setText(currentItem.getPrice()+"/-");

        return convertView;
    }
}
public class LabTest extends AppCompatActivity {
    Button back;
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