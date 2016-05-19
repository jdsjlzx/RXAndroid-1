package com.rocky.rxandroid.network.api;

import com.rocky.rxandroid.model.Apps;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface AppListApi {
    //http://ard.28app.com/app2/list.do?pageNum=page
    @GET("list.do")
    Observable<Apps> getApps(@Query("pageNum") int page);
}
