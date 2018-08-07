package com.zia.rxretrofit.encapsulation.demo.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created By zia on 2018/8/6.
 */

public class RxJava_Map {

    private static boolean hasCache = false;

    public static void main(String[] args) {
        Observable a = Observable.create(observableEmitter -> {
//            if (....)i
            hasCache = true;
        });
        Observable b = Observable.create(observableEmitter -> {
            if (!hasCache){
                return;
            }else{

            }
        });

        Observable.concat(a,b)
                .subscribe(System.out::println);
    }
}
