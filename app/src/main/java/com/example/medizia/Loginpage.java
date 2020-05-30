package com.example.medizia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Loginpage extends AppCompatActivity {
    EditText ed1,ed2;
    Button b1;
    String unamee,upswd;
    DatabaseReference reference;
    Patient patient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);
        ed1=(EditText)findViewById(R.id.uname);
        ed2=(EditText)findViewById(R.id.pswd);
        b1=(Button)findViewById(R.id.login);

        patient=new Patient();
        reference= FirebaseDatabase.getInstance().getReference().child("Register");

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                unamee=ed1.getText().toString().trim();
                upswd=ed2.getText().toString().trim();

                if(unamee.isEmpty())
                {
                    ed1.setError("enter name");
                    ed1.requestFocus();
                }
                else if (upswd.isEmpty())
                {
                    ed2.setError("enter Password");
                    ed2.requestFocus();
                }
                else
                {

                    Query query=reference.orderByChild("name").equalTo(unamee);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                        {
                            if (dataSnapshot.exists())
                            {
                                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                                {
                                    patient=snapshot.getValue(Patient.class);
                                    String pass=patient.password;
                                    if(pass.equals(upswd))
                                    {
                                        Toast.makeText(getApplicationContext(),"login successfull",Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(getApplicationContext(),afterlogin.class);
                                        startActivity(intent);

                                    }

                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }


            }
        });


    }
}
