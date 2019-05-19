package com.example.frankline.myclinic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_login);
    }
    public void loginMethod(View v){
        username = (EditText) findViewById(R.id.userEdit);
        password = (EditText) findViewById(R.id.passEdit);

        if (username.getText().length()!= 0  && password.getText().length()!=0){
            Toast.makeText(getApplicationContext(),"Welcome to E-Matatu",Toast.LENGTH_SHORT).show();
            Intent main = new Intent(Login.this,MainActivity.class);
            startActivity(main);

        }else {
            Toast.makeText(getApplicationContext(),"Invalid Input",Toast.LENGTH_SHORT).show();
        }

    }
    public void register(View v){
        Intent main = new Intent(Login.this,RegisterActivity.class);
        startActivity(main);
    }
}
