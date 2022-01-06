package com.example.listmahasiswa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    String user = "admin";
    String pass = "admin";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);

        Button login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equalsIgnoreCase(String.valueOf(user))&&password.getText().toString().
                        equalsIgnoreCase(String.valueOf(pass))){
                    startActivity(new Intent(Login.this,Dashboard.class));
                }else if(username.getText().toString().equalsIgnoreCase("") && password.getText().toString().
                        equalsIgnoreCase("")){
                    Toast.makeText(Login.this,"Harap Username dan Password diisi!!",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Login.this,"Username dan Password Salah!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}