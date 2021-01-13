// AIDL_Service.aidl
package com.lzh.wuhantm.service;

// Declare any non-default types here with import statements
// 在这里用import语句声明任何非默认类型

//AIDL中支持以下的数据类型
//1. 基本数据类型
//2. String 和CharSequence
//3. List 和 Map ,List和Map 对象的元素必须是AIDL支持的数据类型;
//4. AIDL自动生成的接口（需要导入-import）
//5. 实现android.os.Parcelable 接口的类（需要导入-import)
interface AIDL_Service {
    /**
     * Demonstrates some basic types that you can use as parameters
     * 演示一些可以用作参数的基本类型
     * and return values in AIDL.
     * 并返回AIDL中的值
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    void myAIDL_Service();
}