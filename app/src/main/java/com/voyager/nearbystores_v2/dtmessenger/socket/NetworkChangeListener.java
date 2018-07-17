package com.voyager.nearbystores_v2.dtmessenger.socket;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.voyager.nearbystores_v2.AppController;
import com.voyager.nearbystores_v2.GPS.GPStracker;
import com.voyager.nearbystores_v2.Services.BusStation;
import com.voyager.nearbystores_v2.classes.User;
import com.voyager.nearbystores_v2.controllers.messenger.MessengerController;
import com.voyager.nearbystores_v2.controllers.sessions.SessionsController;
import com.voyager.nearbystores_v2.helper.AppHelper;
import com.google.android.gms.maps.model.LatLng;


/**
 * Created by Abderrahim El imame on 8/18/16.
 *
 * @Email : maagoul275@@gmail.com
 * @Author : https://twitter.com/mago275
 */

public class NetworkChangeListener extends BroadcastReceiver {

    private boolean userIsConnected;

    @Override
    public void onReceive(Context mContext, Intent intent) {
        String action = intent.getAction();
        switch (action) {
            case "android.net.conn.CONNECTIVITY_CHANGE":
                if (isConnected()) {

                    AppHelper.LogCat("Connection is available");
                    setUserIsConnected(true);

                    GPStracker gpStracker = new GPStracker(mContext);
                    gpStracker.canGetLocation();
                    if(gpStracker.canGetLocation()){
                        SocketService.latPosition = new LatLng(gpStracker.getLatitude(),
                                gpStracker.getLongitude());
                    }

                    mContext.stopService(new Intent(mContext, SocketService.class));
                    mContext.startService(new Intent(mContext, SocketService.class));

                    loadAllNews(mContext);

                } else {
                    AppHelper.LogCat("Connection is not available");
                    mContext.stopService(new Intent(mContext, SocketService.class));
                    setUserIsConnected(false);
                }

                break;
        }

        BusStation.getBus().post(NetworkChangeListener.this);
    }

    public boolean isUserIsConnected() {
        return userIsConnected;
    }

    public void setUserIsConnected(boolean userIsConnected) {
        this.userIsConnected = userIsConnected;
    }


    /**
     * method to check if the user connection internet is available
     *
     * @return state of network
     */
    public static boolean isConnected() {

        ConnectivityManager cm = (ConnectivityManager) AppController.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }


    public void loadAllNews(Context context){

        try {
            User user = SessionsController.getSession().getUser();
            MessengerController.loadMessages(user,context);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
