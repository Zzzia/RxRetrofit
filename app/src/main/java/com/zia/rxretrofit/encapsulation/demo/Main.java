package com.zia.rxretrofit.encapsulation.demo;

import com.zia.rxretrofit.encapsulation.repository.StudentRes;

import java.util.Scanner;

import io.reactivex.disposables.Disposable;

/**
 * Created By zia on 2018/8/6.
 * 封装测试
 */
public class Main {
    public static void main(String[] args) {
        Disposable disposable = null;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("输入学号查询信息，0结束");
            String id = scanner.nextLine();
            if (id.equals("0")) {
                if (disposable != null)//注意内存泄漏，这里当然不会发生
                    disposable.dispose();
                break;
            } else {
                disposable = StudentRes.getInstance().getStudent(id).subscribe(System.out::println);
            }
        }
    }
}
