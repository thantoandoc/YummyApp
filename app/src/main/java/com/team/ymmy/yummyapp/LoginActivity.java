package com.team.ymmy.yummyapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.os.PatternMatcher;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.constraint.Constraints;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;
import com.team.ymmy.interfaces.InternetConnection;

import java.util.Locale;
import java.util.TimerTask;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUserName,mPassWord;
    private Button mLogin;
    private CheckBox mRemember;
    private TextView mForgotPassword;
    private FirebaseAuth mAuth;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private ProgressBar progressBar;

    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";
    private static final String CHECKBOX_STATE = "CHECKBOX_STATE";

    private final static String MY_LANGUAGE = "MY_LANGUAGE";
    private final static String MY_LANGUAGE_FILE = "MY_LANGUAGE_FILE";
    private SharedPreferences sharedPreferences;


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("onStart");
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(LoginActivity.this, TableActivity.class);
            startActivity(intent);
            finish();
        }
        sharedPreferences = getSharedPreferences(MY_LANGUAGE_FILE, MODE_PRIVATE);
        String language = sharedPreferences.getString(MY_LANGUAGE, "en");
        changeLanguage(language);
    }
    private void changeLanguage(String language) {
        System.out.println(language);
        changeLanguageOnSystem(language);
        mapViews();
        initReferences();
        handleEvents();
    }

    private void changeLanguageOnSystem(String language) {
        Locale locale = new Locale(language);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration configuration = res.getConfiguration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, dm);
    }
    private void showSnack() {
        Snackbar snackbar = Snackbar.make( findViewById(R.id.layout_login) , R.string.nE, Snackbar.LENGTH_SHORT);
        snackbar.getView().setBackgroundResource(R.color.color_btn_choose);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) snackbar.getView().getLayoutParams();
        layoutParams.setMargins(0,0,0,0);
        snackbar.getView().setLayoutParams(layoutParams);
        snackbar.show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mapViews();
        initReferences();
        handleEvents();
    }



    private void handleEvents() {
        mLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:{
                if(new InternetConnection(this).isNetworkConnected()){
                    loginEvent();
                }else {
                    showSnack();
                }
            }
        }
    }
    private void loginEvent() {
        String userName = mUserName.getText().toString().trim();
        String passWord = mPassWord.getText().toString().trim();
        if(userName.isEmpty()) {
            mUserName.setError(getResources().getString(R.string.email_require));
            mUserName.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(userName).matches()) {
            mUserName.setError(getResources().getString(R.string.email_invalid));
            mUserName.requestFocus();
            return;
        }

        if(passWord.isEmpty()) {
            mPassWord.setError(getResources().getString(R.string.password_require));
            mPassWord.requestFocus();
            return;
        }
        if(passWord.length() < 6) {
            mPassWord.setError(getResources().getString(R.string.password_require_length));
            mPassWord.requestFocus();
            return;
        }
        if(mRemember.isChecked()) {
            mEditor.putString(USERNAME, userName);
            mEditor.putString(PASSWORD, passWord);
            mEditor.putBoolean(CHECKBOX_STATE, mRemember.isChecked());
            mEditor.commit();
        }
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(userName, passWord).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.action_login_success), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, TableActivity.class);
                    progressBar.setVisibility(View.INVISIBLE);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.action_login_fail), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
    private void initReferences() {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        mEditor = mSharedPreferences.edit();

        String username = mSharedPreferences.getString(USERNAME, "");
        String password = mSharedPreferences.getString(PASSWORD, "");
        boolean stateCheckBox = mSharedPreferences.getBoolean(CHECKBOX_STATE, false);
        mUserName.setText(username);
        mPassWord.setText(password);
        mRemember.setChecked(stateCheckBox);
    }
    private void mapViews() {
        mUserName=(EditText)findViewById(R.id.edt_username);
        mPassWord=(EditText)findViewById(R.id.edt_password);
        mLogin = (Button)findViewById(R.id.btn_login);
        mRemember=(CheckBox)findViewById(R.id.chk_remember);
        mForgotPassword=(TextView)findViewById(R.id.txt_forgot_password);
        progressBar = findViewById(R.id.progressbar_login);
        progressBar.getIndeterminateDrawable().setColorFilter(0xf3bfedff, PorterDuff.Mode.SRC_ATOP);

        mAuth = FirebaseAuth.getInstance();
    }
}
