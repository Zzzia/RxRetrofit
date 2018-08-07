package com.zia.rxretrofit.encapsulation.repository;

import com.zia.rxretrofit.encapsulation.model.Student;
import com.zia.rxretrofit.encapsulation.net.RequestManager;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created By zia on 2018/8/6.
 * Student管理仓库，单例（懒得写全局缓存了），三级缓存
 */
public class StudentRes {

    private RequestManager requestManager;
    private HashMap<String, List<Student>> memoryCache;//内存缓存，这里应该用类似lru的缓存，我懒
    private HashMap<String, List<Student>> database;//数据库缓存，这里用hashMap代替

    private StudentRes() {
        requestManager = new RequestManager();
        memoryCache = new HashMap<>();
        database = new HashMap<>();
    }

    private static class StudentResHolder {
        private final static StudentRes instance = new StudentRes();
    }

    public static StudentRes getInstance() {
        return StudentResHolder.instance;
    }

    public void getStudent(String id, Observer<List<Student>> observer) {
        if (memoryCache.get(id) != null) {
            System.out.println("从缓存中读取到student");
            Observable.just(memoryCache.get(id)).subscribe(observer);
        } else if (database.get(id) != null) {
            System.out.println("从数据库中读取到student");
            Observable.just(database.get(id)).subscribe(observer);
        } else {
            System.out.println("通过网络请求获得student");
            requestManager
                    .getStudent(id)
                    .map(student -> {//将数据放入缓存中，会有性能影响，可以通过实现一个observer的包装类将缓存操作放到onNext后面来解决。
                        memoryCache.put(id, student);
                        database.put(id, student);
                        return student;
                    })
                    .subscribe(observer);
        }
    }

    public Observable<List<Student>> getStudent(String id) {
        if (memoryCache.get(id) != null) {
            System.out.println("从缓存中读取到student");
            return Observable.just(memoryCache.get(id));
        } else if (database.get(id) != null) {
            System.out.println("从数据库中读取到student");
            return Observable.just(database.get(id));
        } else {
            System.out.println("通过网络请求获得student");
            return requestManager
                    .getStudent(id)
                    .map(student -> {//将数据放入缓存中，会有性能影响，可以通过实现一个observer的包装类将缓存操作放到onNext后面来解决。
                        memoryCache.put(id, student);
                        database.put(id, student);
                        return student;
                    })
                    .subscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }

}
