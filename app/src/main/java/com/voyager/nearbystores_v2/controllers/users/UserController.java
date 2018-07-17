package com.voyager.nearbystores_v2.controllers.users;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.voyager.nearbystores_v2.AppController;
import com.voyager.nearbystores_v2.activities.MainActivity;
import com.voyager.nearbystores_v2.appconfig.AppConfig;
import com.voyager.nearbystores_v2.appconfig.Constances;
import com.voyager.nearbystores_v2.classes.User;
import com.voyager.nearbystores_v2.controllers.sessions.SessionsController;
import com.voyager.nearbystores_v2.network.VolleySingleton;
import com.voyager.nearbystores_v2.network.api_request.SimpleRequest;
import com.voyager.nearbystores_v2.parser.api_parser.UserParser;
import com.voyager.nearbystores_v2.security.Security;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by Amine on 7/13/2017.
 */

public class UserController {

    public static boolean insertUsers(final RealmList<User> list){

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(list);
            }
        });
        return  true;

    }

    public static void checkUserConnection(final FragmentActivity context){

        (new android.os.Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                checkUserWithThread(context);
            }
        },10000);

    }

    public static void checkUserWithThread(final FragmentActivity context){


        if(SessionsController.isLogged()){


            User user = SessionsController.getSession().getUser();
            final String email = user.getEmail();
            final String userid = String.valueOf(user.getId());
            final String username = user.getUsername();
            final String senderid = user.getSenderid();

            SimpleRequest request = new SimpleRequest(Request.Method.POST,
                    Constances.API.API_USER_CHECK_CONNECTION, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try{

                        if(AppConfig.APP_DEBUG)
                            Log.e("___checkUser",response);


                        JSONObject jsonObject = new JSONObject(response);
                        final UserParser mUserParser = new UserParser(jsonObject);

                        if(mUserParser.getSuccess()==0){
                            SessionsController.logOut();
                            ActivityCompat.finishAffinity(context);
                            context.startActivity(new Intent(context, MainActivity.class));
                        }

                    }catch (JSONException e){
                        //send a rapport to support
                        e.printStackTrace();

                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {

                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();

                    params.put("email",email);
                    params.put("userid",userid);
                    params.put("username",username);
                    params.put("senderid",senderid);

                    return Security.cryptedData(params);

                }

            };

            request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            VolleySingleton.getInstance(AppController.getInstance())
                    .getRequestQueue().add(request);


        }

    }
}
