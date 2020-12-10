package com.example.tbnaaproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class adding_request_2stat extends AppCompatActivity {
    Button button1,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_request_2stat);




        button1 = (Button) findViewById(R.id.Accept_2stat);
        button2 = (Button) findViewById(R.id.Reject_2stat);

        button1.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {

            AlertDialog.Builder builder = new AlertDialog.Builder(
                    adding_request_2stat.this);

            builder.setMessage("adoption request accepted");
            builder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {  dialog.cancel();
                        }
                    });

            builder.show();
        }
        });
        button2.setOnClickListener(new View.OnClickListener() { @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    adding_request_2stat.this);

            builder.setMessage("adoption request rejected");
            builder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {  dialog.cancel();
                        }
                    });

            builder.show();
        }
        });
    }
}




