package com.voyager.nearbystores_v2.activities.otppagesubmit.model;


/**
 * Created by User on 15-Sep-17.
 */

public class OTPModel implements IOTPModel {
    String optNo;
    Boolean checkTermsAndConductionBox;
    int success = 0;
    String session_id = "";

    public OTPModel(String optNo, Boolean checkTermsAndConductionBox) {
        this.optNo = optNo;
        this.checkTermsAndConductionBox = checkTermsAndConductionBox;
    }

    public int getSuccess() {
        return success;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getOptNo() {
        return optNo;
    }

    public void setOptNo(String optNo) {
        this.optNo = optNo;
    }

    public Boolean getCheckTermsAndConductionBox() {
        return checkTermsAndConductionBox;
    }

    public void setCheckTermsAndConductionBox(Boolean checkTermsAndConductionBox) {
        this.checkTermsAndConductionBox = checkTermsAndConductionBox;
    }

    @Override
    public int validateRegisterResponseError(int success) {
        if(success!=0){
            //if there is no error message then it means that data response is correct.
            return -9;
        }
        return 0;
    }

    @Override
    public int validateCheckBoxAndOtp(String optNumber) {
        if (optNumber.trim().length()==0){
            return -1;
        }
        /*if(checkTermsAndConductionBox==false){
            return -2;
        }*/
        return 0;
    }
}