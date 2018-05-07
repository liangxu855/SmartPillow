package com.example.administrator.smartpillow.view.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.administrator.smartpillow.R;
import com.example.administrator.smartpillow.code.base.view.activity.BaseMvpActivity;
import com.example.administrator.smartpillow.presenter.interfaces.login.LoginInterface;
import com.example.administrator.smartpillow.presenter.login.LoginPresenter;

public class LoginActivity extends BaseMvpActivity<LoginInterface.IloginView,LoginPresenter> implements LoginInterface.IloginView {


    public static void startUI(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        context.startActivity(intent);
    }
    public static void startUIForRes(Activity activity,int requestCode){
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        activity.startActivityForResult(intent,requestCode);
    }

    public static void startUIForRes(Fragment fragment, int requestCode){
        Intent intent = new Intent(fragment.getContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        fragment.startActivityForResult(intent,requestCode);
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
        mToolbarTitle.setText(getResources().getString(R.string.login_pwd));
        ivGoBack.setVisibility(View.VISIBLE);
        ivGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_OK);
                onBackPressed();
            }
        });
    }

    @Override
    public void parseIntent(Intent intent) {

    }


    @Override
    public void login(boolean isSucceed) {

    }

    @Override
    public void clearData() {

    }

}
