package com.example.simpleform;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {

    private TextView date, student_id, student_name, identity_number, phone_number, email, date_of_birth, hometown, current_address;
    private CheckBox policy, c, java, python;
    private RadioGroup radio_group;
    private Button select_date;

    private Calendar calendar;
    private DatePickerDialog dpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radio_group = findViewById(R.id.radio_group);
        date = findViewById(R.id.date_of_birth);
        select_date = findViewById(R.id.select_dob);

        student_id = findViewById(R.id.student_id);
        student_name = findViewById(R.id.student_name);
        identity_number = findViewById(R.id.identity_number);
        phone_number = findViewById(R.id.phone_number);
        email = findViewById(R.id.email);
        date_of_birth = findViewById(R.id.date_of_birth);
        hometown = findViewById(R.id.hometown);
        current_address = findViewById(R.id.current_address);
        c = findViewById(R.id.c);
        java = findViewById(R.id.java);
        python = findViewById(R.id.python);
        policy = findViewById(R.id.policy);

        select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText((month + 1 < 10 ? "0" : "") + (month + 1) + "/" + (dayOfMonth < 10 ? "0" : "") + dayOfMonth + "/" + year);
                    }
                }, day, month, year);
                dpd.show();
            }
        });

        Button b = findViewById(R.id.ok_btn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String datePattern = "^(1[0-2]|0[1-9])/(3[01]" + "|[12][0-9]|0[1-9])/[0-9]{4}$";

                if (student_id.getText().toString().equals("")) {
                    student_id.setError("Empty student id");
                }  else if (student_name.getText().toString().equals("")) {
                    student_name.setError("Empty student name");
                } else if (identity_number.getText().toString().equals("")) {
                    identity_number.setError("Empty identity number");
                } else if (phone_number.getText().toString().equals("")) {
                    phone_number.setError("Empty phone number");
                } else if (email.getText().toString().equals("")) {
                    email.setError("Empty email");
                } else if (!email.getText().toString().trim().matches(emailPattern)) {
                    email.setError("Invalid email address");
                } else if (date_of_birth.getText().toString().equals("")) {
                    date_of_birth.setError("Empty date of birth");
                } else if (!date_of_birth.getText().toString().trim().matches(datePattern)) {
                    date_of_birth.setError("Date format should be 'mm/dd/yyyy'");
                } else if(!policy.isChecked()) {
                    policy.setError("Policy must be accepted");
                } else {
                    student_id.setText("");
                    student_name.setText("");
                    identity_number.setText("");
                    phone_number.setText("");
                    email.setText("");
                    date_of_birth.setText("");
                    hometown.setText("");
                    current_address.setText("");
                    c.setChecked(false);
                    java.setChecked(false);
                    python.setChecked(false);
                    policy.setChecked(false);
                    policy.setError(null);
                    toastMessage("Submitted successfully!");
                }
            }
        });
    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

