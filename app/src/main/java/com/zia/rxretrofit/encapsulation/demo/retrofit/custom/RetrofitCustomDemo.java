package com.zia.rxretrofit.encapsulation.demo.retrofit.custom;

import com.zia.rxretrofit.encapsulation.demo.retrofit.Api;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Retrofit;

/**
 * 使用自定义factory的demo
 */
public class RetrofitCustomDemo {
    public static void main(String[] args) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://zzzia.net:8080/api/")
                .addConverterFactory(MyConverterFactory.create())
                .addCallAdapterFactory(MyRxJavaCallAdapterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        //测试StringConverter
        String string = api.getByIdWithMyConverterString(2016211541).execute().body();
        System.out.println(string);

        //测试GsonConverter
        StudentBean studentBean = api.getByIdWithMyConverterGson(2016211541).execute().body();
        System.out.println(studentBean.getData().get(0).getName());

        //测试MyRxJavaCallAdapterFactory
        api.getBeanObservableById(2016211541).subscribe(new Observer<StudentBean>() {
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(StudentBean studentBean) {
                System.out.println(studentBean);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
