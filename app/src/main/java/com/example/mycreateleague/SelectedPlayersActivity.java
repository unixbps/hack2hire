package com.example.mycreateleague;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SelectedPlayersActivity extends AppCompatActivity {

    private SharedPreferences prefs;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_players);
        getSupportActionBar().setTitle("MY Team");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.listView);
        listView.setCacheColorHint(Color.LTGRAY);

        prefs = getSharedPreferences("selected_players", MODE_PRIVATE);
        String data = prefs.getString("playerlist", null);
        List<String> tasksList = new ArrayList<String>(Collections.singleton(data));
        listView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, tasksList));

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}

