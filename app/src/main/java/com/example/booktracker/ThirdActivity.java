package com.example.booktracker;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    EditText queryText;
    Button searchButton;

    EditText phoneNumberText;
    Button callButton;

    TimePicker alarmTime;
    Button alarmButton;

    Button returnButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intent_implementing);


        // Searching action
        queryText = findViewById(R.id.query);
        searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check that the query is not empty
                String query = queryText.getText().toString();
                if (!query.isEmpty()) {

                    // create new implicit intent
                    Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                    intent.putExtra(SearchManager.QUERY, query);
                    //if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    //}
                }
            }
        });

        // Calling action
        phoneNumberText = findViewById(R.id.phoneNumber);
        callButton = findViewById(R.id.callButton);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = phoneNumberText.getText().toString();
                // check that phone number is not empty
                if (!phoneNumber.isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + phoneNumber));
                    //if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                    //}
                }
            }
        });

        // Alarm action
        alarmTime = findViewById(R.id.timePicker);
        alarmButton = findViewById(R.id.alarmButton);
        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get hour and minute and create alarm
                createAlarm("Read book", alarmTime.getHour(), alarmTime.getMinute());
            }
        });

        // return to second activity
        returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ThirdActivity", "go to second activity");
                finish();
            }
        });
    }

    public void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);
        //if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        //}
    }

}