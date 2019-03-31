package com.example.radiobe.registrations;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.radiobe.R;
import com.example.radiobe.mainScreen.MainScreen;

public class Login extends AppCompatActivity {
     EditText etName;
     EditText etPassword;
     CheckBox cbRemember;
     TextView tvForgotPassword;
     Button btnLogin;
     Button btnSignUp;
     Button btnFacebook;
     Button btnGoogle;
     Button btnInstagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findView();
        btnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
        });

        btnLogin.setOnClickListener(v -> {

//            if(etName.length() != -1 && etPassword.length() != -1) {
                Intent intent = new Intent(this, MainScreen.class);
                startActivity(intent);
//            }
        });
    }

    private void findView() {
        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        cbRemember = findViewById(R.id.cbRemember);
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnFacebook = findViewById(R.id.btnFacebook);
        btnGoogle = findViewById(R.id.btnGoogle);
        btnInstagram = findViewById(R.id.btnInstagram);
    }
}
