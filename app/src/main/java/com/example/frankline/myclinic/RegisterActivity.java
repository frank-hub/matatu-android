package com.example.frankline.myclinic;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {

    private EditText userName;
    private EditText email;
    private EditText phone;
    private EditText password;
    private EditText confirmPassword;
    private Button btnRegister;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userName = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.c_password);
        btnRegister = findViewById(R.id.button);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String username = this.userName.getText().toString();
        String email = this.email.getText().toString();
        String confirm_password = this.confirmPassword.getText().toString();
        String phone = this.phone.getText().toString();
        String password = this.password.getText().toString();

        if(username.isEmpty() || password.isEmpty() || email.isEmpty() || confirm_password.isEmpty() ||
            phone.isEmpty()){
            showMessage("Empty credentials are not allowed");
            return;
        }

        if(!password.equals(confirm_password)){
            showMessage("Passwords do not match.");
            return;
        }

        progressDialog.setMessage("Registering user ...");
        progressDialog.show();
        Retrofit retrofit =  RetrofitClientInstance.getRetrofitInstance();
        retrofit.create(GetDataService.class)
                .registerUser(email,username,phone,password)
                .enqueue(new Callback<UserToken>() {
                    @Override
                    public void onResponse(Call<UserToken> call, Response<UserToken> response) {
                        progressDialog.dismiss();
                        if(response.isSuccessful()){
                            showToken(response.body());
                        }else{
                            showMessage("User could not be registered...try again.");
                        }
                    }

                    @Override
                    public void onFailure(Call<UserToken> call, Throwable t) {
                        showMessage(t.getMessage());
                        progressDialog.dismiss();
                    }
                });
    }

    private void showToken(UserToken body) {
        Cache.setAuthToken(this,body.token.authToken);
        if(Cache.hasAuthToken(this)){
            Cache.setAuthToken(this,body.token.authToken);
            Intent i = new Intent(this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|
                    Intent.FLAG_ACTIVITY_CLEAR_TASK |
                    Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }else{
            showMessage("Login failed");
        }
    }

    private void showMessage(String authToken) {
        Toast.makeText(this,""+authToken,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        showMessage("Enter your login details to enjoy cheap rides.");
    }

}
