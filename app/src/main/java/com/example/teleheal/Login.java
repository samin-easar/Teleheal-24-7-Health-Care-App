package com.example.teleheal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    EditText username,password;
    TextView registerhere;
    Button loginbtn;
    ImageView googlesignin;
    FirebaseAuth auth;
    FirebaseDatabase db;
    GoogleSignInClient gsigninClient;
    ProgressDialog pdialog;
    TextView forgotpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        registerhere=findViewById(R.id.register);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        loginbtn=findViewById(R.id.loginbtn);
        googlesignin=findViewById(R.id.googlesignin);
        forgotpassword=findViewById(R.id.forgotpass);

        //Forgot Password
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               AlertDialog.Builder builder= new AlertDialog.Builder(Login.this);
               View dialogview = getLayoutInflater().inflate(R.layout.forgot_dialog,null);
               EditText emailBox = dialogview.findViewById(R.id.emailBox);

               builder.setView(dialogview);
               AlertDialog dialog=builder.create();
               dialogview.findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       String userEmail= emailBox.getText().toString();
                       if(TextUtils.isEmpty(userEmail) && !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                           Toast.makeText(Login.this,"Enter Your Registered Email",Toast.LENGTH_SHORT).show();
                           return;
                       }

                       auth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {
                               if(task.isSuccessful()){
                                   Toast.makeText(Login.this,"Check Your Email",Toast.LENGTH_SHORT).show();
                                   dialog.dismiss();
                               }
                               else{
                                   Toast.makeText(Login.this,"Unable to Send , Try Again",Toast.LENGTH_SHORT).show();
                               }
                           }
                       });
                   }
               });
               dialogview.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       dialog.dismiss();
                   }
               });
               if(dialog.getWindow()!=null){
                   dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
               }
               dialog.show();
            }
        });

        //Sign In  With Google
        auth=FirebaseAuth.getInstance();
        db=FirebaseDatabase.getInstance();
        pdialog= new ProgressDialog(Login.this);
        pdialog.setTitle("Creating Account");
        pdialog.setMessage("We are creating your account");

        GoogleSignInOptions gso= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        gsigninClient= GoogleSignIn.getClient(this,gso);


        googlesignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin();
            }
        });


        //Credentials are being checked here
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validUsername()  | !validPassword()){
                    Toast.makeText(Login.this,"Username or Password can't be empty.",Toast.LENGTH_LONG).show();

                }else{
                    checkUser();
                }

            }
        });

        registerhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,SignUp.class);
                startActivity(intent);
            }
        });
    }

    int RC_SIGN_IN=40;
    private void signin() {
        Intent intent= gsigninClient.getSignInIntent();
        startActivityForResult(intent,RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RC_SIGN_IN){

            Task<GoogleSignInAccount> task= GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account= task.getResult(ApiException.class);
                firebaseAuth(account.getIdToken());
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void firebaseAuth(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken,null);
        auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user= auth.getCurrentUser();
                    Users users= new Users();
                    users.setUserId(user.getUid());
                    users.setName(user.getDisplayName());
                    users.setProfile(user.getPhotoUrl().toString());

                    db.getReference().child("Users").child(user.getUid()).setValue(users);
                    Intent intent2= new Intent(Login.this,Home.class);
                    startActivity(intent2);
                }
                else{
                    Toast.makeText(Login.this,"Error , Cant Create Account",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Methods For Checking Login Credentials
    public Boolean validUsername(){
       String val=username.getText().toString();
       if(val.isEmpty()){
           username.setError("Username can't be empty");
           return false;
       }else{
           username.setError(null);
           return true;
       }
    }
    public Boolean validPassword(){
        String val=password.getText().toString();
        if(val.isEmpty()){
            password.setError("Password can't be empty");
            return false;
        }else{
            password.setError(null);
            return true;
        }
    }
    public void checkUser(){
        String userUsername=username.getText().toString();
        String passUsername=username.getText().toString();
        String userPassword=password.getText().toString();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase= reference.orderByChild("username").equalTo(userUsername);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    username.setError(null);
                    String passwordFromDB= snapshot.child(userUsername).child("password").getValue(String.class);
                    //Objects.equals(passwordFromDB,userPassword
                    if(passwordFromDB.equals(userPassword)){
                        username.setError(null);

                        Toast.makeText(Login.this,"Login Successfully",Toast.LENGTH_LONG).show();

                        //Pass a string to Profile
                        HelperClass.stringToPass  = passUsername;
                        startActivity(new Intent(Login.this, Profile.class));

                        Intent intent=new Intent(Login.this,Home.class);
                        startActivity(intent);
                        finish();
                    }else{
                        password.setError("Invalid Credentials");
                        password.requestFocus();
                    }
                }else{
                    username.setError("User does not exist");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}