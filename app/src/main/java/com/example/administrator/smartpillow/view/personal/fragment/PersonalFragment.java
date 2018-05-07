package com.example.administrator.smartpillow.view.personal.fragment;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.smartpillow.R;
import com.example.administrator.smartpillow.code.base.view.fragment.BaseFragment;
import com.example.administrator.smartpillow.utils.ui.ToastUtils;
import com.example.administrator.smartpillow.view.login.LoginActivity;
import com.example.administrator.smartpillow.widget.circleImageView.ICircleImageView;


public class PersonalFragment extends BaseFragment implements View.OnClickListener {

    private ICircleImageView ivIcon;
    private TextView tvName;

    @Override
    public int getContentView() {
        return R.layout.fragment_personal;
    }

    @Override
    public void initView() {
        ivIcon = view.findViewById(R.id.iv_icon);
        tvName = view.findViewById(R.id.tv_name);
        view.findViewById(R.id.to_login).setOnClickListener(this);
        view.findViewById(R.id.perfect_information).setOnClickListener(this);
        view.findViewById(R.id.change_password).setOnClickListener(this);
        view.findViewById(R.id.about_us).setOnClickListener(this);
        view.findViewById(R.id.call_us).setOnClickListener(this);
        view.findViewById(R.id.help_and_feedback).setOnClickListener(this);
        view.findViewById(R.id.logout).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.to_login:
                LoginActivity.startUIForRes(this, activity.REQUEST_CODE);
                break;
            case R.id.perfect_information:
                ToastUtils.getToastShort("perfect_information");
                break;
            case R.id.change_password:
                ToastUtils.getToastShort("change_password");
                break;
            case R.id.about_us:
                ToastUtils.getToastShort("about_us");
                break;
            case R.id.call_us:
                ToastUtils.getToastShort("call_us");
                break;
            case R.id.help_and_feedback:
                ToastUtils.getToastShort("help_and_feedback");
                break;
            case R.id.logout:
                ToastUtils.getToastShort("logout");
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case Activity.RESULT_OK:
                ToastUtils.getToastShort("RESULT_OK");
                break;
        }


    }
}
