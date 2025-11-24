package com.example.booktracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {

    EditText title;
    EditText author;
    EditText year;
    Button submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collectbookdata);

        title = findViewById(R.id.editTitle);
        author = findViewById(R.id.editAuthor);
        year = findViewById(R.id.editYear);
        submit = findViewById(R.id.submitDetails);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check if all details entered
                if (!title.getText().toString().isEmpty() && !author.getText().toString().isEmpty() && !year.getText().toString().isEmpty()) {
                    // go to next activity
                    Log.d("FirstActivity", "go to next activity");
                    Intent i = new Intent(FirstActivity.this, SecondActivity.class);

                    // add all the key-value pairs to pass to second activity
                    int yearNum = Integer.parseInt(year.getText().toString());
                    i.putExtra("title", title.getText().toString());
                    i.putExtra("author", author.getText().toString());
                    i.putExtra("year", yearNum);

                    startActivity(i);
                }
                else {
                    Log.d("FirstActivity","did not input all fields");

                }
            }
        });
    }

}
