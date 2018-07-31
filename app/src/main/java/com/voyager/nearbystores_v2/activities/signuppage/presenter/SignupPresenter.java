package com.voyager.nearbystores_v2.activities.signuppage.presenter;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.voyager.nearbystores_v2.activities.signuppage.model.IUserDetails;
import com.voyager.nearbystores_v2.activities.signuppage.view.ISignupView;
import com.voyager.nearbystores_v2.classes.UserDetails;
import com.voyager.nearbystores_v2.webservices.ApiClient;
import com.voyager.nearbystores_v2.webservices.WebServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by User on 20-Jul-18.
 */

public class SignupPresenter implements ISignupPresenter{

    Context context;
    ISignupView iSignupView;
    String FullName;
    String email;
    String mobNo;
    String pswd;
    String confirmPswd;
    Boolean termsAndCondCheck;
    UserDetails userDetails;
    IUserDetails user;
    String auth;

    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;

    public SignupPresenter(Context context,
                           ISignupView iSignupView,
                           SharedPreferences sharedPrefs,
                           SharedPreferences.Editor editor) {
        this.context = context;
        this.iSignupView = iSignupView;
        this.sharedPrefs = sharedPrefs;
        this.editor = editor;
    }

    @Override
    public void setTermCondMsg(TextView v) {
        SpannableString ss = new SpannableString(context.getString(R.string.signup_term_cond));
        final ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(final View textView) {
                iSignupView.moveToTermsAndConductionPage();
            }

            @Override
            public void updateDrawState(final TextPaint textPaint) {
                textPaint.setColor(ContextCompat.getColor(context.getApplicationContext(), R.color.red));
                textPaint.setUnderlineText(true);
            }
        };
        ss.setSpan(clickableSpan,13,30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        v.setText(ss);
        v.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onRegisteredSucuess() {
        iSignupView.sendPParcelableObj(userDetails);
    }

    @Override
    public void doRegister(String FullName, String email, String mobNo,String pswd, String confirmPswd, Boolean termsAndCondCheck) {
        System.out.println("-------------------RegisterPresenter doRegister FullName : "+FullName+" Password : "+pswd+" RetypePassword : "+confirmPswd+" email : "+email+" phno : "+mobNo+" termsAndCondCheck : "+termsAndCondCheck);
        this.FullName = FullName;
        this.pswd = pswd;
        this.confirmPswd = confirmPswd;
        this.email = email;
        this.mobNo = mobNo;
        this.termsAndCondCheck = termsAndCondCheck;
        initUser();
        Boolean isLoginSuccess = true;
        final int code = user.validateUserDetails(FullName,email,mobNo,pswd,confirmPswd,termsAndCondCheck);
        if (code != 0) {
            isLoginSuccess = false;
        } else {
            sendRegisteredDataAndValidateResponse();
        }
        Boolean result = isLoginSuccess;
        iSignupView.onRegister(result, code);
    }

    private void initUser(){
        user = new UserDetails(FullName,pswd,email,auth,mobNo);
    }

    private void addUserGsonInSharedPrefrences(UserDetails userDetails){
        Gson gson = new Gson();
        String jsonString = gson.toJson(userDetails);
        //UserModel user1 = gson.fromJson(jsonString,UserModel.class);
        if(jsonString!=null) {
            editor.putString("UserDetails", jsonString);
            editor.commit();
            System.out.println("-----------sendRegisteredDataAndValidateResponse  UserDetails"+jsonString);

        }

    }


    public void sendRegisteredDataAndValidateResponse(){
        Retrofit retrofit = new ApiClient().getRetrofitClient();
        WebServices webServices = retrofit.create(WebServices.class);
        Call<UserDetails> call = webServices.registerUser(FullName,pswd,email,mobNo,"customer");
        call.enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                userDetails  = (UserDetails) response.body();
                System.out.println("------- sendRegisteredDataAndValidateResponse  FullName : " + FullName +
                        " Password : " + pswd +
                        " email Address : " + email +
                        " phno : " + mobNo);
               /* userDetails.setName(FullName);
                userDetails.setPassword(pswd);
                userDetails.setEmail(email);
                userDetails.setTel(mobNo);*/
                //userDetails.setUserName();

                final int code =user.validateRegisterResponseError(userDetails.getSuccess());
                System.out.println("-----sendRegisteredDataAndValidateResponse  data code :"+code);
                Boolean isLoginSuccess =true;
                if (code == 0) {
                    isLoginSuccess = false;
                    //Toast.makeText((Context) iSignupView, userDetails.getSuccess(), Toast.LENGTH_SHORT).show();
                    System.out.println("-----sendRegisteredDataAndValidateResponse  data unSuccess ");
                } else {
                    Toast.makeText((Context) iSignupView, "Register Successful", Toast.LENGTH_SHORT).show();
                    addUserGsonInSharedPrefrences(userDetails);
                    System.out.println("----- sendRegisteredDataAndValidateResponse data Successful ");
                }
                Boolean result = isLoginSuccess;
                System.out.println("----- sendRegisteredDataAndValidateResponse second Data Please see, code = " + code + ", result: " + result);
                iSignupView.onRegistered(result, code);
            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {
                Boolean isLoginSuccess =false;
                Boolean result = isLoginSuccess;
                int code = -77;
                iSignupView.onRegistered(result, code);
                t.printStackTrace();
                //Toast.makeText((Context) iRegisterView, "ErrorMessage"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


}
