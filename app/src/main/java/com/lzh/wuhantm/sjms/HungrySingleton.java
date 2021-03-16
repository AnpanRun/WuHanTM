package com.lzh.wuhantm.sjms;

/**
 * Created by LiuBo on 2021/3/9.
 * 该模式的特点是类一旦加载就创建一个单例，保证在调用 getInstance 方法之前单例已经存在了
 */
public class HungrySingleton {

    private static HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {

    }

    private static HungrySingleton getInstance() {
        return instance;
    }
}
