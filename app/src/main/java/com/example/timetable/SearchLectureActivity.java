package com.example.timetable;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
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
    private static final String TAG = "SearchLectureActivity";
    private LectureListViewAdapter adapter;
    private EditText editSearch;
    private List<LectureListViewItem> arraylist = new ArrayList<>();
    private ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.search_lecture);
        getLectureList();
        listView = findViewById(R.id.lecture_list_view);
        editSearch = findViewById(R.id.search_lecture_edit);

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                String text = editSearch.getText().toString();
                search(text);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(
                        getApplicationContext(), // 현재화면의 제어권자
                        SelectedLectureInformationActivity.class); // 다음넘어갈 화면

                // intent 객체에 데이터를 실어서 보내기
                // 리스트뷰 클릭시 인텐트 (Intent) 생성하고 position 값을 이용하여 인텐트로 넘길값들을 넘긴다
                intent.putExtra("subjectName", adapter.getList().get(i).getSubjectName());
                intent.putExtra("startTime", adapter.getList().get(i).getStartTime());
                intent.putExtra("endTime", adapter.getList().get(i).getEndTime());
                intent.putExtra("dayOfWeek", adapter.getList().get(i).getDayOfWeek());
                intent.putExtra("classCode", adapter.getList().get(i).getClassCode());
                intent.putExtra("professorName", adapter.getList().get(i).getProfessorName());
                intent.putExtra("location", adapter.getList().get(i).getLocation());

                startActivity(intent);
            }
        });

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


                        ArrayList<LectureListViewItem> dataList = new ArrayList<>();

//                        월, 화, 수 ---  정렬 필요
                        for(Lecture lecture : response.body().getItems()){
                            String dayOfWeek = "";
                            for(String s : lecture.getDayofweek()){
                                dayOfWeek += "(" + s + "), ";
                            }
                            dayOfWeek = dayOfWeek.substring(0, dayOfWeek.length() - 2);
                            dataList.add(new LectureListViewItem(lecture.getLecture(), lecture.getStart_time(), lecture.getEnd_time(), dayOfWeek, lecture.getCode(), lecture.getProfessor(), lecture.getLocation()));
                        }

                        adapter = new LectureListViewAdapter(dataList, SearchLectureActivity.this);
                        listview.setAdapter(adapter);
                        arraylist.addAll(dataList);

                    }
                }

                @Override
                public void onFailure(Call<LectureList> call, Throwable t) {
                    Log.e("Err", t.getMessage());
                }
            });
    }

    // 검색을 수행하는 메소드
    public void search(String charText) {
        List<LectureListViewItem> list = adapter.getList();
        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
       list.clear();

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            list.addAll(arraylist);
        }
        // 문자 입력을 할때..
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < arraylist.size(); i++)
            {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (arraylist.get(i).getSubjectName().contains(charText) || arraylist.get(i).getProfessorName().contains(charText))
                {
                    // 검색된 데이터를 리스트에 추가한다.
                    list.add(arraylist.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();
    }





}
