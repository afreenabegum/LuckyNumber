package com.example.project_luckynumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    Button btn2;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.tvResult);
        btn2 = findViewById(R.id.button2);

        // Getting name from previous context
        String personName = getIntent().getStringExtra("userName");

        // Getting lucky number
        int number = getLuckyNumber();

        Toast.makeText(getApplicationContext(), "Hey " + personName
                + " your lucky number is " + number, Toast.LENGTH_SHORT).show();

        // Displaying lucky number
        textView.setText(""+number);

        // whenever we click on share on button , name and lucky number should be shared to other
        // apps.

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData(personName, number);
            }
        });

    }

    private void shareData(String name, int digit) {

        // We need to send this data to other Apps like Email, whatsapp etc
        // Implicit Intent
        Intent intent = new Intent(Intent.ACTION_SEND);

        // Sending data in a plain text manner
        intent.setType("text/plain");

        // Passing name, subject
        intent.putExtra(Intent.EXTRA_SUBJECT, name + " got lucky today!");
        intent.putExtra(Intent.EXTRA_TEXT, "The lucky number is: " + digit);

        // This createChooser() is imp, as this method helps in choosing different apps
        startActivity(Intent.createChooser(intent, "Choose a Platform"));

    }


    private int getLuckyNumber() {

        // Random class in Android , helps to generate random numbers, shuffling numbers, random
        // games etc

        Random randomNumber = new Random();

        int upperLimit = 100;
        int luckyNumber = randomNumber.nextInt(upperLimit);

        return luckyNumber;

    }
}