package com.zia.rxretrofit.encapsulation.demo.rxjava.observer_flow;


import java.util.Observable;
import java.util.Observer;

/**
 * Created By zia on 2018/8/4.
 * 观察者模式举例
 */
public class ObserverDemo {
    public static void main(String[] args) {
        Student xiaoming = new Student();
        Teacher teacher1 = new Teacher("年级主任");
        Teacher teacher2 = new Teacher("小明班主任");
        Teacher teacher3 = new Teacher("小明大物老师");
        xiaoming.addObserver(teacher1);
        xiaoming.addObserver(teacher2);
        xiaoming.addObserver(teacher3);
        xiaoming.getScore("我，小明，大物：59");
    }
}

class Student extends Observable {

    public void getScore(String s) {
        setChanged();
        notifyObservers(s);
    }
}

class Teacher implements Observer {

    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(name + " <- " + arg);
    }
}
