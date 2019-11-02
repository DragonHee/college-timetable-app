package com.example.timetable;


import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timetable.view.LectureListViewAdapter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SearchLectureActivity extends AppCompatActivity {
    private static final String TAG = "SearchLectureActivity";
    private Retrofit mRetrofit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.search_lecture);

        ListView listview ;
        LectureListViewAdapter adapter;

        // Adapter 생성
        adapter = new LectureListViewAdapter() ;

        // 리스트뷰 참조 및 Adapter달기
        listview =  findViewById(R.id.lecture_list_view);
        listview.setAdapter(adapter);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                // Create URL
                URL baseURL = null;
                try {
                    baseURL = new URL("https://k03c8j1o5a.execute-api.ap-northeast-2.amazonaws.com/v1/programmers");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                // Create connection
                try {
                    HttpsURLConnection myConnection = (HttpsURLConnection) baseURL.openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        // 첫 번째 아이템 추가.
        adapter.addItem("웹 프로그래밍", "09:00", "10:50", "(월), (수)", "A0000001", "김진수", "공학관 204");
        // 두 번째 아이템 추가.
        adapter.addItem("프로그래밍의 원리", "13:00", "14:50", "(월), (수)", "A0000002", "이지형", "공학1관 102");

        Log.d(TAG, "액티비티 생성...");
    }



}
