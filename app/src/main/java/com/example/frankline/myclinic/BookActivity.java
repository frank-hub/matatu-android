package com.example.frankline.myclinic;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    int day,month,year;
    TextView date_set;
    EditText start_et;
    ProgressDialog progressDoalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        date_set = (TextView) findViewById(R.id.set_date);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        final EditText name_et = (EditText) findViewById(R.id.e_name);
        final EditText email_et = (EditText) findViewById(R.id.e_email);
        final EditText phone_et = (EditText) findViewById(R.id.e_phone);
         start_et = (EditText) findViewById(R.id.e_start);
        final EditText illness_et = (EditText) findViewById(R.id.e_illness);

        progressDoalog = new ProgressDialog(BookActivity.this);
        progressDoalog.setMessage("Loading....");

        Button submitBtn = (Button) findViewById(R.id.submit);
        /*Create handle for the RetrofitInstance interface*/
        final GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Bundle extras = getIntent().getExtras();

//        final String name = extras.getString("name");
//        final String email = extras.getString("email");
//        final String phone = extras.getString("phone");
//        final String illness = extras.getString("illness");
//        final String start = extras.getString("start");

        if (extras != null)
        {
            name_et.setText(" "+extras.getString("name"));
            email_et.setText(" "+extras.getString("email"));
            phone_et.setText(" "+extras.getString("phone"));
            illness_et.setText(" "+extras.getString("illness"));
            start_et.setText(" "+extras.getString("start"));
        }



        CardView openDateBtn = (CardView) findViewById(R.id.openDate);
                openDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (name_et.length() == 0 || email_et.length() == 0
                        || phone_et.length()==0 || illness_et.length()==0||
                        start_et.length()==0){
                    name_et.setError("Name Is required");
                    email_et.setError("Email Field is Required");
                    phone_et.setError("Phone Field is required");
                    illness_et.setError("This Field is Required");
                    start_et.setError("This Field is Required");
                }
                progressDoalog.show();
                Retro retro = new Retro();
                retro.setName(name_et.getText().toString());
                retro.setEmail(email_et.getText().toString().trim());
                retro.setPhone(phone_et.getText().toString());
                retro.setIllness(illness_et.getText().toString().trim());
                retro.setStart(start_et.getText().toString().trim());
                sendBooking(retro);

            }

            public void sendBooking(Retro retro) {
                Call<Retro> call = service.saveBooking(retro);
                call.enqueue(new Callback<Retro>() {
                    @Override
                    public void onResponse(Call<Retro> call, Response<Retro> response) {
                        if (response.isSuccessful()){

                            Toast.makeText(BookActivity.this,"Booking Saved Successfully",Toast.LENGTH_SHORT).show();
                            Log.e("Vibe", ""+response.body().toString());
                            progressDoalog.dismiss();
                            name_et.setText(null);
                            email_et.setText(null);
                            phone_et.setText(null);
                            illness_et.setText(null);
                        }
                    }
                    @Override
                    public void onFailure(Call<Retro> call, Throwable t) {
//                        Log.e("Unable to submit post to API.");
                        Toast.makeText(BookActivity.this,"Unable to submit Try again",Toast.LENGTH_SHORT).show();
                    }
                });
            }
            public void showResponse(String response) {

            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String currentDateString = DateFormat.getDateInstance().format(c.getTime());
        Toast.makeText(BookActivity.this,currentDateString,Toast.LENGTH_SHORT).show();
        start_et.setText(currentDateString);
    }
}
