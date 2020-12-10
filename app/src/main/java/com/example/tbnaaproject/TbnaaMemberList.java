package com.example.tbnaaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tbnaaproject.adapters.TbnaaMemberAdapter;

import java.util.ArrayList;

public class TbnaaMemberList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tbnaa_members);

        // Create a list of words
        final ArrayList<TbnaaMemberWords> words = new ArrayList<TbnaaMemberWords>();
        words.add(new TbnaaMemberWords("Kholoud Awaji", "+966-507-004-514","Kholoud@gmail.com", R.drawable.user));
        words.add(new TbnaaMemberWords("Noura Althobaity", "+966-502-455-001","noura@gmail.com", R.drawable.user));
        words.add(new TbnaaMemberWords("Amjad Alsaeed", "+966-507-000-356","amjad@gmail.com", R.drawable.user));
        words.add(new TbnaaMemberWords("Malak Alqahtani", "+966-507-807-409","malak@gmail.com", R.drawable.user));
        words.add(new TbnaaMemberWords("Fatimah Hazazi", "+966-553-364-006","fatimah@gmail.com", R.drawable.user));
        words.add(new TbnaaMemberWords("Fatimah Mujlid", "+966-500-304-106","fatimah1234@gmail.com", R.drawable.user));
        words.add(new TbnaaMemberWords("Maryam Alayat", "+966-500-304-106","maryam@gmail.com", R.drawable.user));
        words.add(new TbnaaMemberWords("Jumana Algamdi", "+966-500-304-106","Jumana@gmail.com", R.drawable.user));
        words.add(new TbnaaMemberWords("Bayan Algarni", "+966-500-304-106","bayn@gmail.com", R.drawable.user));
        words.add(new TbnaaMemberWords("Maha Alzahrani", "+966-500-304-106","Maha@gmail.com", R.drawable.user));
        words.add(new TbnaaMemberWords("Sahad Alotaibi", "+966-500-304-106","shahad@gmail.com", R.drawable.user));

        TbnaaMemberAdapter adapter = new TbnaaMemberAdapter(this, words);
        ListView listView = (ListView) findViewById(R.id.activity_tbnaa_member_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {



            }
        });
    }
}