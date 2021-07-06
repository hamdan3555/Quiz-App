package com.example.quizworld.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizworld.R;
import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    TextView textView;
    Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);

        firebaseAuth=FirebaseAuth.getInstance();
        textView=findViewById(R.id.txtEmail);
        if(firebaseAuth.getCurrentUser()!=null){
            textView.setText(firebaseAuth.getCurrentUser().getEmail());
        }


        btn_logout=findViewById(R.id.btnLogout);
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(Profile.this, login.class);
                Profile.this.startActivity(intent);
                finish();
            }
        });
    }
}