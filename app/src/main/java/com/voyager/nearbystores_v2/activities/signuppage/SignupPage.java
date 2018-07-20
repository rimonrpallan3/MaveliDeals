package com.voyager.nearbystores_v2.activities.signuppage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.voyager.nearbystores_v2.R;
import com.voyager.nearbystores_v2.activities.login.view.ILoginView;
import com.voyager.nearbystores_v2.common.Helper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 19-Jul-18.
 */

public class SignupPage extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;
    @BindView(R.id.tvSkip)
    TextView tvSkip;
    @BindView(R.id.tvSignUp)
    TextView tvSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        ButterKnife.bind(this);
        sharedPrefs = getSharedPreferences(Helper.UserDetails,
                Context.MODE_PRIVATE);
        editor = sharedPrefs.edit();

        tvSkip.setOnClickListener(this);
        tvSignUp.setOnClickListener(this);
        //find view


        //init

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvSkip:

                break;
            case R.id.tvSignUp:

                Toast.makeText(this, "Plase wait for this feather Sign up", Toast.LENGTH_LONG).show();
                break;

        }
    }
}
