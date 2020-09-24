package com.example.librarypiyusheklavya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.view.View.*;

public class AddActivity extends AppCompatActivity {

    EditText title_ET, description_ET;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        title_ET = findViewById(R.id.title_ET);
        description_ET = findViewById(R.id.description_ET);
        addBtn = findViewById(R.id.addbook_btn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addBook(title_ET.getText().toString().trim(),
                            description_ET.getText().toString().trim());
            }
        });
    }
}