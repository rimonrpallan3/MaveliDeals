package com.voyager.nearbystores_v2.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.voyager.nearbystores_v2.AppController;
import com.voyager.nearbystores_v2.R;
import com.voyager.nearbystores_v2.activities.login.LoginPage;
import com.voyager.nearbystores_v2.animation.Animation;
import com.voyager.nearbystores_v2.appconfig.AppConfig;
import com.voyager.nearbystores_v2.appconfig.AppContext;
import com.voyager.nearbystores_v2.appconfig.Constances;
import com.voyager.nearbystores_v2.classes.CountriesModel;
import com.voyager.nearbystores_v2.classes.User;
import com.voyager.nearbystores_v2.classes.Category;
import com.voyager.nearbystores_v2.controllers.categories.CategoryController;
import com.voyager.nearbystores_v2.controllers.events.EventController;
import com.voyager.nearbystores_v2.controllers.sessions.GuestController;
import com.voyager.nearbystores_v2.controllers.sessions.SessionsController;
import com.voyager.nearbystores_v2.controllers.stores.OffersController;
import com.voyager.nearbystores_v2.controllers.stores.StoreController;
import com.voyager.nearbystores_v2.dtmessenger.socket.TokenInstance;
import com.voyager.nearbystores_v2.helper.AppHelper;
import com.voyager.nearbystores_v2.load_manager.ViewManager;
import com.voyager.nearbystores_v2.network.ServiceHandler;
import com.voyager.nearbystores_v2.network.VolleySingleton;
import com.voyager.nearbystores_v2.network.api_request.SimpleRequest;
import com.voyager.nearbystores_v2.parser.Parser;
import com.voyager.nearbystores_v2.parser.api_parser.CategoryParser;
import com.voyager.nearbystores_v2.parser.tags.Tags;
import com.voyager.nearbystores_v2.push_notification_firebase.FirebaseInstanceIDService;
import com.voyager.nearbystores_v2.security.Security;
import com.voyager.nearbystores_v2.utils.MessageDialog;
import com.voyager.nearbystores_v2.utils.SecurityUtils;
import com.voyager.nearbystores_v2.utils.Utils;
import com.voyager.nearbystores_v2.views.CustomDialog;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;

import static com.voyager.nearbystores_v2.appconfig.AppConfig.APP_DEBUG;
import static com.voyager.nearbystores_v2.security.Security.CRYPRO_KEY;

public class SplashActivity extends AppCompatActivity implements ViewManager.CustomView, View.OnClickListener {

    public ViewManager mViewManager;

    private Button findstore,createstore,connect,get;
    private TextView getting,app_name;
    private User user=null;
    private Context context;
    private String TAG = ".SplashActivity";
    //init request http
    private RequestQueue queue;
    private ProgressDialog pdialog;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Utils.enableEvent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        System.out.println(TAG+": onCreate");

       if( GuestController.isStored() == false ){
           FirebaseInstanceIDService.reloadToken();
           System.out.println(TAG+": onCreate  isStored");
       } else{
            if(AppConfig.APP_DEBUG){
                Log.e("GuestId-"+GuestController.getGuest().getId(),GuestController.getGuest().getFcmId());
                System.out.println(TAG+": onCreate APP_DEBUG ");
                FirebaseInstanceIDService.reloadToken();
            }

       }

        ImageView splashImage = (ImageView) findViewById(R.id.splashImage);
        Animation.startZoomEffect(splashImage);

        Realm realm = Realm.getDefaultInstance();

        AppController app = (AppController) getApplication();
        if(AppConfig.TabsConfig==null)
            AppConfig.TabsConfig = app.parseAppTabsConfig();

        for (Category cat : AppConfig.TabsConfig)
            CategoryController.insertCategory(cat);

        OffersController.removeAll();
        StoreController.removeAll();
        EventController.removeAll();

