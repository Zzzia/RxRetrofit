package com.zia.rxretrofit.encapsulation.demo.retrofit.custom;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

public class MyRxJavaCallAdapterFactory extends CallAdapter.Factory {

    private static final MyRxJavaCallAdapterFactory instance = new MyRxJavaCallAdapterFactory();

    public static MyRxJavaCallAdapterFactory create() {
        return instance;
    }

    @Override
    public CallAdapter<?, ?> get(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (getRawType(type) == Observable.class) {
            return new ObservableCallAdapter(type);
        }
        return null;
    }

    class ObservableCallAdapter<T> implements CallAdapter<T, Observable<T>> {

        private Type type;

        ObservableCallAdapter(Type type) {
            this.type = type;
        }

        @Override
        public Type responseType() {
            return type;
        }

        @Override
        public Observable<T> adapt(Call<T> call) {
            return Observable.create(observableEmitter -> {
                T body = call.execute().body();
                observableEmitter.onNext(body);
                observableEmitter.onComplete();
            });
        }
    }
}
