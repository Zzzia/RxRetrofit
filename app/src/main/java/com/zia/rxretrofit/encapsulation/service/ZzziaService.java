package com.zia.rxretrofit.encapsulation.service;

import com.zia.rxretrofit.encapsulation.model.Student;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ZzziaService {

    @GET(Const.API_STUDENT_INFO)
    Observable<Student.StudentListWrapper> getStudentById(@Path("id") String id);

}