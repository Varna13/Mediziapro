package com.example.medizia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class afteradminlogin extends AppCompatActivity {
    Button bvieworder,bviewvolunteer,baddvol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afteradminlogin);
       bvieworder=(Button)findViewById(R.id.vorder);
       bviewvolunteer=(Button)findViewById(R.id.vvolunter);
       baddvol=(Button)findViewById(R.id.addvolunter);

        bvieworder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getApplicationContext(),vieworder.class);
                startActivity(intent);


            }
        });
        bviewvolunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),viewvolunteers.class);
                startActivity(i);
            }
        });


        baddvol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getApplicationContext(),addvolunter.class);
                startActivity(intent);
            }
        });

    }
}
