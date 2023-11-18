package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
      public UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView userDetailsTextView = findViewById(R.id.user_details);
        Button logoutButton = findViewById(R.id.logout);
        EditText fullnameEditText = findViewById(R.id.fullname);
        EditText codeEditText = findViewById(R.id.code);
        Button submitButton = findViewById(R.id.submit);
        Button txtButton = findViewById(R.id.txt_btn);

        // Initialize Firebase
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("user_data");

        // Set click listener for the "המשך" button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the button click
                String fullname = fullnameEditText.getText().toString();
                String code = codeEditText.getText().toString();

                // Store user data in Firebase
                storeUserData(fullname, code);

                // Create an Intent to start the ListActivity
                Intent intent = new Intent(MainActivity.this, ListActivity.class);

                // Pass data to the second activity
                intent.putExtra("FULLNAME", fullname);
                intent.putExtra("CODE", code);

                // Start the second activity
                startActivity(intent);
            }
        });

        // Add your other code for the first layout here
    }

    private void storeUserData(String fullname, String code) {
        // Store user data in Firebase
        userData = new UserData(fullname, code);
        databaseReference.push().setValue(userData);
    }
}