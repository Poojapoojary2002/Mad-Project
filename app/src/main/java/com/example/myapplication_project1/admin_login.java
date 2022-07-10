package com.example.myapplication_project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class admin_login extends AppCompatActivity {
    EditText lname, lpws;
    Button lbtn;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        lname = findViewById(R.id.name);

        lpws=findViewById(R.id.psw);

        lbtn = findViewById(R.id.al);
        progressDialog = new ProgressDialog(this);

        lbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String un=lname.getText().toString();
                String pa=lpws.getText().toString();
                if(!un.equals("admin") ) {
                    Toast.makeText(admin_login.this,"Wrong Username!",Toast.LENGTH_LONG).show();
                    return;
                }else if(!pa.equals("admin")){
                    Toast.makeText(admin_login.this,"Wrong password!",Toast.LENGTH_LONG).show();
                    return;
                }
                else{
                    Toast.makeText(admin_login.this,"Welcome Admin!",Toast.LENGTH_LONG).show();

                    Intent i=new Intent(admin_login.this,home.class);
                    startActivity(i);
                }
            }
        });
    }
}

