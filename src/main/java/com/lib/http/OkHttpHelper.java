package com.lib.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;

/**
 * @author:markLiu
 * @create:2018/12/3
 * @title: 配置OkHttp
 * @description:
 **/
public class OkHttpHelper {

    public static OkHttpClient.Builder getOkHttp() {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .callTimeout(15, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(new LoggingInterceptor(new MyLogger()));
    }

    static final class MyLogger implements LoggingInterceptor.Logger {

        @Override
        public void log(String message) {
            if (BuildConfig.DEBUG)
                Platform.get().log(0, message, null);
        }
    }
}
