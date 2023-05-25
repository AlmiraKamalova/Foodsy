/*package com.example.foodsy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.foodsy.MainActivity;
import com.example.foodsy.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void Register(View view) {

        Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }

    public void mainActivity(View view) {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
}*/
package com.example.foodsy.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodsy.DatabaseHelper;
import com.example.foodsy.MainActivity;
import com.example.foodsy.R;
import com.example.foodsy.models.User;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private DatabaseHelper databaseHelper;

    private Button loginButton;
    private TextView registerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.editText2);
        passwordEditText = findViewById(R.id.editText3);
        passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());

        loginButton = findViewById(R.id.button);
        registerTextView = findViewById(R.id.reg);

        databaseHelper = new DatabaseHelper(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty()) {
            emailEditText.setError("Enter email");
            emailEditText.requestFocus();
            return;
        }

        if (!isValidEmail(email)) {
            emailEditText.setError("Enter a valid email address");
            emailEditText.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passwordEditText.setError("Enter password");
            passwordEditText.requestFocus();
            return;
        }

        if (password.length() < 4) {
            passwordEditText.setError("Password must be at least 4 characters long");
            passwordEditText.requestFocus();
            return;
        }

        User user = databaseHelper.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            // После успешной проверки учетных данных пользователя

            // Сохраняем данные пользователя в SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("userName", user.getName());
            editor.putString("userEmail", user.getEmail());
            editor.apply();

            // Переходим в MainActivity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isValidEmail(String email) {
        // Add your email validation logic here
        // Return true if the email is valid, false otherwise
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}


