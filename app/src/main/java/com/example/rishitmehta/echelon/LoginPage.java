package com.example.rishitmehta.echelon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class LoginPage extends AppCompatActivity {


    private EditText Name;
    private EditText Password;
    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);


        ImageView myImageView = (ImageView) findViewById(R.id.ImageLogo);
        myImageView.setImageResource(R.drawable.icon);


        Name = (EditText)findViewById(R.id.etUsername);
        Password = (EditText)findViewById(R.id.etPassword);
        Login = (Button)findViewById(R.id.btLogin);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString(),Password.getText().toString());
            }
        });

    }

    private void validate(String UserName, String UserPassword ){

        if(UserName.equals("Rishit") && UserPassword.equals("1234")){
            Intent intent = new Intent(LoginPage.this, HomePage.class);
            startActivity(intent);
        }
    }
}
