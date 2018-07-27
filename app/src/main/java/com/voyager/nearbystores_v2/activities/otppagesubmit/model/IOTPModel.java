package com.voyager.nearbystores_v2.activities.otppagesubmit.model;

/**
 * Created by User on 15-Sep-17.
 */

public interface IOTPModel {
    int validateCheckBoxAndOtp(String optNumber);
    int validateRegisterResponseError(int success);
}
