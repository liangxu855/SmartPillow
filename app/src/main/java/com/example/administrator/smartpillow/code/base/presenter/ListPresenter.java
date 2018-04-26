package com.example.administrator.smartpillow.code.base.presenter;


import com.example.administrator.smartpillow.code.base.view.iview.IListView;
import com.example.administrator.smartpillow.http.httpquest.HttpCallBack;
import com.example.administrator.smartpillow.http.mode.HttpRequest;
import com.example.administrator.smartpillow.model.httpresponse.HttpResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/8.
 */

public class ListPresenter<T> extends BasePresenter<IListView<T>> {
    public void getHttpListData(final boolean isStart) {
        if (isStart) {
            mvpView.clearPagingData();
        }
        HttpCallBack.Builder buider = new HttpCallBack.Builder(this)
                .setShowLoadding(false)
                .setLoadingAndRetryManager(mvpView.getLoadingAndRetryManager())
                .setStatusChangListener(mvpView.getStatusChangListener())
                .setSwipeRefreshLayout(mvpView.getSwipeRefreshLayout());
        final HttpCallBack httpCallBack = new HttpCallBack<HttpResult<List<T>>>(buider) {
            @Override
            public void onSuccess(HttpResult<List<T>> result) {
                super.onSuccess(result);
                //进行data处理
                if (result.isSucceed()) {
                    if (isStart) {
                        mvpView.clearData();
                    }
                    mvpView.upDataUI((List<T>) mvpView.getValidData(result.getData()));
                }
            }
        };
        Map<String, String> map = new HashMap<>();
        addPageData(map);
        mvpView.addSubscription(HttpRequest.executePost(httpCallBack, map));
    }


}
