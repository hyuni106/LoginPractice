package com.example.user.loginpractice;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.loginpractice.util.ContextUtil;
import com.example.user.loginpractice.util.GlobalData;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        GlobalData.initGlobalData();
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (!ContextUtil.getLoginUserId(mContext).equals("")) {
                    intent = new Intent(mContext, MainActivity.class);
                } else {
                    intent = new Intent(mContext, LoginActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {

    }
}
