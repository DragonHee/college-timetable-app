package com.example.timetable;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timetable.lecture.Lecture;
import com.example.timetable.lecture.LectureList;
import com.example.timetable.retrofit.NetRetrofit;
import com.example.timetable.view.LectureListViewAdapter;
import com.example.timetable.view.LectureListViewItem;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectedLectureInformationActivity extends AppCompatActivity {
    private static final String USER_KEY = "022c67d4ef7e7f3454192358c900d156";
    private static final String TAG = "SelectedLectureInformationActivity";
    private TextView subjectNameTextView;
    private TextView startTimeTextView;
    private TextView endTimeTextView;
    private TextView dayOfWeekTextView;
    private TextView classCodeTextView;
    private TextView professorTextView;
    private TextView locationTextView;
    private Button entrustLectureBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.lecture_infomation);

        subjectNameTextView = findViewById(R.id.lecture_information_subject);
        startTimeTextView = findViewById(R.id.lecture_information_start_time);
        endTimeTextView = findViewById(R.id.lecture_information_end_time);
        dayOfWeekTextView = findViewById(R.id.lecture_information_dayOfWeek);
        classCodeTextView = findViewById(R.id.lecture_information_code);
        professorTextView = findViewById(R.id.lecture_information_professor);
        locationTextView = findViewById(R.id.lecture_information_location);

        Intent intent = getIntent();

        subjectNameTextView.setText(intent.getStringExtra("subjectName"));
        startTimeTextView.setText(intent.getStringExtra("startTime"));
        endTimeTextView.setText(intent.getStringExtra("endTime"));
        dayOfWeekTextView.setText(intent.getStringExtra("dayOfWeek"));
        classCodeTextView.setText(intent.getStringExtra("classCode"));
        professorTextView.setText(intent.getStringExtra("professorName"));
        locationTextView.setText(intent.getStringExtra("location"));

        entrustLectureBtn = findViewById(R.id.entrustLectureBtn);

        entrustLectureBtn.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View view) {
                insertLecture(classCodeTextView.getText().toString());
            }
        });
    }

    public void insertLecture(String code) {
        Call<ResponseBody> res = NetRetrofit.getInstance().getService().insertLecture(USER_KEY, code);

        res.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, response.code() + "");
                Log.d(TAG, response.body() + "");
                Toast.makeText(getApplicationContext(), response.code() + "번 서버 오류 발생...",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "통신 실패!");
            }
        });
    }
}