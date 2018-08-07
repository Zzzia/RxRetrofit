package com.zia.rxretrofit.encapsulation.demo.retrofit;

import com.zia.rxretrofit.encapsulation.demo.retrofit.custom.StudentBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {
    //zzzia.net:8080/api/id=2016211541
    @GET("id={id}")
    Call<ResponseBody> getById(@Path("id") int id);

    //使用MyConverter，用gson反序列化
    @GET("id={id}")
    Call<StudentBean> getByIdWithMyConverterGson(@Path("id") int id);

    //使用MyConverter，转换成string类型
    @GET("id={id}")
    Call<String> getByIdWithMyConverterString(@Path("id") int id);

    @GET("id={id}")
    Observable<String> getStringObservableById(@Path("id") int id);

    @GET("id={id}")
    Observable<StudentBean> getBeanObservableById(@Path("id") int id);
}