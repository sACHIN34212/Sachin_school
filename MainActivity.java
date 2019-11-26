package com.example.sachin_school;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText  pass,email;
    private Button login;
    private FirebaseAuth firebaseAuth;
    private int counter=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText)findViewById(R.id.editText2);
        pass = (EditText)findViewById(R.id.editText3);
        login = (Button)findViewById(R.id.button);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user!=null)
        {
            finish();
            startActivity(new Intent(MainActivity.this , home.class));
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(email.getText().toString(), pass.getText().toString());

            }
        });
    }


    private void validate(String Email, String password){
        firebaseAuth.signInWithEmailAndPassword(Email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this , home.class));
                }else {
                    Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                    counter--;
                    if (counter==0){
                        login.setEnabled(false);
                    }
                }
            }
        });
    }

}
