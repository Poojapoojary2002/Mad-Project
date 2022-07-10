package com.example.myapplication_project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class stu_home extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    myAdapter myadapter;
    ArrayList<Bus> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stu_home);
        recyclerView=findViewById(R.id.stu_home);
        String busno=getIntent().getStringExtra("busno");
        database= FirebaseDatabase.getInstance().getReference("BusDetails");
        Query query=FirebaseDatabase.getInstance().getReference("BusDetails")
                        .orderByChild("textno")
                                .equalTo(busno);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        myadapter=new myAdapter(this,list);
        recyclerView.setAdapter(myadapter);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Bus bus=dataSnapshot.getValue(Bus.class);
                    list.add(bus);
                }
                myadapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    }
