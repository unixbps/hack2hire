package com.example.mycreateleague;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mycreateleague.Models.DataModel;
import com.example.mycreateleague.Models.MainPageAdepter;
import com.example.mycreateleague.Models.Post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlayersListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AppCompatButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_list);
        getSupportActionBar().setTitle("ALL Players");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button = findViewById(R.id.Submit);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext() ,SelectedPlayersActivity.class);
                startActivity(i);
            }
        });

        mydata();

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }


    public void mydata(){

        final ProgressDialog progressDialog = new ProgressDialog(PlayersListActivity.this);
        progressDialog.setMessage(" Loading...");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Post.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Post service = retrofit.create(Post.class);

        Call<List<DataModel>> call = service.getJSON();

        call.enqueue(new Callback<List<DataModel>>() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {
                Log.d("TAG", "onResult: NullPointerException: " +response );
                progressDialog.dismiss();

                if (response.code() == 200) {

                    List<DataModel> list =response.body();
                    recyclerView.setAdapter (new MainPageAdepter(PlayersListActivity.this, (List<DataModel>) list));


                }else {

                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), " Something went wrong ", Toast.LENGTH_SHORT).show();
                }

                if (response.code() == 404){
                    // error case
                    switch (response.code ( )) {
                        case 404:

                            progressDialog.dismiss();
                            Toast.makeText (getApplicationContext(), "server not found", Toast.LENGTH_SHORT).show ( );
                            break;

                    }
                }

                if (response.code() == 500){
                    // error case
                    switch (response.code ( )) {
                        case 500:

                            progressDialog.dismiss();
                            Toast.makeText (getApplicationContext(), "server broken", Toast.LENGTH_SHORT).show ( );
                            break;
                    }
                }
            }
            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {

                progressDialog.dismiss();
                Log.d("TAG", "onFailure" +t );
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Something went wrong please try again", Toast.LENGTH_LONG).show();
            }
        });
    }

}
