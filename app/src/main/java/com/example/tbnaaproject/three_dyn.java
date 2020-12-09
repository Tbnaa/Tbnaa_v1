package com.example.tbnaaproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class three_dyn extends AppCompatActivity {
    Button f1,f2,f3;
    Fragment frag;
    Button closeButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_dyn);
        f1=findViewById(R.id.Fragment1);
        f2=findViewById(R.id.Fragment2);
        f3=findViewById(R.id.Fragment3);
        closeButton=findViewById(R.id.closeButton);
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
        closeButton.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    three_dyn.this);

            builder.setMessage("Do you want to close this application ?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    finish();
                }
            })
            ;

            builder.setNegativeButton("NO",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {  dialog.cancel();
                        }
                    })

            ;

            builder.show();
        }
        });
    }
}




