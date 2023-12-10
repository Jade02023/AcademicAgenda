package com.example.academicagendav1;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Welcome extends AppCompatActivity {

    private static final long SPLASH_TIME_OUT = 3000; // 3 seconds

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        // Using a Handler to delay the transition to the next screen
        new Handler().postDelayed(() -> {
            // Start the next activity after the splash time out
            Intent intent = new Intent(Welcome.this, MainActivity.class);
            startActivity(intent);

            finish();
        }, SPLASH_TIME_OUT);

    }
}
