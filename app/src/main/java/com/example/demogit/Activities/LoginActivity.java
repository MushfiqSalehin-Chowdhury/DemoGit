package com.example.demogit.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.demogit.R;

public class LoginActivity extends AppCompatActivity {
    EditText email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.email);
    }


    public void showProfile(View view) {

        Intent intent = new Intent (this,ProfilePageActivity.class );
        intent.putExtra ( "TextBox", email.getText().toString() );
        startActivity(intent);

    }
}
