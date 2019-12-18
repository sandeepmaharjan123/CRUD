package com.example.crud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditDetails extends AppCompatActivity {
    EditText etName, etEmail, etPhone;
    Button btnUpdate, btnGoBack;
    String id, name, email, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_details);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnGoBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        phone = intent.getStringExtra("phone");

        etName.setText(name);
        etEmail.setText(email);
        etPhone.setText(phone);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper db = new DatabaseHelper(EditDetails.this);
                String ename = etName.getText().toString();
                String eemail = etEmail.getText().toString();
                String ephone = etPhone.getText().toString();
                boolean status = db.updateStudentDetails(Integer.parseInt(id), ename, eemail, ephone);

                if(status){
                    Toast.makeText(EditDetails.this, "Student Details Changed!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditDetails.this, "Error! Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_back = new Intent(EditDetails.this, DisplayStudent.class);
                startActivity(intent_back);
            }
        });


    }
}
