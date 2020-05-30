package com.example.medizia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class complaintbox extends AppCompatActivity {
    EditText edate,esub,ecomp;
    Button bsubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaintbox);

        edate=(EditText)findViewById(R.id.date);
        esub=(EditText)findViewById(R.id.subject);
        ecomp=(EditText)findViewById(R.id.complaint1);
        bsubmit=(Button)findViewById(R.id.submit);
    }
}
