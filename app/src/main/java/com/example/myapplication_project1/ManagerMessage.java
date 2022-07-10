package com.example.myapplication_project1;

import static com.example.myapplication_project1.Constants.TOPIC;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication_project1.api.ApiUtilities;
import com.example.myapplication_project1.model.NotificationData;
import com.example.myapplication_project1.model.PushNotification;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagerMessage extends AppCompatActivity {
    EditText man_busno,msg;
    Button send;
    DatabaseReference dbstuddetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_message);
        man_busno=(EditText) findViewById(R.id.no);
        msg=(EditText) findViewById(R.id.upda);
        send=(Button) findViewById(R.id.button4);

        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC);

        send.setOnClickListener(v ->{
            String busno=man_busno.getText().toString();
            String message= msg.getText().toString();

            if(!busno.isEmpty() && !message.isEmpty()){
                PushNotification notification = new PushNotification(new NotificationData(busno,message),TOPIC);
                sendNotification(notification);
            }
        });
    }

    private void sendNotification(PushNotification notification) {
        ApiUtilities.getClient().sendNotification(notification).enqueue(new Callback<PushNotification>() {
            @Override
            public void onResponse(Call<PushNotification> call, Response<PushNotification> response) {
                if(response.isSuccessful())
                    Toast.makeText(ManagerMessage.this,"success",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(ManagerMessage.this,"error",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PushNotification> call, Throwable t) {
                Toast.makeText(ManagerMessage.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}