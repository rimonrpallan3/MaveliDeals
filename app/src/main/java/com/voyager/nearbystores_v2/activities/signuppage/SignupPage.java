package com.voyager.nearbystores_v2.activities.signuppage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.voyager.nearbystores_v2.R;
import com.voyager.nearbystores_v2.activities.login.view.ILoginView;
import com.voyager.nearbystores_v2.activities.signuppage.presenter.ISignupPresenter;
import com.voyager.nearbystores_v2.activities.signuppage.presenter.SignupPresenter;
import com.voyager.nearbystores_v2.activities.signuppage.view.ISignupView;
import com.voyager.nearbystores_v2.common.Helper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 19-Jul-18.
 */

public class SignupPage extends AppCompatActivity implements View.OnClickListener,ISignupView {

    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;
    @BindView(R.id.ibClose)
    ImageButton ibClose;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etUserName)
    EditText etUserName;
    @BindView(R.id.etMobileNo)
    EditText etMobileNo;
    @BindView(R.id.etPswd)
    EditText etPswd;
    @BindView(R.id.etConfirmPswd)
    EditText etConfirmPswd;
    @BindView(R.id.checkTermsAndConductionBox)
    CheckBox checkTermsAndConductionBox;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.tvTermsAndCond)
    TextView tvTermsAndCond;
    ISignupPresenter iSignupPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        ButterKnife.bind(this);
        sharedPrefs = getSharedPreferences(Helper.UserDetails,
                Context.MODE_PRIVATE);
        editor = sharedPrefs.edit();

        btnRegister.setOnClickListener(this);
        ibClose.setOnClickListener(this);
        iSignupPresenter = new SignupPresenter(this,this);
        iSignupPresenter.setTermCondMsg(tvTermsAndCond);
        //find view


        //init

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                Toast.makeText(this, "Please wait for this feather.", Toast.LENGTH_LONG).show();

                break;
            case R.id.ibClose:
                finish();
                break;

        }
    }

    @Override
    public void moveToTermsAndConductionPage() {
        Toast.makeText(this, "Document is on processing, so please continue to use our service with out any rules.", Toast.LENGTH_LONG).show();
    }
}
