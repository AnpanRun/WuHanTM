package com.lzh.common.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * 创建数据库子类，继承SQLiteOpenHelper
 * 需 复写onCreate() onUpgrade()
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    //数据库的版本号
    private static Integer Version = 1;

    /**
     * 构造函数
     * 在SQLiteOpenHelper的子类中，必须有该构造函数
     *
     * @param context 上下文对象
     * @param name    数据库名称
     * @param factory 一个可选的游标工厂（通常是 Null）
     * @param version 当前数据库的版本，值必须是整数并且是递增的状态
     */
    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        // 必须通过super调用父类的构造函数
        super(context, name, factory, version);
    }


    /**
     * 复写onCreate()
     * 调用时刻：当数据库第1次创建时调用
     * 作用：创建数据库 表 & 初始化数据
     * SQLite数据库创建支持的数据类型 ：  整形数据、字符串类型 、 日期类型 、二进制
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库1张表
        //通过execSQL() 执行SQL语句（此处创建了1个名为person的表)
        String sql = "create table person (id integer primary key autoincrement , name varchar(64))";
        db.execSQL(sql);

        // 注：数据库实际上是没被创建 / 打开的（因该方法还没调用）
        // 直到getWritableDatabase() / getReadableDatabase() 第一次被调用时才会进行创建 / 打开
    }

    /**
     * 复写onUpgrade()
     * 调用时刻：当数据库升级时则自动调用（即 数据库版本 发生变化时）
     * 作用：更细数据库表结构
     * 注：创建SQLiteOpenHelper子类对象时，必须传入一个version参数
     * 该参数 = 当前数据库版本，若该版本高于之前版本，就调用onUpgrade()
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 参数说明：
        // db ： 数据库
        // oldVersion ： 旧版本数据库
        // newVersion ： 新版本数据库

        // 使用 SQL的ALTER语句
        String sql = "alter table person add sex varchar(8)";
        db.execSQL(sql);
    }
}
