package com.voyager.nearbystores_v2.activities.login.presenter;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by User on 19-Jul-18.
 */

public class LoginPresenter implements ILoginPresenter{

    Context context;
    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;

    public LoginPresenter(Context context, SharedPreferences sharedPrefs, SharedPreferences.Editor editor) {
        this.context = context;
        this.sharedPrefs = sharedPrefs;
        this.editor = editor;
    }
}
