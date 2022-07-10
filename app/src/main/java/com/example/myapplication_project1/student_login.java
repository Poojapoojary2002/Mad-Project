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

public class student_login extends AppCompatActivity {
    EditText slname, slpws, slemail,sbus;
    Button slbtn;
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        slname = findViewById(R.id.name1);
        slpws = findViewById(R.id.psw1);
     sbus=findViewById(R.id.busno);
        slemail = findViewById(R.id.semail2);
        slbtn = findViewById(R.id.sl);

        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        slbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perfoAuth();
            }
        });
    }
            private void perfoAuth() {
                String useremail2 = slemail.getText().toString();
                String password2 = slpws.getText().toString();

                if (!useremail2.matches(emailPattern)) {
                    slemail.setError("Enter correct Email");
                } else if (password2.isEmpty() || password2.length() < 6) {
                    slpws.setError("Enter Proper Password");
                } else {
                    progressDialog.setMessage("Plaese wait while login.....");
                    progressDialog.setTitle("login");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    mAuth.signInWithEmailAndPassword(useremail2, password2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                sendUserToNextActivity();

                                Toast.makeText(student_login.this, "Login successfull", Toast.LENGTH_SHORT).show();
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(student_login.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            }
                private void sendUserToNextActivity() {
                    Intent intent=new Intent(student_login.this,stu_home.class);
                    String bus = sbus.getText().toString();
                    intent.putExtra("busno",bus);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }


}