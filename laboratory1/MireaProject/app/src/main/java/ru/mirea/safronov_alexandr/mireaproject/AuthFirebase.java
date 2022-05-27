package ru.mirea.safronov_alexandr.mireaproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthFirebase extends AppCompatActivity implements View.OnClickListener {

    private EditText emailEditText;
    private EditText passwordTextEdit;
    private TextView statusTextView;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_firebase);

        emailEditText = findViewById(R.id.editEmail);
        passwordTextEdit = findViewById(R.id.editPass);
        statusTextView = findViewById(R.id.signView);

        findViewById(R.id.signIn).setOnClickListener(this);
        findViewById(R.id.createAccountBtn).setOnClickListener(this);
        findViewById(R.id.signOut).setOnClickListener(this);
        auth = FirebaseAuth.getInstance();

    }
    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.createAccountBtn) {
            createAccount(emailEditText.getText().toString(),
                    passwordTextEdit.getText().toString());
        } else if (i == R.id.signIn) {
            signIn(emailEditText.getText().toString(),
                    passwordTextEdit.getText().toString());
        } else if (i == R.id.signOut){
            signOut();
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        updateUI(currentUser);

    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            statusTextView.setText(getString(R.string.emailpassword_status_fmt,
                    user.getEmail(), user.isEmailVerified()));
            findViewById(R.id.editEmail).setVisibility(View.GONE);
            findViewById(R.id.editPass).setVisibility(View.GONE);
            findViewById(R.id.signIn).setVisibility(View.GONE);
            findViewById(R.id.createAccountBtn).setVisibility(View.GONE);
            findViewById(R.id.signOut).setVisibility(View.VISIBLE);

        } else {
            statusTextView.setText(R.string.signed_out);
            findViewById(R.id.editPass).setVisibility(View.VISIBLE);
            findViewById(R.id.editEmail).setVisibility(View.VISIBLE);
            findViewById(R.id.signIn).setVisibility(View.VISIBLE);
            findViewById(R.id.createAccountBtn).setVisibility(View.VISIBLE);
            findViewById(R.id.signOut).setVisibility(View.VISIBLE);
        }
    }

    private boolean validateForm() {
        boolean valid = true;
        String email = emailEditText.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Required.");
            valid = false;
        } else {
            emailEditText.setError(null);
        }
        String password = passwordTextEdit.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordTextEdit.setError("Required.");
            valid = false;
        } else {
            passwordTextEdit.setError(null);
        }
        return valid;
    }

    private void createAccount(String email, String password) {
        if (!validateForm()) {
            return;
        }
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        updateUI(user);
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(AuthFirebase.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });
    }

    private void signOut() {
        auth.signOut();
        updateUI(null);
    }

    private void signIn(String email, String password) {
        if (!validateForm()) {
            return;
        }
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        updateUI(user);
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(AuthFirebase.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                    if (!task.isSuccessful()) {
                        statusTextView.setText(R.string.auth_failed);
                    }
                });
    }
}