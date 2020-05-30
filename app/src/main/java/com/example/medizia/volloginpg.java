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

public class volloginpg extends AppCompatActivity {
    EditText evolname,evolpass;
    String username,passwrd;
    Button blog;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volloginpg);

        evolname=(EditText)findViewById(R.id.usrname);
        evolpass=(EditText)findViewById(R.id.password);
        blog=(Button) findViewById(R.id.log);
        reference= FirebaseDatabase.getInstance().getReference().child("Volunteerlist");

        blog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                username=evolname.getText().toString().trim();
                passwrd=evolpass.getText().toString().trim();

                if(username.isEmpty())
                {
                    evolname.setError("enter name");
                    evolname.requestFocus();
                }
                else if (passwrd.isEmpty())
                {
                    evolpass.setError("enter Password");
                    evolpass.requestFocus();
                }
                else
                {

                    Query query=reference.orderByChild("name").equalTo(username);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                        {
                            if (dataSnapshot.exists())
                            {
                                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                                {


                                        Toast.makeText(getApplicationContext(),"login successfull",Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(getApplicationContext(),aftervollogin.class);
                                        startActivity(intent);



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
