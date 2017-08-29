package com.example.user.loginpractice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.user.loginpractice.util.ContextUtil;

public class MainActivity extends BaseActivity {

    private android.widget.TextView idTxt;
    private android.widget.TextView pwTxt;
    private android.widget.TextView nameTxt;
    private android.widget.Button logoutBtn;
    private android.widget.ImageView profileImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContextUtil.logoutProcess(mContext);
                Toast.makeText(mContext, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void setValues() {
        idTxt.setText(ContextUtil.getLoginUserId(mContext));
        pwTxt.setText(ContextUtil.getLoginUserPw(mContext));
        nameTxt.setText(ContextUtil.getLoginUserName(mContext));
        Glide.with(mContext).load(ContextUtil.getLoginUserUrl(mContext)).into(profileImg);
    }

    @Override
    public void bindViews() {
        this.logoutBtn = (Button) findViewById(R.id.logoutBtn);
        this.nameTxt = (TextView) findViewById(R.id.nameTxt);
        this.pwTxt = (TextView) findViewById(R.id.pwTxt);
        this.idTxt = (TextView) findViewById(R.id.idTxt);
        this.profileImg = (ImageView) findViewById(R.id.profileImg);
    }
}
