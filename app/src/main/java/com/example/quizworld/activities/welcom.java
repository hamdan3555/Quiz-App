package com.example.quizworld.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quizworld.R;
import com.google.firebase.auth.FirebaseAuth;

public class welcom extends AppCompatActivity {
    private Button get_start;
    private FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        get_start = (Button) findViewById(R.id.get_started);
        Auth = FirebaseAuth.getInstance();
// Make Sure the if condition must be is not equal to
        if (Auth.getCurrentUser() != null) {

            Toast.makeText(welcom.this, "Already Logged In",
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(welcom.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();

        }
        else{
            get_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(welcom.this, login.class));
                    finish();
                }
            });
        }


    }
}