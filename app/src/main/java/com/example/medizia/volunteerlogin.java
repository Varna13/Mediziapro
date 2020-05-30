package com.example.medizia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class volunteerlogin extends AppCompatActivity {
    Button blogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteerlogin);
        blogin=(Button)findViewById(R.id.loginn);

        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),volloginpg.class);
                startActivity(intent);
            }
        });
    }
}
