package com.example.frankline.myclinic;

import android.app.ProgressDialog;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login extends AppCompatActivity {
    EditText username,password;
    private ProgressDialog progressDialog;
    private TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_login);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        username = (EditText) findViewById(R.id.userEdit);
        password = (EditText) findViewById(R.id.passEdit);
        tvMessage = findViewById(R.id.tvMessage);

    }
    public void loginMethod(View v){

        attemptLogin();

    }
    public void register(View v){
        Intent main = new Intent(Login.this,RegisterActivity.class);
        startActivity(main);
    }

    private void attemptLogin() {
        String email = username.getText().toString();
        String pass = password.getText().toString();
        if(email.isEmpty() || pass.isEmpty()){
            showMessage("Empty credentials are not allowed");
            return;
        }
        tvMessage.setVisibility(View.GONE);
        progressDialog.setMessage("Authenticating user ...");
        progressDialog.show();
        Retrofit retrofit =  RetrofitClientInstance.getRetrofitInstance();
        retrofit.create(GetDataService.class)
                .getLoginToken(email,pass)
                .enqueue(new Callback<UserToken>() {
                    @Override
                    public void onResponse(Call<UserToken> call, Response<UserToken> response) {
                        progressDialog.dismiss();
                        if(response.isSuccessful()){
                            showToken(response.body());
                        }else{
                            tvMessage.setText("Sorry, invalid passenger username or password.");
                            tvMessage.setVisibility(View.VISIBLE);
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
