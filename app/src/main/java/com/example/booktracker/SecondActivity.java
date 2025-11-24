package com.example.booktracker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;


import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class SecondActivity extends AppCompatActivity {

    TextView titleText;
    TextView authorText;
    TextView yearText;

    ImageView imageView;

    private ActivityResultLauncher<Intent> cameraLauncher;
    Button cameraButton;

    Button nextButton;

    Button returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bookphoto);

        // get all fields which need to have text added
        titleText = findViewById(R.id.titleLabel);
        authorText = findViewById(R.id.authorLabel);
        yearText = findViewById(R.id.yearLabel);

        // extract intent
        String title = "Title: " + Objects.requireNonNull(getIntent().getExtras()).getString("title");
        String author = "Author: " + Objects.requireNonNull(getIntent().getExtras()).getString("author");
        String year = "Year: " + Objects.requireNonNull(getIntent().getExtras()).getInt("year");

        // display book details
        titleText.setText(title);
        authorText.setText(author);
        yearText.setText(year);

        // add camera functionality
        cameraButton = findViewById(R.id.photoButton);
        imageView = findViewById(R.id.bookImage);

        // Register the ActivityResult launcher
        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null && data.getExtras() != null) {
                            Bitmap photo = (Bitmap) data.getExtras().get("data");
                            imageView.setImageBitmap(photo);
                        }
                    }
                }
        );

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                    cameraLauncher.launch(cameraIntent);
                //}
            }
        });

        // add next intent change
        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("SecondActivity", "go to next activity");
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);

                startActivity(i);
            }
        });

        // return to first activity
        returnButton = findViewById(R.id.returnButton);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("SecondActivity", "go to first activity");
                finish();
            }
        });

    }
}