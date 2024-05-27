package com.example.travelzz;

// LoginActivity.java
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    private EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();

        // Initialize EditText fields
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);

        // Add OnClickListener to login button
        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(); // Call loginUser() method when login button is clicked
            }
        });
    }

//    private void loginUser() {
//        String email = etEmail.getText().toString().trim();
//        String password = etPassword.getText().toString().trim();
//
//        // Implement authentication logic here (e.g., using Firebase Authentication)
//        // For demonstration purposes, assume authentication is successful
//        // Replace the following lines with actual authentication logic
//        if (email.isEmpty() || password.isEmpty()) {
//            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
//    }

    private void loginUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(LoginActivity.this, "Authentication successful.",
                                Toast.LENGTH_SHORT).show();
                        // Redirect to main activity or dashboard
                        // Example: startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
