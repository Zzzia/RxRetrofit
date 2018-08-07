package com.zia.rxretrofit.encapsulation.demo.rxjava.observer_flow;

//import java.util.concurrent.Flow;
//Flow在Android Studio里用不了

/**
 * Created By zia on 2018/8/4.
 * Flow举例，响应式编程
 */
//
//public class FlowDemo {
//    public static void main(String[] args) {
//
//        Flow.Publisher<TeacherFlow> teacherFlow = subscriber -> {
//            subscriber.onNext(new TeacherFlow("小明大物老师"));
//            subscriber.onNext(new TeacherFlow("小明班主任"));
//            subscriber.onNext(new TeacherFlow("年级主任"));
//            subscriber.onComplete();
//        };
//
//        teacherFlow.subscribe(new StudentFlow());
//    }
//}
//
//class StudentFlow implements Flow.Subscriber<TeacherFlow> {
//
//    private boolean[] isKilled = new boolean[1];//disposable
//
//    @Override
//    public void onSubscribe(Flow.Subscription subscription) {
//
//    }
//
//    @Override
//    public void onNext(TeacherFlow item) {
//        if (isKilled[0] != true) {
//            item.giveScore(isKilled, "我，小明，大物：59");
//        }
//    }
//
//    @Override
//    public void onError(Throwable throwable) {
//        throwable.printStackTrace();
//    }
//
//    @Override
//    public void onComplete() {
//        System.out.println("全部通知完了");
//    }
//}
//
//class TeacherFlow {
//
//    private String name;
//
//    public TeacherFlow(String name) {
//        this.name = name;
//    }
//
//    public void giveScore(boolean[] isKilled, String args) {
//        System.out.println(name + " <- " + args);
//        if (name.equals("小明班主任")) {
//            System.out.println();
//            System.out.println("这么点分，不能让年级主任知道");
//            isKilled[0] = true;
//            System.out.println("小明凉了，现在没人知道小明的成绩了");
//            System.out.println();
//        }
//    }
//}

