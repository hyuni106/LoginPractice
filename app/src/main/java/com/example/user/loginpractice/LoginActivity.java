package com.example.user.loginpractice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.loginpractice.data.User;
import com.example.user.loginpractice.util.ContextUtil;
import com.example.user.loginpractice.util.GlobalData;

public class LoginActivity extends BaseActivity {

    private android.widget.EditText inputIdTxt;
    private android.widget.EditText inputPwTxt;
    private android.widget.Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean loginOk = false;
                for (User user : GlobalData.allUsers) {
                    if (inputIdTxt.getText().toString().equals(user.getId())) {
                        loginOk = true;
                        if (inputPwTxt.getText().toString().equals(user.getPassword())) {
                            ContextUtil.setLoginUser(mContext, user.getId(), user.getPassword(), user.getName(), user.getProfileURL());
                            Toast.makeText(mContext, "로그인 성공", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(mContext, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(mContext, "비밀번호가 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                
                if (!loginOk) {
                    Toast.makeText(mContext, "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void setValues() {
        inputIdTxt.setText(ContextUtil.getLoginUserId(mContext));
        inputPwTxt.setText(ContextUtil.getLoginUserPw(mContext));
    }

    @Override
    public void bindViews() {
        this.loginBtn = (Button) findViewById(R.id.loginBtn);
        this.inputPwTxt = (EditText) findViewById(R.id.inputPwTxt);
        this.inputIdTxt = (EditText) findViewById(R.id.inputIdTxt);
    }
}
