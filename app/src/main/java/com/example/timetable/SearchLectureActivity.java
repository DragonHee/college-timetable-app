package com.example.timetable;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timetable.lecture.Lecture;
import com.example.timetable.lecture.LectureList;
import com.example.timetable.retrofit.NetRetrofit;
import com.example.timetable.view.LectureListViewAdapter;
import com.example.timetable.view.LectureListViewItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Callback;


public class SearchLectureActivity extends AppCompatActivity {
    private List<LectureListViewItem> itemList;
    private static final String TAG = "SearchLectureActivity";
    private EditText editSearch;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.search_lecture);
        getLectureList();

        editSearch = findViewById(R.id.search_lecture_edit);
        itemList = new ArrayList<>();

//        editSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                // input창에 문자를 입력할때마다 호출된다.
//                // search 메소드를 호출한다.
//                String text = editSearch.getText().toString();
//                search(text);
//            }
//        });
        for(LectureListViewItem l : itemList){
            Log.d(TAG, l.getProfessorName());
        }
        Log.d(TAG, "길이 : " + itemList.size());
        Log.d(TAG, "액티비티 생성...");
    }

    public void getLectureList() {
            Call<LectureList> res = NetRetrofit.getInstance().getService().getLectureList();

            res.enqueue(new Callback<LectureList>() {
                @Override
                public void onResponse(Call<LectureList> call, Response<LectureList> response) {
                    Log.d("Retrofit", response.toString());
                    if (response.body() != null) {
                        ListView listview = findViewById(R.id.lecture_list_view);
                        LectureListViewAdapter adapter = new LectureListViewAdapter();
                        listview.setAdapter(adapter);

                        ArrayList<LectureListViewItem> dataList = new ArrayList<>();

//                        월, 화, 수 ---  정렬 필요
                        for(Lecture lecture : response.body().getItems()){
                            String dayOfWeek = "";
                            for(String s : lecture.getDayofweek()){
                                dayOfWeek += "(" + s + "), ";
                            }
                            dayOfWeek = dayOfWeek.substring(0, dayOfWeek.length() - 2);
                            dataList.add(new LectureListViewItem(lecture.getLecture(), lecture.getStart_time(), lecture.getEnd_time(), dayOfWeek, lecture.getCode(), lecture.getProfessor(), lecture.getLocation()));
                            adapter.addItem(lecture.getLecture(), lecture.getStart_time(), lecture.getEnd_time(), dayOfWeek, lecture.getCode(), lecture.getProfessor(), lecture.getLocation());
                        }
                    }
                }

                @Override
                public void onFailure(Call<LectureList> call, Throwable t) {
                    Log.e("Err", t.getMessage());
                }
            });
    }







}
