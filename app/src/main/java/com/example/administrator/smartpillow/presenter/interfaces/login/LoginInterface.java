package com.example.administrator.smartpillow.presenter.interfaces.login;

import com.example.administrator.smartpillow.code.base.view.iview.BaseView;

public interface LoginInterface {

    interface IloginView extends BaseView {
        /**
         * 登录成功后接收UserData对象
         *
         * @param
         */
        void login(boolean isSucceed);

    }
}
