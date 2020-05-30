package com.example.medizia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Signuppage extends AppCompatActivity
{
    Button bsignup,bgotologin;
    EditText ename,eaddress,eemail,ephoneNo,eplace,epassword,econfpassword;
    String name,adres,email,phone,place,password,mobno;
    DatabaseReference reference;
    Register register;
    long backpress;
    Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuppage);
        this.setTitle("Sign Up");


        bsignup=(Button)findViewById(R.id.signup);
        bgotologin=(Button)findViewById(R.id.gotologin);
        ephoneNo=(EditText)findViewById(R.id.phn);
        epassword=(EditText)findViewById(R.id.pswd);
        ename=(EditText)findViewById(R.id.name);
        eaddress=(EditText)findViewById(R.id.adrs);
        eemail=(EditText)findViewById(R.id.email);
        eplace=(EditText)findViewById(R.id.plc);
        econfpassword=(EditText) findViewById(R.id.confpass);

        register=new Register();
        reference= FirebaseDatabase.getInstance().getReference().child("Register");


        bsignup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                phone=ephoneNo.getText().toString();
                password=epassword.getText().toString();
                name=ename.getText().toString().trim();
                adres=eaddress.getText().toString().trim();
                email=eemail.getText().toString().trim();
                place=eplace.getText().toString().trim();


                if (name.isEmpty())
                {
                    ename.setError("Name is required");
                    ename.requestFocus();
                }
                else if (phone.isEmpty())
                {
                    ephoneNo.setError("Mobile No. is required");
                    ephoneNo.requestFocus();
                }
                else if (phone.length()<13)
                {
                    ephoneNo.setError("Mobile No. must have 10 numbers");
                    ephoneNo.requestFocus();
                }
                else if (password.isEmpty())
                {
                    epassword.setError("Password is required");
                    epassword.requestFocus();
                }
                else if (adres.isEmpty())
                {
                    eaddress.setError("address Required");
                    eaddress.requestFocus();
                }
                else if (email.isEmpty())
                {
                    eemail.setError("email required");
                    eemail.requestFocus();
                }
                else if (place.isEmpty())
                {
                    eplace.setError("place required");
                    eplace.requestFocus();
                }
                else
                {
                    String code="91";
                    mobno="+"  + code + phone;

                    Query query=reference.orderByChild("mobileNum").equalTo(mobno);
                    query.addListenerForSingleValueEvent(new ValueEventListener()
                    {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                        {
                            if (dataSnapshot.exists())
                            {
                                ephoneNo.setError("Patient already exist..! Try Login");
                                ephoneNo.requestFocus();
                            }
                            else
                            {
                                Intent intent=new Intent(getApplicationContext(),otppage.class);
                                intent.putExtra("signMobile",phone);
                                intent.putExtra("signPassword",password);
                                intent.putExtra("signName",name);
                                intent.putExtra("signAddress",adres);
                                intent.putExtra("signEmail",email);
                                intent.putExtra("signPlace",place);
                                startActivity(intent);


                                ename.setText("");
                                epassword.setText("");
                                ephoneNo.setText("");
                                eaddress.setText("");
                                eemail.setText("");
                                eplace.setText("");

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError)
                        {

                        }
                    });

                }
            }
        });
        bgotologin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getApplicationContext(),Loginpage.class);
                startActivity(intent);
            }
        });
    }
    public void onBackPressed()
    {
        if (backpress+2000>System.currentTimeMillis())
        {
            backToast.cancel();
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
        else
        {
            backToast= Toast.makeText(getApplicationContext(), "Press again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backpress=System.currentTimeMillis();
    }
}
