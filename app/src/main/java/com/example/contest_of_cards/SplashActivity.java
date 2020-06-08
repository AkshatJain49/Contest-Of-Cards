package com.example.contest_of_cards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final ProgressBar progressBar = findViewById(R.id.progressBar);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(1000);
                    for (int i = 1; i <= 100; i++) {
                        sleep(20);
                        progressBar.setProgress(i);

                    }
                    sleep(500);
                    Intent intent = new Intent(SplashActivity.this, PlayActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }; //THREAD CLASS ENDS
        thread.start();
    }
}