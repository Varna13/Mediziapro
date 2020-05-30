package com.example.medizia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Adminlogin extends AppCompatActivity {
    EditText eaname,eapass;
    String adname,adpass;
    Button b;
    String  auname,apswd;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        eaname=(EditText)findViewById(R.id.aduname);
        eapass=(EditText)findViewById(R.id.adpass);
        b=(Button)findViewById(R.id.adlogin);
        reference= FirebaseDatabase.getInstance().getReference().child(Admindetail);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adname=eaname.getText().toString().trim();
                adpass=eapass.getText().toString().trim();

                if(adname.isEmpty())
                {
                    eaname.setError("enter name");
                    eaname.requestFocus();
                }
                else if (adpass.isEmpty())
                {
                    eapass.setError("enter Password");
                    eapass.requestFocus();
                }
                Intent intent = new Intent(getApplicationContext(),afteradminlogin.class);
                startActivity(intent);
            }
        });
    }
}
