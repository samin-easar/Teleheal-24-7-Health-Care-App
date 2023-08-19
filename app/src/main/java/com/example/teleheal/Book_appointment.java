package com.example.teleheal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Book_appointment extends AppCompatActivity {

    String userUsername = HelperClass.stringToPass;

    EditText ed1, ed2, ed3, chember;
    TextView tv;
    String dt,tm,fullname,contact,fees,chmbr,desig;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button date, time, regbtn, backbtn;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference appointmentRef = database.getReference("appointments").child(userUsername);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_book_appointment);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tv = findViewById(R.id.titleappreg);
        ed1 = findViewById(R.id.appregfullname);
        ed2 = findViewById(R.id.appregContact);
        ed3 = findViewById(R.id.appregFees);
        date = findViewById(R.id.datepick);
        time = findViewById(R.id.timepick);
        regbtn = findViewById(R.id.appregister);
        backbtn = findViewById(R.id.back);
        chember = findViewById(R.id.chember);

        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);

        Intent intent = getIntent();
        //String title = intent.getStringExtra("name");
        fullname = intent.getStringExtra("name");
        contact = intent.getStringExtra("contact");
        fees = intent.getStringExtra("fee");
        chmbr = intent.getStringExtra("chember");
        desig = intent.getStringExtra("desig");

        //tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(contact);
        ed3.setText("Cons Fees: " + fees + "/-");
        chember.setText(chmbr);

        //date
        initDatePicker();
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        //time
        initTimePicker();
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });

        //back
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Book_appointment.this, FindDoctor.class));
            }
        });

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registration();

            }
        });
    }

    private void registration() {
        final String dname = fullname;
        final String dfee = fees;
        final String dcontact = contact;
        final String dchember = chmbr;
        final String ddesig = desig;
        final String rtime = tm;
        final String rdate = dt;
        Query productQuery = appointmentRef.orderByChild("dname").equalTo(dname);
        productQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    Toast.makeText(Book_appointment.this,"Appointment Already Taken", Toast.LENGTH_SHORT).show();
                }
                else{
                    Info product = new Info(dname,dcontact,dchember,ddesig,dfee,rdate,rtime);
                    appointmentRef.push().setValue(product);

                    Toast.makeText(Book_appointment.this,"Appointment Registred Successfully",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1 + 1;
                date.setText(i2 + "/" + i1 + "/" + i);
                dt= (String) date.getText();
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000);
    }

    private void initTimePicker() {

        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                i1 = i1 + 1;
                time.setText(i + " : " + i1);
                tm= String.valueOf(time.getText());
            }
        };

        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int mins = cal.get(Calendar.MINUTE);
        //int ampm = cal.get(Calendar.AM_PM)

        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this, style, timeSetListener, hrs, mins, true);

    }


}