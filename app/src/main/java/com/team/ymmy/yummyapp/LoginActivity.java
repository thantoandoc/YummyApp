package com.team.ymmy.yummyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    EditText mUserName,mPassWord;
    Button  mLogin;
    CheckBox mRemeber;
    TextView mForgot;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        vAnhXa();
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLiSuKien();
            }
        });

    }

    private void xuLiSuKien() {
        String  vUserName=mUserName.getText().toString();
        String  vPassWord=mPassWord.getText().toString();
        if(vUserName.equals(" ")||vPassWord.equals("")){
            Toast.makeText(LoginActivity.this,"INPUT PLEASE", Toast.LENGTH_LONG).show();

        }
    }

    private void vAnhXa() {
        mUserName=(EditText)findViewById(R.id.edt_username);
        mPassWord=(EditText)findViewById(R.id.edt_password);
        mLogin=(Button)findViewById(R.id.btn_login);
        mRemeber=(CheckBox)findViewById(R.id.chk_remenber);
        mForgot=(TextView)findViewById(R.id.txt_forgot);
    }
}
