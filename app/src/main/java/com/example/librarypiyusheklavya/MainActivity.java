package com.example.librarypiyusheklavya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity<myDB> extends AppCompatActivity {
    FloatingActionButton add_button;
    RecyclerView recyclerView;
    RecyclerView.Adapter programAdapter;
    RecyclerView.LayoutManager layoutmanager;

    String[] programNameList = {"book one","book two","book three","book four","book five",
            "book six","book seven","book eight","book nine","book ten",};

    String[] programDescriptionList = {"This book is right out of my cherished collection for holiday mood",
            "This book is right out of my cherished collection for holiday mood",
            "This book is right out of my cherished collection for holiday mood",
            "This book is right out of my cherished collection for holiday mood",
            "This book is right out of my cherished collection for holiday mood",
            "This book is right out of my cherished collection for holiday mood",
            "This book is right out of my cherished collection for holiday mood",
            "This book is right out of my cherished collection for holiday mood",
            "This book is right out of my cherished collection for holiday mood",
            "This book is right out of my cherished collection for holiday mood"};

    int[] programImages = {R.drawable.a,R.drawable.b,
                           R.drawable.c,R.drawable.d,
                           R.drawable.e,R.drawable.f,
                           R.drawable.g,R.drawable.h,
                           R.drawable.i,R.drawable.j};

//

    MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
    ArrayList<String> book_id = new ArrayList<>();
    ArrayList<String> book_title = new ArrayList<>();
    ArrayList<String> book_description = new ArrayList<>();

    String[] getStringArray(ArrayList<String> arr)
    {
        String str[] = new String[arr.size()];
        for (int j = 0; j < arr.size(); j++) {
            str[j] = arr.get(j);
        }
        return str;
    }

    void displayData() {
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0) {
            Toast.makeText(this, "No books to show!", Toast.LENGTH_SHORT).show();
        } else {
            while(cursor.moveToNext()) {
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_description.add(cursor.getString(2));
            }
        }
    }

    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayData();

        add_button = findViewById(R.id.floatingActionButton);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });


        recyclerView = findViewById(R.id.rv);
        layoutmanager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutmanager);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        programAdapter = new ProgramAdapter(this, getStringArray(book_title), getStringArray(book_description), programImages);
        //programAdapter = new ProgramAdapter(this, programNameList, programDescriptionList);
        recyclerView.setAdapter(programAdapter);
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            //deleteNote(mNotes.get(viewHolder.getAdapterPosition()));
            return;
        }
    };
}