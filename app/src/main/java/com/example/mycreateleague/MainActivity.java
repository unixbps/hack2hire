package com.example.mycreateleague;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {


    private ViewFlipper mViewFlipper;
    private Button button1 ,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("         HOME");

        button1 =findViewById(R.id.criket);
        button2 = findViewById(R.id.profile);

        mViewFlipper = (ViewFlipper) this.findViewById(R.id.ViewFlipper);
        mViewFlipper.setFlipInterval(3000);
        mViewFlipper.startFlipping();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext() ,PlayersListActivity.class);
                startActivity(i);


            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext() ,ProfileActivity.class);
                startActivity(i);

            }
        });


    }

}
