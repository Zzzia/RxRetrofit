package com.zia.rxretrofit.encapsulation.service;

import com.zia.rxretrofit.encapsulation.model.Exam;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created By zia on 2018/8/6.
 * 考试成绩查询
 */
public interface RedRockService {
    @FormUrlEncoded
    @POST(Const.API_EXAM_SCHEDULE)
    Observable<Exam.ExamWrapper> getExam(@Field("stuNum") String stu);

    @FormUrlEncoded
    @POST(Const.API_REEXAM_SCHEDULE)
    Observable<Exam.ExamWrapper> getReExam(@Field("stu") String stu);
}
