package com.example.administrator.smartpillow.view.common.factory;

import android.util.SparseArray;

import com.example.administrator.smartpillow.code.base.view.fragment.BaseFragment;
import com.example.administrator.smartpillow.view.chat.fragment.ChatFragment;
import com.example.administrator.smartpillow.view.community.fragment.CommunitylFragment;
import com.example.administrator.smartpillow.view.home.fragment.HomeFragment;
import com.example.administrator.smartpillow.view.personal.fragment.PersonalFragment;

public class FragmentFactory {
    private static SparseArray<BaseFragment> mFragments = new SparseArray<BaseFragment>();
    public static BaseFragment createFragment(int position){
        BaseFragment fragment = mFragments.get(position);
        if (fragment == null) {
            switch (position) {
                case 0:
                    //主页
                    fragment = new HomeFragment();
                    break;
                case 1:
                    //群聊
                    fragment = new ChatFragment();
                    break;
                case 2:
                    //社区
                    fragment = new CommunitylFragment();
                    break;
                case 3:
                    //个人
                    fragment = new PersonalFragment();
                    break;
            }

            if (fragment != null) {
                mFragments.put(position, fragment);
            }
        }

        return fragment;
    }
    
}
