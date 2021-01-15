package com.lzh.wuhantm.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.lzh.common.util.DatabaseHelper;
import com.lzh.common.util.MySQLiteOpenHelper;
import com.lzh.wuhantm.R;

public class SQLiteActivity extends AppCompatActivity implements View.OnClickListener{

    private Button instablish;
    private Button insert;
    private Button upgrade;
    private Button modify;
    private Button delete;
    private Button query;
    private Button delete_database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        //绑定按钮
        instablish = (Button) findViewById(R.id.instablish);
        insert = (Button) findViewById(R.id.insert);
        upgrade = (Button) findViewById(R.id.upgrade);
        modify = (Button) findViewById(R.id.modify);
        delete = (Button) findViewById(R.id.delete);
        query = (Button) findViewById(R.id.query);
        delete_database = (Button) findViewById(R.id.delete_database);

        //设置监听器
        instablish.setOnClickListener(this);
        insert.setOnClickListener(this);
        upgrade.setOnClickListener(this);
        modify.setOnClickListener(this);
        delete.setOnClickListener(this);
        query.setOnClickListener(this);
        delete_database.setOnClickListener(this);

    }

    //设置每个按钮对数据库的操作进行控制
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            //点击创建数据库库
            case R.id.instablish:

                // 创建SQLiteOpenHelper子类对象
                MySQLiteOpenHelper dbHelper = new MySQLiteOpenHelper(this,"test_carson");
                //数据库实际上是没有被创建或者打开的，直到getWritableDatabase() 或者 getReadableDatabase() 方法中的一个被调用时才会进行创建或者打开
                SQLiteDatabase  sqliteDatabase = dbHelper.getWritableDatabase();
                // SQLiteDatabase  sqliteDatabase = dbHelper.getReadbleDatabase();

                break;

            //点击更新数据
            case R.id.upgrade:

                // 创建SQLiteOpenHelper子类对象
                MySQLiteOpenHelper dbHelper_upgrade = new MySQLiteOpenHelper(this,"test_carson",2);
                // 调用getWritableDatabase()方法创建或打开一个可以读的数据库
                SQLiteDatabase  sqliteDatabase_upgrade = dbHelper_upgrade.getWritableDatabase();
                // SQLiteDatabase  sqliteDatabase = dbHelper.getReadbleDatabase();

                break;

            //点击插入数据到数据库
            case R.id.insert:

                System.out.println("插入数据");

                // 创建SQLiteOpenHelper子类对象
                ////注意，一定要传入最新的数据库版本号
                MySQLiteOpenHelper dbHelper1 = new MySQLiteOpenHelper(this,"test_carson",2);
                // 调用getWritableDatabase()方法创建或打开一个可以读的数据库
                SQLiteDatabase  sqliteDatabase1 = dbHelper1.getWritableDatabase();

                // 创建ContentValues对象
                ContentValues values1 = new ContentValues();

                // 向该对象中插入键值对
                values1.put("id", 1);
                values1.put("name", "carson");

                // 调用insert()方法将数据插入到数据库当中
                sqliteDatabase1.insert("user", null, values1);

                // sqliteDatabase.execSQL("insert into user (id,name) values (1,'carson')");

                //关闭数据库
                sqliteDatabase1.close();

                break;

            //点击查询数据库
            case R.id.query:

                System.out.println("查询数据");

                // 创建DatabaseHelper对象
                MySQLiteOpenHelper dbHelper4 = new MySQLiteOpenHelper(SQLiteActivity.this,"test_carson",2);

                // 调用getWritableDatabase()方法创建或打开一个可以读的数据库
                SQLiteDatabase sqliteDatabase4 = dbHelper4.getReadableDatabase();

                // 调用SQLiteDatabase对象的query方法进行查询
                // 返回一个Cursor对象：由数据库查询返回的结果集对象
                Cursor cursor = sqliteDatabase4.query("user", new String[] { "id",
                        "name" }, "id=?", new String[] { "1" }, null, null, null);


                String id = null;
                String name = null;

                //将光标移动到下一行，从而判断该结果集是否还有下一条数据
                //如果有则返回true，没有则返回false
                while (cursor.moveToNext()) {
                    id = cursor.getString(cursor.getColumnIndex("id"));
                    name = cursor.getString(cursor.getColumnIndex("name"));
                    //输出查询结果
                    System.out.println("查询到的数据是:"+"id: "+id+"  "+"name: "+name);

                }
                //关闭数据库
                sqliteDatabase4.close();

                break;


            //点击修改数据
            case R.id.modify:
                System.out.println("修改数据");

                // 创建一个DatabaseHelper对象
                // 将数据库的版本升级为2
                // 传入版本号为2，大于旧版本（1），所以会调用onUpgrade()升级数据库
                MySQLiteOpenHelper dbHelper2 = new MySQLiteOpenHelper(SQLiteActivity.this,"test_carson", 2);


                // 调用getWritableDatabase()得到一个可写的SQLiteDatabase对象
                SQLiteDatabase sqliteDatabase2 = dbHelper2.getWritableDatabase();

                // 创建一个ContentValues对象
                ContentValues values2 = new ContentValues();
                values2.put("name", "zhangsan");

                // 调用update方法修改数据库
                sqliteDatabase2.update("user", values2, "id=?", new String[]{"1"});

                //关闭数据库
                sqliteDatabase2.close();
                break;

            //点击删除数据
            case R.id.delete:

                System.out.println("删除数据");

                // 创建DatabaseHelper对象
                MySQLiteOpenHelper dbHelper3 = new MySQLiteOpenHelper(SQLiteActivity.this,"test_carson",2);

                // 调用getWritableDatabase()方法创建或打开一个可以读的数据库
                SQLiteDatabase sqliteDatabase3 = dbHelper3.getWritableDatabase();

                //删除数据
                sqliteDatabase3.delete("user", "id=?", new String[]{"1"});

                //关闭数据库
                sqliteDatabase3.close();
                break;




            //点击删除数据库
            case R.id.delete_database:

                System.out.println("删除数据库");

                MySQLiteOpenHelper dbHelper5 = new MySQLiteOpenHelper(SQLiteActivity.this,
                        "test_carson",2);

                // 调用getReadableDatabase()方法创建或打开一个可以读的数据库
                SQLiteDatabase sqliteDatabase5 = dbHelper5.getReadableDatabase();

                //删除名为test.db数据库
                deleteDatabase("test_carson");
                break;

            default:
                break;

        }
    }


    //SQLite相关知识
    private void SQLiteTest() {
        // 步骤1：创建DatabaseHelper对象
        // 注：一定要传入最新的数据库版本号
        SQLiteOpenHelper dbHelper = new DatabaseHelper(this, "test_lzh", null, 1);
        // 步骤2：真正创建/打开数据库
        // 创建 or 打开 可读/写的数据库
        // 对于操作 = “增、删、改（更新）”，需获得 可"读 / 写"的权限：getWritableDatabase()
        SQLiteDatabase sqliteDatabase = dbHelper.getWritableDatabase();
        // 创建 or 打开 可读的数据库
        // 对于操作 = “查询”，需获得 可"读 "的权限getReadableDatabase()
        //SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();


        /**
         *  操作1：插入数据 = insert()
         */
        // a. 创建ContentValues对象
        ContentValues values = new ContentValues();

        // b. 向该对象中插入键值对
        values.put("id", 1);
        values.put("name", "lzh");
        //其中，key = 列名，value = 插入的值
        //注：ContentValues内部实现 = HashMap，区别在于：ContenValues Key只能是String类型
        //Value可存储基本类型数据 & String类型

        //c. 插入数据到数据库当中:insert()
        // 参数1：要操作的表名称
        // 参数2：SQl不允许一个空列，若ContentValues是空，那么这一列被明确的指明为NULL值
        // 参数3：ContentValues对象
        sqliteDatabase.insert("user", null, values);
        // 注：也可采用SQL语句插入
        String sql = "insert into user (id,name) values (1,'carson')";
        sqliteDatabase.execSQL(sql);


        /**
         *  操作2 ：修改数据 = update()
         */
        ContentValues values2 = new ContentValues();
        values.put("name", "xx");

        // 参数1：表名(String)
        // 参数2：需修改的ContentValues对象
        // 参数3：WHERE表达式（String），需数据更新的行； 若该参数为 null, 就会修改所有行；？号是占位符
        // 参数4：WHERE选择语句的参数(String[]), 逐个替换 WHERE表达式中 的“？”占位符;

        // 注：调用完upgrate（）后，则会回调 数据库子类的onUpgrade()
        sqliteDatabase.update("user", values, "id=?", new String[]{"1"});

        // 注：也可采用SQL语句修改
        sql = "update [user] set name = 'zhangsan' where id='1'";
        sqliteDatabase.execSQL(sql);


        /**
         *  操作3：删除数据 = delete()
         */
        // 删除 id = 1的数据
        sqliteDatabase.delete("user", "id=?", new String[]{"1"});
        // 参数1：表名(String)
        // 参数2：WHERE表达式（String），需删除数据的行； 若该参数为 null, 就会删除所有行；？号是占位符
        // 参数3：WHERE选择语句的参数(String[]), 逐个替换 WHERE表达式中 的“？”占位符;

        // 注：也可采用SQL语句修改
        sql = "delete from user where id='1'";
        sqliteDatabase.execSQL(sql);


        /**
         *  操作4：查询数据1 = rawQuery()
         *  直接调用 SELECT 语句
         */
        Cursor c = sqliteDatabase.rawQuery("select * from user where id=?", new String[]{"1"});
        // 返回值一个 cursor 对象

        // 通过游标的方法可迭代查询结果
        if (c.moveToFirst()) {
            String password = c.getString(c.getColumnIndex("name"));
        }

        //Cursor对象常用方法如下：
        //c.move(int offset); //以当前位置为参考,移动到指定行
        c.move(1);
        c.moveToFirst();    //移动到第一行
        c.moveToLast();     //移动到最后一行
        //c.moveToPosition( int position); //移动到指定行
        c.moveToPosition(1); //移动到指定行
        c.moveToPrevious(); //移动到前一行
        c.moveToNext();     //移动到下一行
        c.isFirst();        //是否指向第一条
        c.isLast();         //是否指向最后一条
        c.isBeforeFirst();  //是否指向第一条之前
        c.isAfterLast();    //是否指向最后一条之后
        //c.isNull( int columnIndex);  //指定列是否为空(列基数为0)
        c.isNull(1);
        c.isClosed();       //游标是否已关闭
        c.getCount();       //总数据项数
        c.getPosition();    //返回当前游标所指向的行数
        //c.getColumnIndex(String columnName);//返回某列名对应的列索引值
        c.getColumnIndex("name");//返回某列名对应的列索引值
        //c.getString( int columnIndex);   //返回当前行指定列的值
        c.getString(1);   //返回当前行指定列的值

        // 通过游标遍历1个名为user的表
        Cursor result = sqliteDatabase.rawQuery("SELECT _id, username FROM user",null);
        result.moveToFirst();
        while (!result.isAfterLast()) {
            int id = result.getInt(0);
            String name = result.getString(1);
            //String password = result.getString(2);
            // do something useful with these
            result.moveToNext();
        }
        result.close();


        // 若查询是动态的，使用该方法会复杂。此时使用 query() 会方便很多
        // 注：无法使用SQL语句，即db.execSQL(sql);

        /**
         *  操作4：查询数据2 = query()
         *  直接调用 SELECT 语句
         */
        // 方法说明

        // 参数说明
        // table：要操作的表
        // columns：查询的列所有名称集
        // selection：WHERE之后的条件语句，可以使用占位符
        // groupBy：指定分组的列名
        // having指定分组条件，配合groupBy使用
        // orderBy指定排序的列名
        // limit指定分页参数
        // distinct可以指定“true”或“false”表示要不要过滤重复值

        //        db.query(String table, String[]columns, String selection, String[]selectionArgs, String
        //        groupBy, String having, String orderBy);
        //        db.query(String table, String[]columns, String selection, String[]selectionArgs, String
        //        groupBy, String having, String orderBy, String limit);
        //        db.query(String distinct, String table, String[]columns, String selection, String[]
        //        selectionArgs, String groupBy, String having, String orderBy, String limit);




        // 所有方法将返回一个Cursor对象，代表数据集的游标

        // 具体使用
        Cursor cursor = sqliteDatabase.query("user", new String[]{"id", "name"}, "id=?", new String[]{"1"}, null, null, null);
        // 参数1：（String）表名
        // 参数2：（String[]）要查询的列名
        // 参数3：（String）查询条件
        // 参数4：（String[]）查询条件的参数
        // 参数5：（String）对查询的结果进行分组
        // 参数6：（String）对分组的结果进行限制
        // 参数7：（String）对查询的结果进行排序

        // 注：无法使用SQL语句，即db.execSQL(sql);
        /**
         *  操作5：关闭数据库 = close()
         *  注：完成数据库操作后，记得调用close（）关闭数据库，从而释放数据库的连接
         */
        sqliteDatabase.close();

        /**
         *  操作6：删除数据库 = deleteDatabase（）
         */
        // 删除 名为person的数据库
        deleteDatabase("test.db");
    }
}