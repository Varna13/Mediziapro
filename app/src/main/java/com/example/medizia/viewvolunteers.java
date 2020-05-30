package com.example.medizia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class viewvolunteers extends AppCompatActivity {
    DatabaseReference reference;
    RecyclerView recyclerView;
    Adaptervolunteer adaptervolunteer;
    Volunteerlist volunteerlist;
    ArrayList<Volunteerlist> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewvolunteers);

        recyclerView=(RecyclerView)findViewById(R.id.recyclervol);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList=new ArrayList();
        volunteerlist=new Volunteerlist();
        reference= FirebaseDatabase.getInstance().getReference().child("Volunteerlist");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                if(dataSnapshot.exists())
                {
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                    {
                        Volunteerlist volunteerlist=dataSnapshot1.getValue(Volunteerlist.class);
                        arrayList.add(volunteerlist);

                    }
                    adaptervolunteer = new Adaptervolunteer(viewvolunteers.this, arrayList);
                    recyclerView.setAdapter(adaptervolunteer);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "something wnt wrong", Toast.LENGTH_LONG).show();

            }
        });
    }
}
