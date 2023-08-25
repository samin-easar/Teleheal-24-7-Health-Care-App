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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
class AppointmentAdapter extends ArrayAdapter<Info> {
    ImageView delete;
    public AppointmentAdapter(Context context, List<Info> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.appointment,parent,false);

        }
        Info currentItem = getItem(position);
        TextView nameView = convertView.findViewById(R.id.dname);
        TextView priceTextView = convertView.findViewById(R.id.fee);
        TextView chemberView = convertView.findViewById(R.id.chember);
        TextView dateView = convertView.findViewById(R.id.date);
        TextView timeView = convertView.findViewById(R.id.time);


        nameView.setText(currentItem.getDname());
        priceTextView.setText("Fee : "+currentItem.getFee()+" / Tk");
        chemberView.setText(currentItem.getChember());
        dateView.setText("Date : "+currentItem.getDate());
        timeView.setText("Time : "+currentItem.getTime());

        return convertView;
    }
}

public class Appointments extends AppCompatActivity {

    String userUsername = HelperClass.stringToPass;

    ListView listView;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> dataList = new ArrayList<>();
    private DatabaseReference databaseReference;

    Button back;
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_appointments);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        username=findViewById(R.id.username);
        username.setText(userUsername);



        List<Info> itemlist;
        AppointmentAdapter adapter1;
        itemlist=new ArrayList<>();
        adapter1= new AppointmentAdapter(this,itemlist);
        listView=findViewById(R.id.appointmentList);
        listView.setAdapter(adapter1);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("appointments").child(userUsername);

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

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Appointments.this,Profile.class);
                startActivity(intent);
            }
        });
    }
}