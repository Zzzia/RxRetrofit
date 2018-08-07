package com.zia.rxretrofit.encapsulation.demo.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RxJavaBaseDemo {
    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                observableEmitter.onNext("123");
                observableEmitter.onNext("456");
                observableEmitter.onComplete();
            }
        })
//                .observeOn(AndroidSchedulers.mainThread())//这个只能在sdk里用
                .subscribe(new Observer<String>() {

                    private Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable disposable) {
                        this.disposable = disposable;
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
                        if (s.equals("xxxx")) {
                            disposable.dispose();
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("complete!");
                    }
                });
    }
}
