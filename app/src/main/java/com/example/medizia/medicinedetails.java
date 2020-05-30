package com.example.medizia;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class medicinedetails extends AppCompatActivity {
    EditText ed1, ed2,ed3;
    Button bupload, badd, bcancel, bsubmit;
    ImageView image;
    String prescribtion, no_of_medicine, dldate;
    TableLayout t1;
    int i;
    DatabaseReference reference;
    Ordering ordering;
    TextView tabname, tabquant;
    String []medname,mednum;
    int n;
    Spinner s;
    FirebaseAuth fAuth;

    private Uri imageuri;
    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicinedetails);
        ed1 = (EditText) findViewById(R.id.medicinenumber);
        ed2 = (EditText) findViewById(R.id.deliverydate);
        badd = (Button) findViewById(R.id.add);
        bupload = (Button) findViewById(R.id.ulid);
        bcancel = (Button) findViewById(R.id.cancel);
        bsubmit = (Button) findViewById(R.id.submit);
        image = (ImageView) findViewById(R.id.imageeee);

        t1 = (TableLayout) findViewById(R.id.tablet);
        tabname = (TextView) findViewById(R.id.tabname);
        tabquant = (TextView) findViewById(R.id.quan);

        ordering = new Ordering();
        reference = FirebaseDatabase.getInstance().getReference().child("Ordering");
        storageReference = FirebaseStorage.getInstance().getReference();
        fAuth=FirebaseAuth.getInstance();


        badd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 0;
                int tabnum = Integer.parseInt(ed1.getText().toString().trim());
                tabname.setVisibility(View.VISIBLE);
                tabquant.setVisibility(View.VISIBLE);

                for (i = 0; i < tabnum; i++) {
                    TableRow trow = new TableRow(getApplicationContext());
                    ed2 = new EditText(getApplicationContext());
                    ed2.setText("               ");
                    ed2.setId(i);
                    ed3 = new EditText(getApplicationContext());
                    ed3.setText("               ");
                    ed3.setId(i + tabnum);
                    trow.addView(ed2);
                    trow.addView(ed3);
                    t1.addView(trow);

                }


            }
        });
        bupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGallery,1000);
            }
        });


        bsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no_of_medicine = ed1.getText().toString().trim();
                dldate = ed2.getText().toString().toString().trim();

                if (no_of_medicine.isEmpty()) {
                    ed1.setError("enter no of medicines");
                    ed1.requestFocus();
                } else if (dldate.isEmpty()) {
                    ed2.setError("enter date");
                    ed1.requestFocus();
                } else if (ed1 != null && ed2 != null)
                {
                    Toast.makeText(getApplicationContext(), "successfully ordered", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), Delivarypage.class);
                    startActivity(intent);
                    ed1.setText("");
                    ed2.setText("");
                    ed3.setText("");
                }
                else
                    {
                        medname =new String[n];
                        mednum =new String[n];
                        for(int j=0;j<n;j++) {

                            s = (Spinner) findViewById(i);
                            String s1 = s.getSelectedItem().toString().trim();
                            medname[i] = s1;
                            ed2 = (EditText) findViewById(i + tabnum);
                            String s2 = ed2.getText().toString().trim();
                            mednum[i] = s2;

                        }

                }
            }
        });
        bcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "the order is canceled", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000){
            if (resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();
                //prescribtion.setImageURI(imageUri);

                uploadImagetoFirebase(imageUri);
            }
        }
    }

    private void uploadImagetoFirebase(Uri imageUri) {

        final StorageReference fileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"pic.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(image);
                        Toast.makeText(medicinedetails.this,"Prescribtion uploaded",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(medicinedetails.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
