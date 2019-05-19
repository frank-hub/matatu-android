package com.example.frankline.myclinic;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class my_appointments extends AppCompatActivity {
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    ProgressDialog progressDoalog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointments);

        generateDataList(new ArrayList<Retro>());

        progressDoalog = new ProgressDialog(my_appointments.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<Retro>> call = service.getAllTests();
        call.enqueue(new Callback<List<Retro>>() {
            @Override
            public void onResponse(Call<List<Retro>> call, Response<List<Retro>> response) {
                progressDoalog.dismiss();
                Log.e("Vibe", ""+response.toString());
                setData(response.body());
            }

            @Override
            public void onFailure(Call<List<Retro>> call, Throwable t) {
                progressDoalog.dismiss();
                Log.e("LostVibe", t.getLocalizedMessage());
                Toast.makeText(my_appointments.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void setData(List<Retro> body) {
        adapter.updateView(body);
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Retro> photoList) {
        recyclerView = findViewById(R.id.custom_recycler);
        adapter = new CustomAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(my_appointments.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void delete(View v){
        // Build an AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(my_appointments.this);

        // Set a title for alert dialog
        builder.setTitle("Are You Sure You Want To Delete");

        // Ask the final question
        builder.setMessage("The booking will be deleted");

        // Set the alert dialog yes button click listener
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when user clicked the Yes button
                GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
                Call<Retro> call = service.deleteBooking(87);
                call.enqueue(new Callback<Retro>() {
                    @Override
                    public void onResponse(Call<Retro> call, Response<Retro> response) {
                        Log.i("Tag",""+response.toString());
                        Toast.makeText(getApplicationContext(),
                                "Booking Deleted",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Retro> call, Throwable t) {

                    }
                });


            }
        });

        // Set the alert dialog no button click listener
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do something when No button clicked

            }
        });

        AlertDialog dialog = builder.create();
        // Display the alert dialog on interface
        dialog.show();
    }

}
