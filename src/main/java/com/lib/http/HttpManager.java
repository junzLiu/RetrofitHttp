package com.lib.http;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @author:markLiu
 * @create:2018/12/3
 * @title:
 * @description:
 **/
public class HttpManager {

    private static HttpManager instance;
    private static Retrofit retrofit;

    private HttpManager() {
    }

    public static HttpManager getInstance() {
        if (instance == null)
            instance = new HttpManager();
        return instance;
    }

    public void init(String baseUrl, Interceptor... interceptors) {
        OkHttpClient.Builder okHttpBuilder = OkHttpHelper.getOkHttp();
        if (interceptors != null && interceptors.length > 0)
            for (Interceptor interceptor : interceptors)
                okHttpBuilder.addInterceptor(interceptor);
        retrofit = RetrofitHelper.buildRetrofit(baseUrl).client(okHttpBuilder.build()).build();
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }
}
