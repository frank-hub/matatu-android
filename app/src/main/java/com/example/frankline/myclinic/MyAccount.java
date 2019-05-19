package com.example.frankline.myclinic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MyAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
    }
    public void appoitment(View v){
        Intent appoitment = new Intent(this,my_appointments.class);
        startActivity(appoitment);

    }
}
