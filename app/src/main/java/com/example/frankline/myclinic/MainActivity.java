package com.example.frankline.myclinic;


import android.content.Intent;
import android.net.Uri;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }

    public void openCal(View v){
        Intent book = new Intent(this,BookActivity.class);
        startActivity(book);
    }


    public void makeCall(View v){
        String phone = "+254717353774";
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        startActivity(intent);

    }
    public void MyAccount(View v){
        Intent myAccount = new Intent(this,MyAccount.class);
        startActivity(myAccount);
    }


    public void Health(View view){
        Intent healthTips = new Intent(this,HealthTips.class);
        startActivity(healthTips);
    }
}
