package com.example.medizia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.medizia.R;
import com.example.medizia.Register;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class otppage extends AppCompatActivity
{
    String verificationcode,mobno,password,name,adrs,email,place;
    ProgressBar progressBar;
    EditText editText;
    Button button;
    FirebaseAuth mAuth;
    DatabaseReference reference;
    Register register;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otppage);
        this.setTitle("OTP Verification");


        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        editText=(EditText)findViewById(R.id.signupOTP);
        button=(Button)findViewById(R.id.signupOTP_verificationButton);

        mAuth=FirebaseAuth.getInstance();
        register=new Register();

        reference= FirebaseDatabase.getInstance().getReference().child("Register");

        Intent intent=getIntent();
        mobno=intent.getStringExtra("signMobile");
        password=intent.getStringExtra("signPassword");
        name=intent.getStringExtra("signName");
        adrs=intent.getStringExtra("signAddress");
        email=intent.getStringExtra("signEmail");
        place=intent.getStringExtra("signPlace");


        Toast.makeText(this,mobno,Toast.LENGTH_LONG).show();


        sendVerificationCode(mobno);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String code=editText.getText().toString().trim();
                if (code.isEmpty()||code.length()<6)
                {
                    editText.setError("Enter code...!");
                    editText.requestFocus();
                    return;
                }
                verifyCode(code);

            }
        });

    }
    public void verifyCode(String code)
    {
        editText.setText(code);
        PhoneAuthCredential credential= PhoneAuthProvider.getCredential(verificationcode, code);
        signInWithCredential(credential);
    }
    void signInWithCredential(PhoneAuthCredential credential)
    {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {


                    register.setName(name);
                    register.setPhone(mobno);
                    register.setPassword(password);
                    register.setAddress(adrs);
                    register.setEmail(email);
                    register.setPlace(place);

                    reference.push().setValue(register).addOnSuccessListener(new OnSuccessListener<Void>()
                    {
                        @Override
                        public void onSuccess(Void aVoid)
                        {
                            Toast.makeText(getApplicationContext(), "Sucessfully Registered", Toast.LENGTH_SHORT).show();
                        }
                    });

                    Intent intent = new Intent(getApplicationContext(), afterlogin.class);
                    intent.putExtra("mob1",mobno);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

            }
        });
    }

    public void sendVerificationCode(String num)
    {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(num,30, TimeUnit.SECONDS,this,mCallBack);

    }
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential)
        {
            String code=phoneAuthCredential.getSmsCode();
            if (code!=null)
            {
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationcode=s;
        }

        @Override
        public void onVerificationFailed(FirebaseException e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }
    };

}
