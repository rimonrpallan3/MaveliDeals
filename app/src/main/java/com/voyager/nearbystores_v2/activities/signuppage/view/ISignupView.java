package com.voyager.nearbystores_v2.activities.signuppage.view;

import com.voyager.nearbystores_v2.classes.UserDetails;

/**
 * Created by User on 20-Jul-18.
 */

public interface ISignupView {
    public void moveToTermsAndConductionPage();
    void onRegister(Boolean result, int code);
    void onRegistered(Boolean result, int code);
    void sendPParcelableObj(UserDetails userDetails);
}
