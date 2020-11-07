package com.example.tbnaaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class AdoptionRequestsAdmin extends AppCompatActivity {
// TODO: Menu !!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption_requests_admin);

        // Create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Alex", "Sara", R.drawable.img_cat_alex));
        words.add(new Word("Lucy", "Maha", R.drawable.img_cat_lucy));
        words.add(new Word("Maya", "Noha", R.drawable.img_cat_maya));
        words.add(new Word("Nader", "Emmy", R.drawable.img_cat_nader));
        words.add(new Word("Shasha", "Nana", R.drawable.img_cat_sasha));
        words.add(new Word("Alex", "Farah", R.drawable.img_cat_alex));
        words.add(new Word("Lucy", "Hamad", R.drawable.img_cat_lucy));
        words.add(new Word("Maya", "Mohammed", R.drawable.img_cat_maya));
        words.add(new Word("Nader", "Amr", R.drawable.img_cat_nader));
        words.add(new Word("Shasha", "Omar", R.drawable.img_cat_sasha));

        WordAdapter adapter = new WordAdapter(this, words);
        ListView listView = (ListView) findViewById(R.id.activity_adoption_requests_adminList);
        listView.setAdapter(adapter);

        ListView listView2 = (ListView) findViewById(R.id.activity_adoption_requests_adminList2);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Word word = words.get(position);

                //TODO: Open more INFO
            }
        });
    }
}