package com.lzh.wuhantm.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lzh.wuhantm.R;

public class TMThreadActivity extends AppCompatActivity {

    private Button mBtMP;
    private Button mBtAtLoad;
    private Button mBtAtCancel;
    private TextView mTvtest;
    private ProgressBar mPbpb;
    Handler mainHandler, workHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmthread);

        mBtMP = (Button) findViewById(R.id.bt_mp);
        mBtAtLoad = (Button) findViewById(R.id.bt_atload);
        mTvtest = (TextView) findViewById(R.id.tv_test);
        mPbpb = (ProgressBar) findViewById(R.id.pb_pb);
        mBtAtCancel = (Button) findViewById(R.id.bt_atcancel);

        //两个线程卖票
        mBtMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //步骤2：创建线程类的实例
                //创建两个线程，模拟两个窗口卖票
                Mythread mt1 = new Mythread("窗口A");
                Mythread mt2 = new Mythread("窗口B");

                //步骤3：调用start()方法开启线程
                //启动两个线程，也即是窗口，开始卖票
                mt1.start();
                mt2.start();

//                MyRunnableThread mrt = new MyRunnableThread();
//                Thread thread = new Thread(mrt);

                //Runnable方式启动启动线程
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
                thread.start();


                /**
                 * a.必须在UI线程中调用
                 * b.同一个AsyncTask实例对象只能执行1次，若执行第2次将会抛出异常
                 * c.执行任务中，系统会自动调用AsyncTask的一系列方法：onPreExecute() 、doInBackground() 、onProgressUpdate() 、 onPostExecute()
                 * d.不能手动调用上述方法
                 */
                TMAsyncTask tmAsyncTask = new TMAsyncTask();
                tmAsyncTask.execute();
            }
        });
        MyTask myTask = new MyTask();
        //模拟一个进度条加载
        mBtAtLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myTask.execute("go");
            }
        });

        mBtAtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myTask.onCancelled();
            }
        });


        /**
         * HandlerThread的使用
         */

        mainHandler = new Handler();

        HandlerThread mHandlerThread = new HandlerThread("handlerThread");

        mHandlerThread.start();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private class Mythread extends Thread {
        private static final String TAG = "Mythread";
        //一个窗口有100张票
        private int ticket = 100;
        //窗口名，也既是线程的名字
        private String name;

        public Mythread(String name) {
            this.name = name;
        }

        //在run方法里复写需要进行的操作
        @Override
        public void run() {
            while (ticket > 0) {
                ticket--;
                //System.out.println(name + "卖掉了1张票，剩余票数为:" + ticket);
                Log.d(TAG, name + "卖掉了1张票，剩余票数为:" + ticket);//
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //实现Runnable接口
    private class MyRunnableThread implements Runnable {

        @Override
        public void run() {
        }

    }


    /**
     * 2021.1.18
     * 一个讲解类
     */
    private class TMAsyncTask extends AsyncTask<String, Integer, String> {
        public TMAsyncTask() {
            super();
        }

        //方法1：onPreExecute()
        //作用：执行 线程任务前的操作
        //注： 根据需求复写
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        //方法2：doInBackground()
        //作用：接收输入参数、执行任务中的耗时操作、返回 线程任务执行的结果
        //注： 必须复写，从而自定义线程任务
        @Override
        protected String doInBackground(String... strings) {

            //可调用publishProgress()显示进度，之后将执行onProgressUpdate()
            publishProgress(100);
            return null;
        }

        //方法3：onProgressUpdate()
        //作用：在主线程 显示线程任务执行的进度
        //注： 根据需求复写
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        //方法4：onPostExecute()
        //作用：接收线程任务执行结果、将执行结果显示到UI组件
        //注： 必须复写，从而自定义UI操作
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        //方法5：onCancelled()
        //作用：将异步任务设置为：取消状态
        @Override
        protected void onCancelled(String s) {
            super.onCancelled(s);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }


    /**
     * 模拟一个进度条加载
     */
    private class MyTask extends AsyncTask<String, Integer, String> {
        public MyTask() {
            super();
        }

        @Override
        protected void onPreExecute() {
            mTvtest.setText("加载中");
        }

        @Override
        protected void onPostExecute(String s) {
            mTvtest.setText("加载完成");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mPbpb.setProgress(values[0]);
            mTvtest.setText("loading.." + values[0] + "%");
        }

        @Override
        protected void onCancelled(String s) {
            mTvtest.setText("加载取消s");
        }

        @Override
        protected void onCancelled() {
            mTvtest.setText("加载取消");
        }

        @Override
        protected String doInBackground(String... strings) {
            int progress = 0;
            while (progress < 100) {
                progress++;
                publishProgress(progress);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "ok";
        }
    }
}