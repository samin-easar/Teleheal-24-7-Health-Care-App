package com.example.teleheal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Edit_Profile extends AppCompatActivity {
    String userUsername = HelperClass.stringToPass;
    Button back,update;
    EditText eFname,eEmail,ePassword,eAge;
    Spinner eBG;
    Spinner spinner;
    String[] blood_group={"A+","A-","B+","B-","AB+","AB-","O+","O-"};
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_edit_profile);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        eFname=findViewById(R.id.editFname);
        eEmail=findViewById(R.id.editEmail);
        ePassword=findViewById(R.id.editPass);
        eAge=findViewById(R.id.editAge);
        eBG=findViewById(R.id.bloodgroup);
        update=findViewById(R.id.update);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Fname= eFname.getText().toString();
                String Email= eEmail.getText().toString();
                String Pass= ePassword.getText().toString();
                String Age= eAge.getText().toString();
                String BG= eBG.getSelectedItem().toString();

                updateData(userUsername,Fname,Email,Pass,Age,BG);
            }
        });

        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Edit_Profile.this,Profile.class);
                startActivity(intent);
            }
        });

        spinner=findViewById(R.id.bloodgroup);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Edit_Profile.this, android.R.layout.simple_spinner_item,blood_group);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(Edit_Profile.this,value,Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    private void updateData(String userUsername, String fname, String email, String pass, String age,String bg) {
        HashMap user= new HashMap();
        if(!fname.isEmpty()){
            user.put("fullname",fname);
        }
        if(!email.isEmpty()){
            user.put("email",email);
        }
        if(!pass.isEmpty()){
            user.put("password",pass);
        }
        if(!age.isEmpty()){
            user.put("age",age);
        }
        if(!bg.isEmpty()){
            user.put("blood group",bg);
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        databaseReference.child(userUsername).updateChildren(user).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Edit_Profile.this,"Updated Successfully",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Edit_Profile.this,"Failed To update",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}