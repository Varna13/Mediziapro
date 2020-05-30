package com.example.medizia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.IInterface;
import android.view.View;
import android.widget.Button;

public class afterlogin extends AppCompatActivity {
    Button border,bcomplaint,bstatus,bhome,bmyorder,bprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterlogin);

        border=(Button)findViewById(R.id.buy);
        bcomplaint=(Button)findViewById(R.id.complaint);
        bstatus=(Button)findViewById(R.id.status);
        bhome=(Button)findViewById(R.id.home);
        bmyorder=(Button)findViewById(R.id.myorder);
        bprofile=(Button)findViewById(R.id.myprofile);
        border.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),medicinedetails.class);
                startActivity(i);
            }
        });
        bcomplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),complaintbox.class);
                startActivity(intent);

            }
        });
        bhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),startingpage.class);
                startActivity(i);
            }
        });
        bmyorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getApplicationContext(),myorder.class);
                startActivity(intent);

            }
        });
        bprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),myprofile.class);
                startActivity(i);
            }
        });

    }
}
