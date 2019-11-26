package com.example.sachin_school;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class home extends AppCompatActivity {

    private Button logout , profile1 ,attendence1;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        logout = (Button)findViewById(R.id.logout);
        attendence1 = findViewById(R.id.attendence);

        firebaseAuth=FirebaseAuth.getInstance();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(home.this,MainActivity.class));
            }
        });

        profile1 = findViewById(R.id.profile);
        profile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this , profile.class));
            }
        });

        attendence1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(home.this , attendence.class));
            }
        });

    }
}
