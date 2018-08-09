package com.voyager.nearbystores_v2.activities.login.view;

import com.voyager.nearbystores_v2.activities.firstotppage.model.CountryDetails;
import com.voyager.nearbystores_v2.classes.UserDetails;

import java.util.List;

/**
 * Created by User on 19-Jul-18.
 */

public interface ILoginView {
    void onClearText();
    void setLoader(int visibility);
    void onLoginResult(Boolean result, int code);
    void onLoginResponse(Boolean result, int code);
    void sendPParcelableObj(UserDetails userDetails);
    public void getCountryDetailList(List<CountryDetails> countryDetailsList);
}
