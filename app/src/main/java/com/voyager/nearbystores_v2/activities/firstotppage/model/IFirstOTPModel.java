package com.voyager.nearbystores_v2.activities.firstotppage.model;

/**
 * Created by User on 15-Sep-17.
 */

public interface IFirstOTPModel {
    int validateFirstOTPpage(String contry, String zipCode, String phno);
    int validateRegisterResponseError(int success);

}
