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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class student_reg extends AppCompatActivity {
    EditText rname,rpws,cpws,email,bus,phone;
    Button rbtn,login;
    ProgressDialog progressDialog;
    DatabaseReference students_details;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_reg);
        rname=findViewById(R.id.sname);
        rpws=findViewById(R.id.spsw);
        cpws=findViewById(R.id.spsw);
        email=findViewById(R.id.semail);

        phone=findViewById(R.id.phone);
        rbtn=findViewById(R.id.sreg);
        login=findViewById(R.id.button5);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        students_details= FirebaseDatabase.getInstance().getReference().child("StudentsDetails");


        rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(student_reg.this,student_login.class);
                startActivity(intent);
            }
        });
        rbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perfoAuth();

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(student_reg.this,student_login.class);
                startActivity(intent);
            }
        });

    }

    private void perfoAuth() {
        String useremail=email.getText().toString();
        String password=rpws.getText().toString();
        String cpassword=cpws.getText().toString();
        if (!useremail.matches(emailPattern)) {
            email.setError("Enter correct Email");
        }
        else if(password.isEmpty()||password.length()<6)
        {
            rpws.setError("Enter Proper Password");
        }
        else if(!password.equals(cpassword))
        {
            cpws.setError("Password Not Matched");
        }
        else
        {
            progressDialog.setMessage("Plaese wait while Registration.....");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            mAuth.createUserWithEmailAndPassword(useremail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        sendUserToNextActivity();


                        Toast.makeText(student_reg.this,"Registration successfull",Toast.LENGTH_SHORT).show();
                    } else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(student_reg.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                    }
                }

                private void sendUserToNextActivity() {
                    Intent intent=new Intent(student_reg.this,student_login.class);

                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            });



        }
    }
}