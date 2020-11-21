package com.roy;

import com.roy.service.MyService;
import com.roy.utils.MyServiceLoader;
import org.junit.jupiter.api.Test;

import java.util.ServiceLoader;
import java.util.stream.StreamSupport;

/**
 * description：
 * author：dingyawu
 * date：created in 18:33 2020/11/21
 * history:
 */
public class Test11 {

    public static void main(String[] args){
        ServiceLoader<MyService> loader = MyServiceLoader.loadAll(MyService.class);
        StreamSupport.stream(loader.spliterator(), false).forEach(s -> s.print("Hello World"));
    }
}