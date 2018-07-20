package com.voyager.nearbystores_v2.activities.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.voyager.nearbystores_v2.R;
import com.voyager.nearbystores_v2.activities.MainActivity;
import com.voyager.nearbystores_v2.activities.login.presenter.ILoginPresenter;
import com.voyager.nearbystores_v2.activities.login.presenter.LoginPresenter;
import com.voyager.nearbystores_v2.activities.login.view.ILoginView;
import com.voyager.nearbystores_v2.activities.signuppage.SignupPage;
import com.voyager.nearbystores_v2.common.Helper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 19-Jul-18.
 */

public class LoginPage extends AppCompatActivity implements ILoginView ,View.OnClickListener{

    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;
    ILoginPresenter iLoginPresenter;

    @BindView(R.id.tvSkip)
    TextView tvSkip;
    @BindView(R.id.tvSignUp)
    TextView tvSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin_page);
        ButterKnife.bind(this);
        sharedPrefs = getSharedPreferences(Helper.UserDetails,
                Context.MODE_PRIVATE);
        editor = sharedPrefs.edit();

        tvSkip.setOnClickListener(this);
        tvSignUp.setOnClickListener(this);
        //find view


        //init
        iLoginPresenter = new LoginPresenter(this,sharedPrefs,editor);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSkip:
                Intent intent = new Intent(LoginPage.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tvSignUp:
                intent = new Intent(LoginPage.this, SignupPage.class);
                startActivity(intent);
                finish();
                Toast.makeText(this,"Plase wait for this feather Sign up",Toast.LENGTH_LONG).show();
                break;

        }
    }
}
