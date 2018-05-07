package com.example.administrator.smartpillow.presenter.login;


import com.example.administrator.smartpillow.code.base.presenter.BasePresenter;
import com.example.administrator.smartpillow.http.httpquest.HttpCallBack;
import com.example.administrator.smartpillow.http.mode.HttpRequest;
import com.example.administrator.smartpillow.model.httpresponse.HttpResult;
import com.example.administrator.smartpillow.model.login.LoginData;
import com.example.administrator.smartpillow.presenter.interfaces.login.LoginInterface;
import com.example.administrator.smartpillow.utils.other.LogUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 说明: 登录的P类
 */

public class LoginPresenter extends BasePresenter<LoginInterface.IloginView> {
    /**
     * 注册  接口
     * account/register
     * <p>
     * Response body
     * {
     * "body": true,
     * "code": "string",
     * "msg": "string",
     * "success": true
     * }
     *
     * @param accountMobile
     * @param code
     * @param loginPwd
     * @param oldPwd
     * @param userId
     */
    public void register(String accountMobile, String code, String loginPwd, String oldPwd, int userId) {
        HttpCallBack.Builder buider = new HttpCallBack.Builder(this)
                .setShowLoadding(true)
                .setLoadingAndRetryManager(mvpView.getLoadingAndRetryManager());
        HttpCallBack httpCallBack = new HttpCallBack<HttpResult<LoginData>>(buider) {

            @Override
            public void onSuccess(HttpResult<LoginData> result) {
                super.onSuccess(result);
                LogUtils.e(result.toString());
            }

            @Override
            public void onError(Throwable error) {
                super.onError(error);
                LogUtils.e(error.toString());
            }
        };
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("accountMobile",accountMobile);
            jsonObject.put("code",code);
            jsonObject.put("loginPwd",loginPwd);
            jsonObject.put("oldPwd",oldPwd);
            jsonObject.put("userId",userId);
            HttpRequest.executePost(httpCallBack, "account/register", jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
