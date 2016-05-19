package com.rocky.rxandroid.network;

import com.rocky.rxandroid.network.api.AppListApi;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 项目名称：RXAndroid
 * 类描述：
 * 创建人：y7un
 * 创建时间：2016/5/19 9:37
 * 修改人：y7un
 * 修改时间：2016/5/19 9:37
 * 修改备注：
 */
public class NetWork {
    private static AppListApi appListApi;
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public static AppListApi getAppListApi() {
        if (appListApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("http://ard.28app.com/app2/")
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            appListApi = retrofit.create(AppListApi.class);
        }
        return appListApi;
    }
}
