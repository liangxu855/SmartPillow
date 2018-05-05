package com.example.administrator.smartpillow.presenter.interfaces.login;


import com.example.administrator.smartpillow.code.base.view.iview.BaseView;

/**
 * Created by yang on 2016/8/17.
 */
public interface IloginView extends BaseView {
    /**
     * 获取验证码
     *
     * @param whetherVerification
     */
    void getVerification(boolean whetherVerification);

    /**
     * 登录成功后接收UserData对象
     *
     * @param
     */
    void login(boolean isSucceed);

    /**
     * 绑定手机号
     */
    void bindMobile();

    /**
     * 注册
     */
    void register();
}
