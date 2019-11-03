package com.example.timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.timetable.lecture.EntrustedLecture;
import com.example.timetable.lecture.Lecture;
import com.example.timetable.lecture.LectureList;
import com.example.timetable.retrofit.NetRetrofit;
import com.example.timetable.view.LectureListViewAdapter;
import com.example.timetable.view.LectureListViewItem;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String USER_KEY = "022c67d4ef7e7f3454192358c900d156";
    private static final String TAG = "MAINACTIVITY";
    private static final SimpleDateFormat dataFormat = new SimpleDateFormat ( "dd");
    private static final SimpleDateFormat yearAndMonthFormat = new SimpleDateFormat("yyyy년 MM월");
    private static Calendar c = Calendar.getInstance();
    private static int changedTime = 0;
    ImageButton searchBtn;
    TextView mondayTextView;
    TextView tuesdayTextView;
    TextView weddayTextView;
    TextView thurdayTextView;
    TextView fridayTextView;
    TextView yearAndMonthView;
    ImageButton beforeBtn;
    ImageButton nextBtn;
    TextView changedWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        yearAndMonthView = findViewById(R.id.year_and_month);
       searchBtn =  findViewById(R.id.search_lecture_btn);
       mondayTextView = findViewById(R.id.mondayTextView);
       tuesdayTextView = findViewById(R.id.tuesdayTextView);
       weddayTextView = findViewById(R.id.weddayTextView);
       thurdayTextView = findViewById(R.id.thurdayTextView);
       fridayTextView = findViewById(R.id.fridayTextView);
       beforeBtn = findViewById(R.id.beforeWeekBtn);
       nextBtn = findViewById(R.id.nextWeekBtn);
       changedWeek = findViewById(R.id.changed_week_time_textview);

       searchBtn.setOnClickListener(new View.OnClickListener(){

           @Override
           public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, SearchLectureActivity.class);
               startActivity(intent);
           }
       });

       beforeBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               changedTime -= 1;
               setDate(-7);
           }
       });

       nextBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               changedTime += 1;
               setDate(7);
           }
       });
       getEntrustedLecture();
       setDate(0);

    }
    public void setDate(int date){
        c.add(c.DATE, date);
        yearAndMonthView.setText(yearAndMonthFormat.format(c.getTime()));
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        mondayTextView.setText(dataFormat.format(c.getTime()));
        c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        tuesdayTextView.setText(dataFormat.format(c.getTime()));
        c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        weddayTextView.setText(dataFormat.format(c.getTime()));
        c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        thurdayTextView.setText(dataFormat.format(c.getTime()));
        c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        fridayTextView.setText(dataFormat.format(c.getTime()));

        if(changedTime > 0) changedWeek.setText(changedTime + "주 뒤");
        else if(changedTime < 0) changedWeek.setText(-changedTime + "주 전");
        else changedWeek.setText("오늘");
    }

    public void getEntrustedLecture() {
        Call<EntrustedLecture> res = NetRetrofit.getInstance().getService().getEntrustedLecture(USER_KEY);

        res.enqueue(new Callback<EntrustedLecture>() {
            @Override
            public void onResponse(Call<EntrustedLecture> call, Response<EntrustedLecture> response) {
                Log.d("Retrofit", response.toString());
                if (response.body() != null) {
                    Log.d(TAG,"결과 : " + response.body().toString());
                }
            }
            @Override
            public void onFailure(Call<EntrustedLecture> call, Throwable t) {
                Log.e("Err", t.getMessage());
            }
        });
    }
}
