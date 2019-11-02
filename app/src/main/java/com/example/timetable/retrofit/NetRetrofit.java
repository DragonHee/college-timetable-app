package com.example.timetable.retrofit;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetRetrofit {
    private static final String BASE_URL = "https://k03c8j1o5a.execute-api.ap-northeast-2.amazonaws.com/v1/programmers";
    private static final String API_KEY = "QJuHAX8evMY24jvpHfHQ4pHGetlk5vn8FJbk70O6";
    private static final String TOKEN = "022c67d4ef7e7f3454192358c900d156";


    private static NetRetrofit ourInstance = new NetRetrofit();
    public static NetRetrofit getInstance() {
        return ourInstance;
    }
    private NetRetrofit() {
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // 파싱등록
            .build();

    RetrofitService service = retrofit.create(RetrofitService.class);

    public RetrofitService getService() {
        return service;
    }
}

