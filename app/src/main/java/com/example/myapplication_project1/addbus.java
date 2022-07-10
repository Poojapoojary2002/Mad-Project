package com.example.myapplication_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addbus extends AppCompatActivity {

    EditText textno;
    EditText textdriver;
    EditText textroute;
    EditText textphone;
    Button add_button;
    DatabaseReference bus_details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbus);
        textno=(EditText) findViewById(R.id.textno);
        textdriver=(EditText) findViewById(R.id.textdriver);
        textroute=(EditText) findViewById(R.id.textroute);
        textphone=(EditText) findViewById(R.id.dno);
         bus_details= FirebaseDatabase.getInstance().getReference().child("BusDetails");
         add_button=findViewById(R.id.add);
         add_button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 insertbusdetaildata();
             }

             private void insertbusdetaildata() {
                 String text_no = textno.getText().toString();
                 String text_driver = textdriver.getText().toString();
                 String text_route = textroute.getText().toString();
                 String text_phone = textphone.getText().toString();
                 BusDetails busdetails=new BusDetails(text_no,text_driver,text_route,text_phone);
                 bus_details.push().setValue(busdetails);
                 Toast.makeText(addbus.this,"Data Inserted",Toast.LENGTH_SHORT).show();


             }
         });


        //if (TextUtils.isEmpty(text_no)) {
          //  textno.setError("please enter bus number");
        //}
        //if (TextUtils.isEmpty(text_driver)) {
          //  textdriver.setError("please enter bus driver");
        //}
        //if (TextUtils.isEmpty(text_route)) {
          //  textroute.setError("please enter route");
        //}
        //else{
        //}

    }
}