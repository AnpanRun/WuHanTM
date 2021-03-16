package com.lzh.wuhantm.sjms;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 懒汉模式单例
 * 该模式的特点是类加载时没有生成单例，只有当第一次调用 getlnstance 方法时才去创建这个单例
 * Created by LiuBo on 2021/3/9.
 */
public class LazySingleton {
    //volatile 保证可见性   保证instance在线程中同步
    private static volatile LazySingleton instance = null;

    private LazySingleton() {

    }

    private static synchronized LazySingleton getInstance() {
        //getInstance方法前加同步
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}


