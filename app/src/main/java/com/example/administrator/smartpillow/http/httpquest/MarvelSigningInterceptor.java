package com.example.administrator.smartpillow.http.httpquest;


import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



/**
 * Created by likun on 2016/8/3.
 * 添加一些公共的参数
 */

public class MarvelSigningInterceptor implements Interceptor {

    public String[] noAction = new String[]{};

    public MarvelSigningInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取请求
        Request oldRequest = chain.request();
        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                .newBuilder()
                .scheme(oldRequest.url().scheme())
                .host(oldRequest.url().host());

        String ac = oldRequest.url().encodedQuery();
        RequestBody requestBody = oldRequest.body();


        // 新的请求
        Request newRequest = chain.request().newBuilder()
                    .method(oldRequest.method(), requestBody)
                    .url(authorizedUrlBuilder.build())
                    .build();

        return chain.proceed(newRequest);
    }
}
