package com.example.mycreateleague.Models;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mycreateleague.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

public class MainPageAdepter extends RecyclerView.Adapter<MainPageAdepter.ViewHolder> {

    private List<DataModel> myData;
    private Activity activity;
    private  ArrayList<String> selectedValues = new ArrayList<>();

    public MainPageAdepter(Activity activity, List<DataModel> myData) {
        this.myData = myData;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item22, viewGroup, false);
        return new MainPageAdepter.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final MainPageAdepter.ViewHolder viewHolder, final int i) {

        viewHolder.tittle.setText(myData.get(i).getName());
        viewHolder.content.setText((myData.get(i).getRealname()));
        viewHolder.version.setText((myData.get(i).getFirstappearance()));

        viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (viewHolder.checkBox.isChecked())
                {
                    String data = myData.get(i).getName();
                    selectedValues.add(data);
                     String s = new String(String.valueOf(selectedValues));

                    Gson gson = new Gson();
                    String json = gson.toJson(selectedValues);

                    SharedPreferences.Editor editor = activity.getSharedPreferences("selected_players", MODE_PRIVATE).edit();
                   // editor.putString("playerlist", String.valueOf(json);
                    editor.putString("playerlist", new String(s));
                    editor.apply();

                    Toast.makeText(activity, ""+json, Toast.LENGTH_SHORT).show();


                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return myData.size();
    }

    public DataModel get(int position) {
        return myData.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tittle;
        public TextView content;
        public TextView version;
        public CheckBox checkBox;

        public ViewHolder(View view) {
            super(view);

            checkBox = (CheckBox) view.findViewById(R.id.checkbox);
            tittle = (TextView) view.findViewById(R.id.tv_name);
            content = (TextView) view.findViewById(R.id.tv_version);
            version = (TextView) view.findViewById(R.id.tv_api_level);

        }
    }

}

