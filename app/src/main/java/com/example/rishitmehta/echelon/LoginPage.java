package com.example.rishitmehta.echelon;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rishitmehta.echelon.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {


    private EditText Name;
    private EditText Password;
    private Button Login,signup;
    private String Tag = "LogIn";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);


        ImageView myImageView = (ImageView) findViewById(R.id.ImageLogo);
        myImageView.setImageResource(R.drawable.icon);


        Name = (EditText)findViewById(R.id.etUsername);
        Password = (EditText)findViewById(R.id.etPassword);
        Login = (Button)findViewById(R.id.btLogin);
        signup = (Button)findViewById(R.id.btSignUp);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = firebaseDatabase.getReference("User");

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog mDialog =new ProgressDialog(LoginPage.this);
                mDialog.setMessage("Authenticating....");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        mDialog.dismiss();

                        User user = dataSnapshot.child(Name.getText().toString()).getValue(User.class);
                        if(user.getPassword().equals(Password.getText().toString()))
                        {
                            Toast.makeText(LoginPage.this,"LogIN Sucess",Toast.LENGTH_SHORT).show();
                            Intent homeIntent = new Intent(LoginPage.this,HomePage.class);
                            startActivity(homeIntent);
                            finish();

                        }
                        else{
                            Toast.makeText(LoginPage.this,"LogIN Failed",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog =new ProgressDialog(LoginPage.this);
                mDialog.setMessage("Authenticating....");
                mDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        mDialog.dismiss();


                        if(dataSnapshot.child(Name.getText().toString()).exists())
                        {
                            Toast.makeText(LoginPage.this,"Already Registered",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            User user = new User(Password.getText().toString());
                            table_user.child(Name.getText().toString()).setValue(user);
                            Toast.makeText(LoginPage.this,"Signed Up",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });
            }
        });

    }

}
