package com.voyager.nearbystores_v2.activities.otppagesubmit.presenter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.voyager.nearbystores_v2.R;
import com.voyager.nearbystores_v2.activities.otppagesubmit.model.IOTPModel;
import com.voyager.nearbystores_v2.activities.otppagesubmit.model.OTPModel;
import com.voyager.nearbystores_v2.activities.otppagesubmit.view.IOTPView;
import com.voyager.nearbystores_v2.classes.Errors;
import com.voyager.nearbystores_v2.webservices.ApiClient;
import com.voyager.nearbystores_v2.webservices.WebServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * Created by User on 8/30/2017.
 */

public class OTPPresenter implements IOTPControler {

    IOTPView iotpView;
    Context context;
    IOTPModel user;
    String edtOPTNo;
    Boolean checkTermsAndConductionBox;
    String PhoneNo;
    String session_id =  "";


    public OTPPresenter(IOTPView iotpView, Context context,String PhoneNo) {
        this.iotpView = iotpView;
        initUser();
        this.context = context;
        this.PhoneNo = PhoneNo;
    }


    @Override
    public void setOPTSecondMsg(TextView v) {
        SpannableString ss = new SpannableString(context.getString(R.string.signup_term_cond));
        final ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(final View textView) {
                iotpView.moveToTermsAndConductionPage();
            }

            @Override
            public void updateDrawState(final TextPaint textPaint) {
                textPaint.setColor(ContextCompat.getColor(context.getApplicationContext(), R.color.colorHeighLight));
                textPaint.setUnderlineText(true);
            }
        };
        ss.setSpan(clickableSpan,59,76, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        v.setText(ss);
        v.setMovementMethod(LinkMovementMethod.getInstance());
    }


    public void resendOtpPage(final Boolean result, int code){
        Retrofit retrofit = new ApiClient().getRetrofitClient();
        WebServices webServices = retrofit.create(WebServices.class);
        Call<OTPModel> call = webServices.resendOtp(PhoneNo);
        call.enqueue(new Callback<OTPModel>() {
            @Override
            public void onResponse(Call<OTPModel> call, Response<OTPModel> response) {
                OTPModel otpModel  = (OTPModel) response.body();

                Gson gson = new Gson();
                String jsonString = gson.toJson(otpModel);

                System.out.println(" ----------- getFilters OfferListMap "+jsonString);
                if(jsonString!=null) {
                    System.out.println("-----------getFilters OfferList"+jsonString+" userDetails.getSuccess() : "+otpModel.getSuccess());
                }

                final int code =user.validateRegisterResponseError(otpModel.getSuccess());
                System.out.println("--------- validateLoginDataBaseApi code: "+code);
                Boolean isLoginSuccess =true;
                if (code == 0) {
                    isLoginSuccess = false;
                    Errors errors = otpModel.getErrors();
                    System.out.println("--------- validateLoginDataBaseApi isError: "+errors.getConnect());
                    Toast.makeText((Context) iotpView, errors.getConnect(), Toast.LENGTH_SHORT).show();
                    System.out.println("-----validateLoginDataBaseApi  data unSuccess ");
                } else {
                    System.out.println("----- validateLoginDataBaseApi isError: "+otpModel.getSuccess());
                    Toast.makeText((Context) iotpView, "OTP Verified", Toast.LENGTH_SHORT).show();
                    iotpView.resendOtp(result, code,session_id);
                    System.out.println("----- validateLoginDataBaseApi data Successful ");
                }
                Boolean result = isLoginSuccess;
                System.out.println("----- sendRegisteredDataAndValidateResponse second Data Please see, code = " + code + ", result: " + result);
                iotpView.resendOtp(result, code,session_id);
            }

            @Override
            public void onFailure(Call<OTPModel> call, Throwable t) {
                Boolean isLoginSuccess = false;
                Boolean result = isLoginSuccess;
                int code = -77;
                iotpView.onSubmit(result, code);
                System.out.println("----- onFailure second Data Please see, printStackTrace = "+t.getMessage());

                t.printStackTrace();
                //Toast.makeText((Context) iRegisterView, "ErrorMessage"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void resendOtpPage(String PhoneNo) {
        this.PhoneNo = PhoneNo;
        initUser();
        Boolean isLoginSuccess = true;
        final int code = user.validatePhoneNo(PhoneNo);
        if (code!=0) isLoginSuccess = false;
        final Boolean result = isLoginSuccess;
        resendOtpPage(result, code);

    }

    @Override
    public void doOTPValidationAndCheck(String edtOPTNo,String session_id) {
        this.edtOPTNo= edtOPTNo;
        this.session_id = session_id;
        Boolean isLoginSuccess = true;
        final int code = user.validatesSessionKeyAndOtp(edtOPTNo,session_id);
        if (code!=0) {
            isLoginSuccess = false;
            final Boolean result = isLoginSuccess;
            iotpView.onSubmit(result, code);
        }else {
            final Boolean result = isLoginSuccess;
            chkOtpWithServer(result,code);
        }

    }

    public void chkOtpWithServer(final Boolean result, int code){
        Retrofit retrofit = new ApiClient().getRetrofitClient();
        WebServices webServices = retrofit.create(WebServices.class);
        Call<OTPModel> call = webServices.verifyOtp(edtOPTNo,session_id);
        call.enqueue(new Callback<OTPModel>() {
            @Override
            public void onResponse(Call<OTPModel> call, Response<OTPModel> response) {
                OTPModel otpModel  = (OTPModel) response.body();

                Gson gson = new Gson();
                String jsonString = gson.toJson(otpModel);

                System.out.println(" ----------- getFilters OfferListMap "+jsonString);
                if(jsonString!=null) {
                    System.out.println("-----------getFilters OfferList"+jsonString+" userDetails.getSuccess() : "+otpModel.getSuccess());
                }

                final int code =user.validateSessionOtpResponse(otpModel.getSuccess());
                System.out.println("--------- validateLoginDataBaseApi code: "+code);
                Boolean isLoginSuccess =true;
                if (code == 0) {
                    isLoginSuccess = false;
                    Errors errors = otpModel.getErrors();
                    System.out.println("--------- validateLoginDataBaseApi isError: "+errors.getConnect());
                    Toast.makeText((Context) iotpView, errors.getOtp(), Toast.LENGTH_SHORT).show();
                    System.out.println("-----validateLoginDataBaseApi  data unSuccess ");
                } else {
                    System.out.println("----- validateLoginDataBaseApi isError: "+otpModel.getSuccess());
                    Toast.makeText((Context) iotpView, "Otp Verified", Toast.LENGTH_SHORT).show();
                    iotpView.onSubmit(result, code);
                    System.out.println("----- validateLoginDataBaseApi data Successful ");
                }
                Boolean result = isLoginSuccess;
                System.out.println("----- sendRegisteredDataAndValidateResponse second Data Please see, code = " + code + ", result: " + result);
                iotpView.onSubmit(result, code);
            }

            @Override
            public void onFailure(Call<OTPModel> call, Throwable t) {
                Boolean isLoginSuccess = false;
                Boolean result = isLoginSuccess;
                int code = -78;
                iotpView.onSubmit(result, code);
                System.out.println("----- onFailure second Data Please see, printStackTrace = "+t.getMessage());

                t.printStackTrace();
                //Toast.makeText((Context) iRegisterView, "ErrorMessage"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initUser(){
        user = new OTPModel(edtOPTNo,checkTermsAndConductionBox);
    }

}
