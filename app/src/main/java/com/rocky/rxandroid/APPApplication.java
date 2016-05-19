package com.rocky.rxandroid;

import android.app.Application;

/**
 * 项目名称：RXAndroid
 * 类描述：
 * 创建人：y7un
 * 创建时间：2016/5/19 9:31
 * 修改人：y7un
 * 修改时间：2016/5/19 9:31
 * 修改备注：
 */
public class APPApplication extends Application {
    private static APPApplication instance;

    public static APPApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
