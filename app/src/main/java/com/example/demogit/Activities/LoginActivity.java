package com.example.demogit.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demogit.R;

public class LoginActivity extends AppCompatActivity {
    EditText email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
    }


    public void showProfile(View view) {

        if (!TextUtils.isEmpty(email.getText().toString())) {
            Intent intent = new Intent(this, ProfilePageActivity.class);
            intent.putExtra("Username", email.getText().toString());
            startActivity(intent);
        }
        else
            email.setError("Field Required".toString()
        );

    }
}
