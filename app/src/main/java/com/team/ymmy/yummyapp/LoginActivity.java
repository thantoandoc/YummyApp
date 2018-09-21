package com.team.ymmy.yummyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUserName,mPassWord;
    private Button mLogin;
    private CheckBox mRemember;
    private TextView mForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mapViews();

        handleEvents();

    }

    private void handleEvents() {
        mLogin.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                loginEvent();
        }
    }
    private void loginEvent() {
        String vUserName = mUserName.getText().toString();
        String vPassWord = mPassWord.getText().toString();
        if(vUserName.trim().equals("")||vPassWord.trim().equals("")){
            Toast.makeText(LoginActivity.this,"input please!", Toast.LENGTH_SHORT).show();
        }else {
            // xu li
        }
    }
    private void mapViews() {
        mUserName=(EditText)findViewById(R.id.edt_username);
        mPassWord=(EditText)findViewById(R.id.edt_password);
        mLogin = (Button)findViewById(R.id.btn_login);
        mRemember=(CheckBox)findViewById(R.id.chk_remember);
        mForgotPassword=(TextView)findViewById(R.id.txt_forgot_password);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
