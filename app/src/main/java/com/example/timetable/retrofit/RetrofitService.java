package com.example.timetable.retrofit;


import com.example.timetable.lecture.EntrustedLecture;
import com.example.timetable.lecture.LectureList;

import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitService {
    String  BASE_URL = "https://k03c8j1o5a.execute-api.ap-northeast-2.amazonaws.com/v1/programmers/";

    @GET("lectures")
    @Headers({"x-api-key: QJuHAX8evMY24jvpHfHQ4pHGetlk5vn8FJbk70O6", "Content-Type: application/json"})
    Call<LectureList> getLectureList();

    @GET("timetable")
    @Headers({"x-api-key: QJuHAX8evMY24jvpHfHQ4pHGetlk5vn8FJbk70O6", "Content-Type: application/json"})
    Call<EntrustedLecture> getEntrustedLecture(@Query("user_key") String user_key);

    @FormUrlEncoded
    @POST("timetable")
    @Headers({"x-api-key: QJuHAX8evMY24jvpHfHQ4pHGetlk5vn8FJbk70O6", "Content-Type: application/json"})
    Call<ResponseBody> insertLecture(@Field("user_key") String key, @Field("code") String code);
}

