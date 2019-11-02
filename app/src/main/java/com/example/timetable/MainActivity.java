package com.example.timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.timetable.view.LectureListViewAdapter;

public class MainActivity extends AppCompatActivity {

    ImageButton searchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

       searchBtn =  findViewById(R.id.search_lecture_btn);

       searchBtn.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, SearchLectureActivity.class);
               startActivity(intent);
           }
       });


    }
}
