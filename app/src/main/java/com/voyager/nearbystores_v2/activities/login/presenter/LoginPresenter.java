package com.voyager.nearbystores_v2.activities.login.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.gson.Gson;
import com.voyager.nearbystores_v2.R;
import com.voyager.nearbystores_v2.activities.firstotppage.model.CountryDetails;
import com.voyager.nearbystores_v2.activities.login.view.ILoginView;
import com.voyager.nearbystores_v2.activities.signuppage.model.IUserDetails;
import com.voyager.nearbystores_v2.classes.Errors;
import com.voyager.nearbystores_v2.classes.UserDetails;
import com.voyager.nearbystores_v2.classes.UserRow;
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
 * Created by User on 19-Jul-18.
 */

public class LoginPresenter implements ILoginPresenter{

    Context context;
    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;
    private static final String TAG = "LoginPresenter";
    String name;
    String passwd;
    IUserDetails user;
    UserDetails userDetails;
    ILoginView iLoginView;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth mAuth;
    Boolean state = false;
    String userId = "";
    String userName = "";
    String userEmailAdress = "";
    String userImageUrl = "";
    String userMob = "";
    Activity activity;
    List<CountryDetails> countryDetailsList;


    public LoginPresenter(Activity activity, SharedPreferences sharedPrefs,
                          SharedPreferences.Editor editor,
                          ILoginView iLoginView,
                          GoogleSignInClient mGoogleSignInClient,
                          FirebaseAuth mAuth) {
        this.activity = activity;
        this.sharedPrefs = sharedPrefs;
        this.editor = editor;
        this.iLoginView = iLoginView;
        this.mGoogleSignInClient = mGoogleSignInClient;
        this.mAuth = mAuth;
        countryDetailsList = getCountryDetailsList();
        iLoginView.getCountryDetailList(countryDetailsList);
    }



    @Override
    public void clear() {
        iLoginView.onClearText();
    }

    @Override
    public void doLogin(String countryCode,String name, String passwd) {
        this.name = countryCode+name;
        this.passwd = passwd;
        System.out.println("-------doLogin  email : " + countryCode+name +
                " Password : " + passwd);
        initUser();
        Boolean isLoginSuccess = true;
        final int code = user.checkUserValidity(name,passwd);
        if (code!=0) isLoginSuccess = false;
        final Boolean result = isLoginSuccess;
        iLoginView.onLoginResult(result, code);
        validateLoginDataBaseApi();
    }


    public void validateLoginDataBaseApi(){
        System.out.println("-------validateLoginDataBaseApi ");
        Retrofit retrofit = new ApiClient().getRetrofitClient();
        WebServices webServices = retrofit.create(WebServices.class);
        Call<UserDetails> call = webServices.loginUser(name,passwd);
        call.enqueue(new Callback<UserDetails>() {
            @Override
            public void onResponse(Call<UserDetails> call, Response<UserDetails> response) {
                userDetails  = (UserDetails) response.body();
                UserRow userRow = userDetails.getUserRow();
                Errors errors = userDetails.getErrors();
                if(userRow!=null){
                     System.out.println("-------validateLoginDataBaseApi  email : " + name +
                        " Password : " + passwd +
                        " LName : " + userRow.getName()+
                        " phno : " + userRow.getMobile() +
                        " email : " + userRow.getEmail() +
                        "pswd " + userRow.getPassword()+
                        "Auth"+ userRow.getTypeAuth());
                }else if(errors!=null){
                    System.out.println("-------validateLoginDataBaseApi  email : " + name +
                            " connect : " + errors.getConnect());
                }

                Gson gson = new Gson();
                String jsonString = gson.toJson(userDetails);

                System.out.println(" ----------- getFilters OfferListMap "+jsonString);
                if(jsonString!=null) {
                    System.out.println("-----------getFilters OfferList"+jsonString);
                }

                final int code =user.validateRegisterResponseError(userDetails.getSuccess());
                System.out.println("--------- validateLoginDataBaseApi code: "+code);
                Boolean isLoginSuccess =true;
                if (code == 0) {
                    isLoginSuccess = false;
                    System.out.println("--------- validateLoginDataBaseApi isError: "+userDetails.getSuccess());
                    Toast.makeText((Context) iLoginView, errors.getConnect(), Toast.LENGTH_SHORT).show();
                    System.out.println("-----validateLoginDataBaseApi  data unSuccess ");
                } else {
                    System.out.println("----- validateLoginDataBaseApi isError: "+userDetails.getSuccess());
                    Toast.makeText((Context) iLoginView, "Login Successful", Toast.LENGTH_SHORT).show();
                    addUserGsonInSharedPrefrences(userDetails);
                    System.out.println("----- validateLoginDataBaseApi data Successful ");
                }
                Boolean result = isLoginSuccess;
                System.out.println("----- sendRegisteredDataAndValidateResponse second Data Please see, code = " + code + ", result: " + result);
                iLoginView.onLoginResponse(result, code);
            }

            @Override
            public void onFailure(Call<UserDetails> call, Throwable t) {
                Boolean isLoginSuccess = false;
                Boolean result = isLoginSuccess;
                int code = -77;
                iLoginView.onLoginResult(result, code);
                t.printStackTrace();
                //Toast.makeText((Context) iRegisterView, "ErrorMessage"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void addUserGsonInSharedPrefrences(UserDetails UserDetails ){
        Gson gson = new Gson();
        String jsonString = gson.toJson(UserDetails);
        //UserDetails user1 = gson.fromJson(jsonString,UserDetails.class);
        if(jsonString!=null) {
            editor.putString("UserDetails", jsonString);
            editor.commit();
            System.out.println("-----------validateLoginDataBaseApi UserDetails"+jsonString);
        }

    }



    @Override
    public void onLoginSucuess() {
        iLoginView.sendPParcelableObj(userDetails);
    }

    @Override
    public void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        /**
         * Once the Google Sign in is successful,
         * Firebase authentication is done using Google's Sign In credentials.
         * And finally the users details are stored on the Firebase Authentication console.
         *
         * @param acct Google's Sign in account.
         */
        // [START auth_with_google]
            Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
            // [START_EXCLUDE silent]
        iLoginView.setLoader(View.VISIBLE);
            // [END_EXCLUDE]
            AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithCredential:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithCredential:failure", task.getException());
                                Snackbar.make(activity.findViewById(android.R.id.content), activity.getResources().getString(R.string.snack_error_acct), Snackbar.LENGTH_SHORT).show();

                                updateUI(null);
                            }
                            // [START_EXCLUDE]
                            iLoginView.setLoader(View.GONE);
                            // [END_EXCLUDE]
                        }
                    });
    }

    @Override
    public void updateUI(final FirebaseUser user) {
        System.out.println("SignInPresenter user : " + user);
        if (user != null) {
            state = true;
            userId = user.getUid();
            userName = user.getDisplayName();
            userEmailAdress = user.getEmail();
            userImageUrl = String.valueOf(user.getPhotoUrl());
            userMob = user.getPhoneNumber();
            iLoginView.setLoader(View.GONE);
            System.out.println("SignInPresenter updateUI state : "+state+" userId : "+userId+" userName : "+userName+" userImageUrl : "+userImageUrl+ " userMob : "+userMob);
        } else {
            System.out.println("Something went wrong SignInPresenter updateUI");
        }
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
        user = new UserDetails(name,passwd);
    }

}
