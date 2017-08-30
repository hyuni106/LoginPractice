package com.example.user.loginpractice;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.loginpractice.data.User;
import com.example.user.loginpractice.util.ContextUtil;
import com.example.user.loginpractice.util.GlobalData;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeResponseCallback;
import com.kakao.usermgmt.response.model.UserProfile;
import com.kakao.util.exception.KakaoException;

public class LoginActivity extends BaseActivity {

    private android.widget.EditText inputIdTxt;
    private android.widget.EditText inputPwTxt;
    private android.widget.Button loginBtn;

    CallbackManager callbackManager;
    private com.facebook.login.widget.LoginButton fbLoginBtn;

    SessionCallback callback;

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
        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        Session.getCurrentSession().checkAndImplicitOpen();

//        로그인 처리가 완료되면 우리 앱에서도 반영하기 위해 콜백을 만들어 등록하는 과정
        callbackManager = CallbackManager.Factory.create();
        fbLoginBtn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

//        프로필 > 내가 누구인지 나타내는 정보 / 트래커 > 추적기
//        ProfileTracker > 접속한 사용자가 바뀌는 상황을 감지
//        새로 로그인 / 로그아웃시에 동작
        ProfileTracker pt = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if (currentProfile == null) {
//                    로그아웃
                    Toast.makeText(mContext, "로그아웃 처리 되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
//                    로그인
                    Toast.makeText(mContext, currentProfile.getName() + "님 접속", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent();
//                    intent.setAction(Intent.ACTION_VIEW);
//                    intent.setData(currentProfile.getLinkUri());
//                    startActivity(intent);
                    ContextUtil.setLoginUser(mContext, currentProfile.getId(), "없음", currentProfile.getName(), currentProfile.getProfilePictureUri(500,500).toString());
                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        inputIdTxt.setText(ContextUtil.getLoginUserId(mContext));
        inputPwTxt.setText(ContextUtil.getLoginUserPw(mContext));
    }

//    페이스북 로그인 화면을 갔다가 돌아오면 콜백매니저가 자동으로 처리할 수 있도록 코딩
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void bindViews() {
        this.fbLoginBtn = (LoginButton) findViewById(R.id.fbLoginBtn);
        this.loginBtn = (Button) findViewById(R.id.loginBtn);
        this.inputPwTxt = (EditText) findViewById(R.id.inputPwTxt);
        this.inputIdTxt = (EditText) findViewById(R.id.inputIdTxt);
    }

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            Toast.makeText(mContext, "로그인 성공", Toast.LENGTH_SHORT).show();
            UserManagement.requestMe(new MeResponseCallback() {
                @Override
                public void onSessionClosed(ErrorResult errorResult) {

                }

                @Override
                public void onNotSignedUp() {

                }

                @Override
                public void onSuccess(UserProfile result) {
                    ContextUtil.setLoginUser(mContext, result.getId() + "", "없음", result.getNickname(), result.getProfileImagePath());
//                    Log.d("로그", ContextUtil.getLoginUserName(mContext));
                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            exception.printStackTrace();
        }
    }
}
