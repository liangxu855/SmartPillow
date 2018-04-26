package com.example.administrator.smartpillow.presenter.interfaces.login;


import com.example.administrator.smartpillow.code.base.view.iview.BaseView;
import com.example.administrator.smartpillow.model.UserInfo;

/**
 * Created by yang on 2016/8/17.
 */
public interface IRegisterView extends BaseView {
    /**
     * 获取验证码

     * @param whetherVerification
     */
    void isVerification(boolean whetherVerification);

    /**
     * 注册成功后接收UserData对象
     *
     * @param
     */
    void receiverUserData(UserInfo UserData);
}
