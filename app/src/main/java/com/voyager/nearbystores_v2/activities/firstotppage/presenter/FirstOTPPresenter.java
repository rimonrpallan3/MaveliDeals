package com.voyager.nearbystores_v2.activities.firstotppage.presenter;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.voyager.nearbystores_v2.activities.firstotppage.model.CountryDetails;
import com.voyager.nearbystores_v2.activities.firstotppage.model.FirstOTPModel;
import com.voyager.nearbystores_v2.activities.firstotppage.model.IFirstOTPModel;
import com.voyager.nearbystores_v2.activities.firstotppage.view.IFirstOTPView;
import com.voyager.nearbystores_v2.activities.signuppage.model.IUserDetails;
import com.voyager.nearbystores_v2.classes.UserDetails;
import com.voyager.nearbystores_v2.webservices.ApiClient;
import com.voyager.nearbystores_v2.webservices.WebServices;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * Created by User on 8/30/2017.
 */

public class FirstOTPPresenter implements IFirstOTPControler {

    IFirstOTPView iotpView;
    IFirstOTPModel user;
    String contry;
    String zipCode;
    String phno;
    Activity activity;
    String countryJson = "";
    List<CountryDetails> countryDetailsList;
    String session_id = "";

    Gson gson;

    public FirstOTPPresenter(IFirstOTPView iotpView, Activity activity) {
        this.iotpView = iotpView;
        this.activity = activity;
        countryJson = loadJSONFromAsset();
        countryDetailsList = getCountryDetailsList();
        Gson gson = new Gson();
        String jsonString = gson.toJson(countryDetailsList);
        System.out.println("FirstOTPPresenter const countryDetailsList json : " + jsonString);
        iotpView.getCountryDetailList(countryDetailsList);
    }




    @Override
    public void doGetData(String contry, String zipCode, String phno) {
        System.out.println("contry : "+contry+" zipCode : "+zipCode+" phno : "+phno);
        this.contry = contry;
        this.zipCode = zipCode;
        this.phno = phno;
        initUser();
        Boolean isLoginSuccess = true;
        final int code = user.validateFirstOTPpage(contry,zipCode,phno);
        if (code!=0) isLoginSuccess = false;
        final Boolean result = isLoginSuccess;
        getOtpPage(result, code);

    }


    public void getOtpPage(final Boolean result, int code){
        Retrofit retrofit = new ApiClient().getRetrofitClient();
        WebServices webServices = retrofit.create(WebServices.class);
        Call<FirstOTPModel> call = webServices.getOtp(phno);
        call.enqueue(new Callback<FirstOTPModel>() {
            @Override
            public void onResponse(Call<FirstOTPModel> call, Response<FirstOTPModel> response) {
                FirstOTPModel firstOTPModel  = (FirstOTPModel) response.body();

                Gson gson = new Gson();
                String jsonString = gson.toJson(firstOTPModel);

                System.out.println(" ----------- getFilters OfferListMap "+jsonString);
                if(jsonString!=null) {
                    System.out.println("-----------getFilters OfferList"+jsonString+" userDetails.getSuccess() : "+firstOTPModel.getSuccess());
                }

                final int code =user.validateRegisterResponseError(firstOTPModel.getSuccess());
                System.out.println("--------- validateLoginDataBaseApi code: "+code);
                Boolean isLoginSuccess =true;
                if (code == 0||code == -1) {
                    isLoginSuccess = false;
                    System.out.println("--------- validateLoginDataBaseApi isError: "+firstOTPModel.getSuccess());
                    //Toast.makeText((Context) iLoginView, userDetails.getSuccess(), Toast.LENGTH_SHORT).show();
                    System.out.println("-----validateLoginDataBaseApi  data unSuccess ");
                } else {
                    session_id = firstOTPModel.getSession_id();
                    System.out.println("----- validateLoginDataBaseApi isError: "+firstOTPModel.getSuccess());
                    Toast.makeText((Context) iotpView, "Login Successful", Toast.LENGTH_SHORT).show();
                    iotpView.validatedSendData(result, code,session_id);
                    System.out.println("----- validateLoginDataBaseApi data Successful ");
                }
                Boolean result = isLoginSuccess;
                System.out.println("----- sendRegisteredDataAndValidateResponse second Data Please see, code = " + code + ", result: " + result);
                iotpView.validatedSendData(result, code,session_id);
            }

            @Override
            public void onFailure(Call<FirstOTPModel> call, Throwable t) {
                Boolean isLoginSuccess = false;
                Boolean result = isLoginSuccess;
                int code = -77;
                iotpView.validatedSendData(result, code,session_id);
                System.out.println("----- onFailure second Data Please see, printStackTrace = "+t.getMessage());

                t.printStackTrace();
                //Toast.makeText((Context) iRegisterView, "ErrorMessage"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = activity.getAssets().open("data/country_phones.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            //System.out.println("-------------loadJSONFromAsset "+json);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public List<CountryDetails> getCountryDetailsList(){
        List<CountryDetails> countryDetailsList = new ArrayList<>();
        try {
            JSONArray m_jArry = new JSONArray(loadJSONFromAsset());
            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                CountryDetails countryDetails = new CountryDetails();
                countryDetails.setName(jo_inside.getString("name"));
                countryDetails.setDial_code(jo_inside.getString("dial_code"));
                countryDetails.setCode(jo_inside.getString("code"));
                countryDetailsList.add(countryDetails);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return countryDetailsList;
    }


    private void initUser(){
        user = new FirstOTPModel(contry,zipCode,phno);
    }

}
