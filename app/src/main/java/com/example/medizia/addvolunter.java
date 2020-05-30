package com.example.medizia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addvolunter extends AppCompatActivity {
    EditText evname,evid,evphn,evpass;
    Button bsubmit;
    String vname,vid,vphn,vpswd;
    DatabaseReference reference;
    Volunteerlist vlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addvolunter);
        evname=(EditText)findViewById(R.id.volname);
        evid=(EditText)findViewById(R.id.volid);
        evphn=(EditText)findViewById(R.id.vphn);
        evpass=(EditText)findViewById(R.id.vpass);
        bsubmit=(Button) findViewById(R.id.vsubmit);

        vlist=new Volunteerlist();


        bsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vname=evname.getText().toString().trim();
                vid=evid.getText().toString().trim();
                vphn=evphn.getText().toString().trim();
                vpswd=evpass.getText().toString().trim();
                vlist.setVname(vname);
                vlist.setVid(vid);
                vlist.setVphn(vphn);
                vlist.setVpswd(vpswd);
                reference= FirebaseDatabase.getInstance().getReference().child("Volunteerlist").child(vid);
                reference.setValue(vlist);
                Toast.makeText(getApplicationContext(),"New volunteer added",Toast.LENGTH_SHORT).show();

                if (vname.isEmpty())
                {
                    evname.setError("Name is Required");
                    evname.requestFocus();
                }
                else if (vid.isEmpty())
                {
                    evid.setError("enter ID");
                    evid.requestFocus();
                }
                else if (vphn.isEmpty())
                {
                    evphn.setError("Enter mob num");
                    evphn.requestFocus();
                }
                else if (vpswd.isEmpty())
                {
                    evpass.setError("Enter password");
                    evpass.requestFocus();
                }
            }
        });
    }
}
