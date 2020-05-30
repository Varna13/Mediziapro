package com.example.medizia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class startingpage extends AppCompatActivity {
    Button buser,badmin,bvolunter,bcontact,babout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startingpage);

        buser=(Button)findViewById(R.id.user);
        badmin=(Button)findViewById(R.id.admin);
        bvolunter=(Button)findViewById(R.id.volunteer);
        bcontact=(Button)findViewById(R.id.contact);
        babout=(Button)findViewById(R.id.about);

        buser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });
        badmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),Adminlogin.class);
                startActivity(i);
            }
        });
        bvolunter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getApplicationContext(),volunteerlogin.class);
                startActivity(intent);

            }
        });
        bcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),contactpg.class);
                startActivity(intent);
            }
        });
        babout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),aboutpg.class);
                startActivity(i);
            }
        });
    }
}