        Gson gson = new Gson();
        final List<CountriesModel> list = gson.fromJson(AppHelper.loadCountriesJSONFromAsset(this), new TypeToken<List<CountriesModel>>() {
        }.getType());

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(list);
                System.out.println(TAG+": onCreate executeTransaction ");

            }
        });
        //PushNotifcation

       // FirebaseMessaging.getInstance().subscribeToTopic("test");
        FirebaseInstanceId.getInstance().getToken();

        context  = this;

        if(SessionsController.isLogged())
            user = SessionsController.getSession().getUser();

        findstore = (Button) findViewById(R.id.findstore);
        createstore = (Button) findViewById(R.id.createstore);
        connect = (Button) findViewById(R.id.connect);

        findstore.setOnClickListener(this);
        createstore.setOnClickListener(this);

        get = (Button) findViewById(R.id.getloc);
        get.setOnClickListener(this);
        getting = (TextView) findViewById(R.id.getting);


        Utils.setFont(this,findstore,"");
        Utils.setFont(this,createstore,"");
        Utils.setFont(this, connect, "");

        if(user!=null){
            connect.setVisibility(View.GONE);
        }else{
            connect.setOnClickListener(this);
        }


        mViewManager = new ViewManager(this);
        mViewManager.setLoading(findViewById(R.id.loading));
        mViewManager.setNoLoading(findViewById(R.id.content_my_store));
        mViewManager.setError(findViewById(R.id.error));
        mViewManager.setEmpty(findViewById(R.id.empty));

        mViewManager.setCustumizeView(this);

        boolean loaded = false;

        try{
            loaded = getIntent().getExtras().getBoolean("loaded");
        }catch (Exception e){

        }

        //sync all categories
        getCategories();

        if(!AppController.isTokenFound()){
            mViewManager.loading();
            System.out.println(TAG+": onCreate isTokenFound ");
            appInit();
        }else{

            if (loaded==false) {
                mViewManager.loading();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                   startMain();
                    }
                }, 3500);

                System.out.println(TAG+": onCreate loading ");
            } else {
                startMain();
            }

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public LatLng getLocationFromAddress(Context context, String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new LatLng(location.getLatitude(), location.getLongitude() );

        } catch (Exception ex) {

            ex.printStackTrace();
        }

        return p1;
    }

    @Override
    public void customErrorView(View v) {

    }

    @Override
    public void customLoadingView(View v) {

    }

    @Override
    public void customEmptyView(View v) {

    }

    @Override
    public void onClick(View v) {


        if(v.getId() == R.id.connect){

            startActivity(new Intent(SplashActivity.this,LoginActivity.class));
            overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);
            finish();

        }else if( v.getId() == R.id.findstore){


            startActivity(new Intent(SplashActivity.this,MainActivity.class));
            overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);
            finish();

        }else if( v.getId() == R.id.createstore){

           /* if(user==null) {
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                i.putExtra("direction",CreateStoreActivity.class);
                startActivity(i);
            }else
                startActivity(new Intent(SplashActivity.this, CreateStoreActivity.class));

            overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);
            finish();*/

        }else if(v.getId() == R.id.getloc){

            LatLng l = getLocationFromAddress(this,getting.getText().toString());
            if(APP_DEBUG) { Log.e("position:","Lat:"+l.latitude+" | "+"Long:"+l.longitude); }

        }

    }

    private void startMain(){

        Intent intent = new Intent(SplashActivity.this,LoginPage.class);

        try {
            intent.putExtra("chat",getIntent().getExtras().getBoolean("chat"));
        }catch (Exception e){

        }

        startActivity(intent);
        //
        // overridePendingTransition(R.anim.lefttoright_enter, R.anim.lefttoright_exit);
        finish();

    }

    public CustomDialog showErrors(Map<String,String> errors){

        final CustomDialog dialog = new CustomDialog(this);
        dialog.setContentView(R.layout.fragment_dialog_costum);
        dialog.setCancelable(false);

        String text = "";
        for ( String key : errors.keySet() ) {
            if(!text.equals(""))
                text = text+"<br>";


            text = text+"#"+errors.get(key);
        }

        Button ok = (Button) dialog.findViewById(R.id.ok);
        Button cancel = (Button) dialog.findViewById(R.id.cancel);

        TextView msgbox = (TextView) dialog.findViewById(R.id.msgbox);

        if(!text.equals("") ){
            msgbox.setText(Html.fromHtml(text));
        }
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                appInit();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        cancel.setVisibility(View.GONE);
        dialog.show();

        return dialog;

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }


    private void getCategories(){



        queue = VolleySingleton.getInstance(this).getRequestQueue();

        SimpleRequest request = new SimpleRequest(Request.Method.GET,
                Constances.API.API_USER_GET_CATEGORY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{

                    if(APP_DEBUG) { Log.e("catsResponse",response);}

                    JSONObject jsonObject = new JSONObject(response);
                    // Log.e("response", jsonObject.toString());
                    final CategoryParser mCategoryParser = new CategoryParser(jsonObject);
                    int success = Integer.parseInt(mCategoryParser.getStringAttr(Tags.SUCCESS));

                    if(success==1 && mCategoryParser.getCategories().size()>0){
                        //database.deleteCats();
                        CategoryController.insertCategories(
                                mCategoryParser.getCategories()
                        );
                    }

                }catch (JSONException e){
                    //send a rapport to support
                    e.printStackTrace();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(APP_DEBUG) { Log.e("ERROR", error.toString());}


            }


        }) {



        };

        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);



    }


    private void appInit(){

        final String device_token = TokenInstance.getTokenID(this);
        final String mac_address = ServiceHandler.getMacAddr();
        final String ip_address = String.valueOf(ServiceHandler.getIpAddress(this));

        SimpleRequest request = new SimpleRequest(Request.Method.POST,
                Constances.API.API_APP_INIT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                System.out.println(TAG+": appInit");
                try {

                    if(AppContext.DEBUG)
                        Log.e("response", response);

                    JSONObject js = new JSONObject(response);

                    Parser mParser = new Parser(js);
                    //CityParser mCityParser = new CityParser(js.getJSONObject(Tags.RESULT).getJSONObject(CityParser.TAG));

                    int success = Integer.parseInt(mParser.getStringAttr(Tags.SUCCESS));

                    if(success==1){

                        final String token = mParser.getStringAttr("token");

                        if(AppConfig.APP_DEBUG)
                            Toast.makeText(SplashActivity.this,token,Toast.LENGTH_LONG).show();

                        AppController.setTokens(mac_address,device_token,token);

                        startActivity(new Intent(SplashActivity.this,LoginPage.class));
                        finish();

                        if(AppContext.DEBUG)
                            Log.e("token",token);
                    }else {

                        //show message error
                        MessageDialog.newDialog(SplashActivity.this).onCancelClick(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                MessageDialog.getInstance().hide();
                                finish();
                            }
                        }).onOkClick(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                appInit();
                                MessageDialog.getInstance().hide();
                            }
                        }).setContent("Token isn't valid!").show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();

                    //show message error
                    MessageDialog.newDialog(SplashActivity.this).onCancelClick(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MessageDialog.getInstance().hide();
                            finish();
                        }
                    }).onOkClick(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            appInit();
                            MessageDialog.getInstance().hide();
                        }
                    }).setContent("Error with initialization!").show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                if(AppContext.DEBUG)
                    Log.e("ERROR", error.toString());
                if (error instanceof TimeoutError || error instanceof NoConnectionError || error instanceof NetworkError) {

                    //show message error
                    MessageDialog.newDialog(SplashActivity.this).onCancelClick(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MessageDialog.getInstance().hide();
                            finish();
                        }
                    }).onOkClick(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            appInit();
                            MessageDialog.getInstance().hide();
                        }
                    }).setContent("Error!").show();


                } else if (error instanceof AuthFailureError) {
                    //TODO
                } else if (error instanceof ServerError) {
                    //TODO
                } else if (error instanceof NetworkError) {
                    //TODO
                } else if (error instanceof ParseError) {
                    //TODO
                }



            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("device_token", device_token);
                params.put("mac_address", mac_address);
                params.put("mac_adr", mac_address);
                params.put("CRYPRO_KEY", CRYPRO_KEY);

                Security.KeyIV  = SecurityUtils.md5(CRYPRO_KEY+"1@"+ mac_address).toLowerCase().substring(0,16);
                Security.SKey   = SecurityUtils.md5(CRYPRO_KEY+"2@"+ mac_address).toLowerCase().substring(0,16);
                String hashed = Security.cryptHashMap(params);

                if(AppContext.DEBUG)
                    Log.e("_SPLASH_SYNC_",  params.toString());


                params = new HashMap<>();
                params.put(SecurityUtils.Tags.BODY,hashed);


                return params;
            }

        };


        request.setRetryPolicy(new DefaultRetryPolicy(SimpleRequest.TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);



    }



}
