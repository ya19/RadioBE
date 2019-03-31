package com.example.radiobe.registrations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.radiobe.R;

public class SignUp extends AppCompatActivity {
    EditText etFirst;
    EditText etLast;
    EditText etDate;
    EditText etEmail;
    EditText etPassword;
    EditText etPassAgain;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        findView();
    }

    private void findView() {
        etFirst = findViewById(R.id.etFirst);
        etLast = findViewById(R.id.etLast);
        etDate = findViewById(R.id.etDate);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etPassAgain = findViewById(R.id.etPassAgain);
        btnSignUp = findViewById(R.id.btnSignUp);
    }
}
