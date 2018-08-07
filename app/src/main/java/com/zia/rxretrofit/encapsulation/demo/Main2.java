package com.zia.rxretrofit.encapsulation.demo;

import com.zia.rxretrofit.encapsulation.net.RequestManager;

import io.reactivex.disposables.Disposable;

/**
 * Created By zia on 2018/8/6.
 */
public class Main2 {

    private static Disposable disposable;

    public static void main(String[] args) throws InterruptedException {
        RequestManager requestManager = new RequestManager();
        disposable = requestManager.getExam("2016211541").subscribe(System.out::println);

        int i = 0;
        while (true) {
            Thread.sleep(1000);
            if (i++ > 2) {
                System.out.println("停止接收数据");
                onDestroy();
                break;
            }
        }
    }

    static void onDestroy() {
        disposable.dispose();
    }
}
