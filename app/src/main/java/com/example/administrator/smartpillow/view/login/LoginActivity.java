package com.example.administrator.smartpillow.view.login;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.example.administrator.smartpillow.R;
import com.example.administrator.smartpillow.code.base.view.activity.BaseMvpActivity;
import com.example.administrator.smartpillow.presenter.interfaces.login.IloginView;
import com.example.administrator.smartpillow.presenter.login.LoginPresenter;
import com.example.administrator.smartpillow.utils.other.CountdownUtils;

public class LoginActivity extends BaseMvpActivity<IloginView,LoginPresenter> implements IloginView{


    public static void startUI(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }

    @Override
    public LoginPresenter initPressenter() {
        return new LoginPresenter();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        presenter.register("这是mobile","这是code","这是密码","这是旧密码",12306);
    }

    @Override
    public void parseIntent(Intent intent) {

    }

    @Override
    public void getVerification(boolean whetherVerification) {
        if (whetherVerification) {
            CountdownUtils.recordTime((TextView) findViewById(R.id.verification));
        }
    }

    @Override
    public void login(boolean isSucceed) {

    }

    @Override
    public void bindMobile() {

    }

    @Override
    public void register() {

    }

    @Override
    public void clearData() {

    }

}
