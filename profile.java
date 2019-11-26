package com.example.sachin_school;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {

    private ImageView profilepic;
    private TextView age1,name1,section1,reg_no1,course1;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth.AuthStateListener authStateListener;
    private DatabaseReference myRef;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilepic = findViewById(R.id.profile_pic);
        age1 = findViewById(R.id.age);
        name1 = findViewById(R.id.name);
        section1 = findViewById(R.id.section);
        reg_no1 = findViewById(R.id.reg_no);
        course1 = findViewById(R.id.course);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        myRef = firebaseDatabase.getReference();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        userID=user.getUid();

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserProfile userProfile = dataSnapshot.getValue(UserProfile.class);
                name1.setText("Name :"+userProfile.getName());
                reg_no1.setText("Registration Number :"+userProfile.getReg_no());
                section1.setText("Section :"+userProfile.getSection());
                age1.setText("Age :"+userProfile.getAge());
                course1.setText("Course "+userProfile.getCourse());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(profile.this, databaseError.getCode(),Toast.LENGTH_LONG).show();

            }
        });
    }
}
