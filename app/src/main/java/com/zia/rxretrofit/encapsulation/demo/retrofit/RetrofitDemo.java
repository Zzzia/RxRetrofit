package com.zia.rxretrofit.encapsulation.demo.retrofit;

import retrofit2.Retrofit;

import java.io.IOException;

/**
 * 基础用法demo
 */
public class RetrofitDemo {

    public static void main(String[] args) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://zzzia.net:8080/api/")
                .build();

        Api api = retrofit.create(Api.class);

        String result = api.getById(2016211541).execute().body().string();

        System.out.println(result);
    }
}
