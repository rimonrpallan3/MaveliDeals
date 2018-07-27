package com.voyager.nearbystores_v2.activities.firstotppage.view;



import com.voyager.nearbystores_v2.activities.firstotppage.model.CountryDetails;

import java.util.List;

/**
 * Created by User on 8/30/2017.
 */

public interface IFirstOTPView {
    public void validatedSendData(Boolean result, int code,String session_id);
    public void getCountryDetailList(List<CountryDetails> countryDetailsList);
}
