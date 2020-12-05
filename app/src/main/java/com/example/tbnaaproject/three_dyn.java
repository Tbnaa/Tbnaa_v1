package com.example.tbnaaproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class three_dyn extends AppCompatActivity {
    Button f1,f2,f3;
    Fragment frag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_dyn);
        f1=findViewById(R.id.Fragment1);
        f2=findViewById(R.id.Fragment2);
        f3=findViewById(R.id.Fragment3);

        f1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag=new Fragment_cat_info();
                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.DefaultFragment,frag);
                ft.addToBackStack(null);
                ft.commit();

            }
        });
        f2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag=new Fragment_user_info();
                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.DefaultFragment,frag);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        f3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frag=new FragmentDy();
                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.DefaultFragment,frag);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }
}




