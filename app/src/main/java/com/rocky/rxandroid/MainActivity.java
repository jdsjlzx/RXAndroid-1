package com.rocky.rxandroid;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.AbsListView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.rocky.rxandroid.model.App;
import com.rocky.rxandroid.model.Apps;
import com.rocky.rxandroid.network.NetWork;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.listView)
    PullToRefreshListView listView;
    private CustomAdapter adapter;
    private List<App> appLists;
    private int page = 1;
    private Subscription subscription;
    private Handler handler = new Handler();
    Observer<Apps> observer = new Observer<Apps>() {
        @Override
        public void onCompleted() {
            listView.onRefreshComplete();
        }

        @Override
        public void onError(Throwable e) {
            listView.onRefreshComplete();
        }

        @Override
        public void onNext(Apps appsList) {
            listView.onRefreshComplete();
            appLists.addAll(appsList.apps);
            adapter.notifyDataSetChanged();
        }
    };

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        appLists = new ArrayList<>();
        adapter = new CustomAdapter(this, appLists);
        listView.setAdapter(adapter);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                if (listView.isHeaderShown()) {
                    setRefresh();
                } else if (listView.isFooterShown()) {
                    addPersonRefresh();
                }
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount + 5 == totalItemCount) {
                    addPersonRefresh();
                }
            }
        });

    }

    private void addPersonRefresh() {
        setPullToRefreshSetting2();
        listView.setRefreshing(true);
        page += 1;
        initData(page);
    }

    private void setRefresh() {
        setPullToRefreshSetting1();
        listView.setRefreshing(true);
        appLists.clear();
        page = 1;
        initData(page);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                listView.setRefreshing(true);
                initData(page);
            }
        }, 2000);
    }

    private void initData(int page) {
        subscription = NetWork.getAppListApi()
                .getApps(page)
                .subscribeOn(Schedulers.io())//i/o操作 无上限的线程，安全，减少不必要的线程创建，io线程
                .observeOn(AndroidSchedulers.mainThread())//指定线程执行，在主线程执行回调
                .subscribe(observer);
    }

    //下拉头部显示设置
    private void setPullToRefreshSetting1() {
        ILoadingLayout layoutProxy = listView.getLoadingLayoutProxy();
        layoutProxy.setPullLabel("下拉刷新");
        layoutProxy.setReleaseLabel("开始刷新");
        layoutProxy.setRefreshingLabel("正在刷新");
    }

    //上拉底部显示设置
    private void setPullToRefreshSetting2() {
        ILoadingLayout layoutProxy = listView.getLoadingLayoutProxy();
        layoutProxy.setPullLabel("上拉加载");
        layoutProxy.setReleaseLabel("开始加载");
        layoutProxy.setRefreshingLabel("正在加载");
    }
}
