package com.roy.service.impl;

import com.roy.service.MyService;

/**
 * description：
 * author：dingyawu
 * date：created in 18:36 2020/11/21
 * history:
 */
public class MyServiceB implements MyService {
    @Override
    public void print(String info) {
        System.out.println(MyServiceB.class.getName() + " print " + info);
    }
}