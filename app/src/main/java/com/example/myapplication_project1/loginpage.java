package com.example.myapplication_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class loginpage extends AppCompatActivity {
    Button mangr,stdnt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mangr=findViewById(R.id.bt1);
        stdnt=findViewById(R.id.bt2);



        mangr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(loginpage.this, admin_login.class);
                startActivity(intent);
            }
        });
        stdnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(loginpage.this, student_reg.class);
                startActivity(intent);
            }
        });
    }
}