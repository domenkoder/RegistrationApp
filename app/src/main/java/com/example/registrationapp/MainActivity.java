package com.example.registrationapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etUsername = findViewById(R.id.editTextText);
        EditText etFullName = findViewById(R.id.editTextText2);
        EditText etCountry = findViewById(R.id.editTextText3);
        EditText etEmail = findViewById(R.id.editTextTextEmailAddress);
        EditText etPhone = findViewById(R.id.editTextPhone);
        RadioGroup radioGroupGender = findViewById(R.id.radioGroupGender);
        EditText etPassword = findViewById(R.id.editTextTextPassword);
        CheckBox checkBoxTerms = findViewById(R.id.checkBox);
        Button btnCreateAccount = findViewById(R.id.button);

        btnCreateAccount.setOnClickListener(v -> {
            int selectedGenderId = radioGroupGender.getCheckedRadioButtonId();
            if (selectedGenderId == -1) {
                Toast.makeText(MainActivity.this, "Please select a gender", Toast.LENGTH_SHORT).show();
                return;
            }

            String username = etUsername.getText().toString();
            String fullName = etFullName.getText().toString();
            String country = etCountry.getText().toString();
            String email = etEmail.getText().toString();
            String phone = etPhone.getText().toString();
            String password = etPassword.getText().toString();

            RadioButton selectedGender = findViewById(selectedGenderId);
            String gender = selectedGender != null ? selectedGender.getText().toString() : "";

            boolean agreed = checkBoxTerms.isChecked();

            String summary = "Username: " + username + "\n" +
                    "Full Name: " + fullName + "\n" +
                    "Country: " + country + "\n" +
                    "Email: " + email + "\n" +
                    "Phone: " + phone + "\n" +
                    "Gender: " + gender + "\n" +
                    "Password: " + password + "\n" +
                    "Agreed to Terms: " + (agreed ? "Yes" : "No");

            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Registration Summary Dialog")
                    .setMessage(summary)
                    .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                    .show();
        });
    }
}
