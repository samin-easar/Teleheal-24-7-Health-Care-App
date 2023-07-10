package com.example.teleheal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Alerter extends AppCompatActivity implements View.OnClickListener{

     private TimePicker tp;
     private Button setbtn;
     Button ext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_alerter);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        tp=findViewById(R.id.timepicker);
        setbtn=findViewById(R.id.setalarm);
        setbtn.setOnClickListener(this);

        ext= findViewById(R.id.exit);
        ext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
                //System.exit(0);
                startActivity(new Intent(Alerter.this,Home.class));
            }
        });

    }
   //@RequiresApi(api = Build.VERSION.M)
    @Override
    public void onClick(View view){
        Calendar cal=Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH),
                tp.getHour(),
                tp.getMinute(),0);
        Alarm_set(cal.getTimeInMillis());
    }
    private void Alarm_set(long  timeInMillis)
    {
        AlarmManager alarmManager= (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,Alarm.class);
        //PendingIntent pendingIntent=PendingIntent.getBroadcast(this,0,intent,0);
        //alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,timeInMillis,AlarmManager.INTERVAL_DAY,pendingIntent);
        Toast.makeText(this,"Your Alarm is Set",Toast.LENGTH_LONG).show();
    }
}