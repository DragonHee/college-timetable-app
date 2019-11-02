package com.example.timetable.retrofit;


import com.example.timetable.lecture.LectureList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface RetrofitService {
    String  BASE_URL = "https://k03c8j1o5a.execute-api.ap-northeast-2.amazonaws.com/v1/programmers/";

    @GET("lectures")
    @Headers({"x-api-key: QJuHAX8evMY24jvpHfHQ4pHGetlk5vn8FJbk70O6", "Content-Type: application/json"})
    Call<LectureList> getLectureList();
}

