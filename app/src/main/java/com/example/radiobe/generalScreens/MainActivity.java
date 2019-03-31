package com.example.radiobe.generalScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.radiobe.R;
import com.example.radiobe.registrations.Login;

public class MainActivity extends AppCompatActivity {
    Button btnLive;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();

        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        });


    }

    private void setupViews() {
        btnLive = findViewById(R.id.btnLive);
        btnLogin = findViewById(R.id.btnLogin);
    }
}
