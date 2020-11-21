package com.roy.utils;

import java.util.ServiceLoader;

/**
 * description：使用SPI机制加载所有的Class，类加载工具
 * author：dingyawu
 * date：created in 18:34 2020/11/21
 * history:
 */
public class MyServiceLoader {

    /**
     * 使用SPI机制加载所有的Class
     */
    public static <S> ServiceLoader<S> loadAll(final Class<S> clazz) {
        return ServiceLoader.load(clazz);
    }
}