/*package com.example.foodsy.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.foodsy.MainActivity;
import com.example.foodsy.R;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public void Login(View view) {
        Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void mainActivity(View view) {
        Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
        startActivity(intent);
    }
}*/
package com.example.foodsy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodsy.DatabaseHelper;
import com.example.foodsy.R;
import com.example.foodsy.models.User;

public class RegistrationActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;

    private Button registerButton;
    private TextView loginTextView;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        nameEditText = findViewById(R.id.editText);
        emailEditText = findViewById(R.id.editText2);

        passwordEditText = findViewById(R.id.editText3);
        passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());

        registerButton = findViewById(R.id.button);
        loginTextView = findViewById(R.id.sign);

        databaseHelper = new DatabaseHelper(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });
    }


    private void register() {
        String username = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Enter username", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!isValidEmail(email)) {
            Toast.makeText(this, "Enter a valid email address", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 4) {
            Toast.makeText(this, "Password must be at least 4 characters long", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean userExists = databaseHelper.checkUser(email, password);
        if (userExists) {
            Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(username, email, password);
        boolean registrationSuccessful = databaseHelper.addUser(user);
        if (registrationSuccessful) {
            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Registration error", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidEmail(String email) {
        // Add your email validation logic here
        // Return true if the email is valid, false otherwise
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
