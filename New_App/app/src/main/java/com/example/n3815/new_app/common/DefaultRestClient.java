package com.example.n3815.new_app.common;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by N3815 on 2016-12-28.
 */

public class DefaultRestClient<T> {

    public static String naverClientId = "waD5IgxI5uodt6KYJa1k";//애플리케이션 클라이언트 아이디값";
    public static String naverClientSecret = "UmCcDYruGI";//애플리케이션 클라이언트 시크릿값";

    private T service;

    /**
     * 국회의원 API를 위한 client
     *
     * @param type
     * @return
     */
    public T getAssemblyClient(Class<? extends T> type){
        if(service == null){
            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .method(original.method(), original.body())
                            .build();

                    return chain.proceed(request);
                }
            }).build();

            Retrofit client = new Retrofit.Builder()
                    .baseUrl("http://apis.data.go.kr/9710000/NationalAssemblyInfoService/")  // 기본 URL
                    .client(okHttpClient)   // http 설정
                    .addConverterFactory(SimpleXmlConverterFactory.create())    // XML로 받겠음.
                    .build();
            service = client.create(type);
        }
        return service;
    }

    /**
     * 검색 API를 위한 client
     *
     * @param type
     * @return
     */
    public T getSearchBlogAPIClient(Class<? extends T> type){
        if(service == null){
            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .header("X-Naver-Client-Id",naverClientId)
                            .header("X-Naver-Client-Secret",naverClientSecret)
                            .method(original.method(), original.body())
                            .build();

                    return chain.proceed(request);
                }
            }).build();

            Retrofit client = new Retrofit.Builder()
                    .baseUrl("https://openapi.naver.com/v1/")  // 기본 URL
                    .client(okHttpClient)   // http 설정
                    .addConverterFactory(SimpleXmlConverterFactory.create())    // XML로 받겠음.
                    .build();
            service = client.create(type);
        }
        return service;
    }


}
