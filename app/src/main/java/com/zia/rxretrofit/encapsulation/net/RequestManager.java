package com.zia.rxretrofit.encapsulation.net;

import com.zia.rxretrofit.encapsulation.model.Exam;
import com.zia.rxretrofit.encapsulation.model.Student;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created By zia on 2018/8/6.
 * 请求封装
 */
public class RequestManager {
    public Observable<List<Student>> getStudent(String id) {
        Observable<List<Student>> observable = RetrofitManager
                .getInstance()
                .zzzia
                .getStudentById(id)
                .map(Student.StudentListWrapper::getData);
        return defaultSchedulers(observable);
    }

    public Observable<List<Exam>> getExam(String stuId) {
        return RetrofitManager.getInstance()
                .redRock
                .getExam(stuId)
                .map(Exam.ExamWrapper::getData)
                .compose(getSchedulers());
    }

    //线程调度
    private <T> ObservableTransformer<T, T> getSchedulers() {
        return o -> {
            o.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())//这个只能在sdk里用
                    .unsubscribeOn(Schedulers.io());
            return o;
        };
    }

    //线程调度 也可以像上面一样写在compose里，更加优雅方便
    private <T> Observable<T> defaultSchedulers(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//这个只能在sdk里用
                .unsubscribeOn(Schedulers.io());
    }
}
