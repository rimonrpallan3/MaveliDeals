package com.voyager.nearbystores_v2.dtmessenger.socket;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.voyager.nearbystores_v2.AppController;
import com.voyager.nearbystores_v2.BuildConfig;
import com.voyager.nearbystores_v2.Services.BusStation;
import com.voyager.nearbystores_v2.Services.Pusher;
import com.voyager.nearbystores_v2.appconfig.AppConfig;
import com.voyager.nearbystores_v2.appconfig.AppContext;
import com.voyager.nearbystores_v2.appconfig.Constances;
import com.voyager.nearbystores_v2.classes.User;
import com.voyager.nearbystores_v2.controllers.sessions.SessionsController;
import com.voyager.nearbystores_v2.dtmessenger.InComingDataParserSender;
import com.voyager.nearbystores_v2.helper.AppHelper;
import com.voyager.nearbystores_v2.security.Security;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.HashMap;

import io.realm.Realm;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import static com.voyager.nearbystores_v2.appconfig.AppConfig.APP_DEBUG;

/**
 * Created by Amine on 12/21/2016.
 */

public class SocketService extends Service {

    public static void init(Context context){

        TAG=BuildConfig.APPLICATION_ID+"/"+ Constances.SOCKET_SERVER_VERSION+"/";
        TAG_CONNECTED = TAG+"user connect";
        TAG_CONNECT_FAILD  =TAG+"user connect";
        TAG_NEW_MESSAGE    =TAG+"user sendMessage";
        TAG_USER_TYPING    =TAG+"user typing";
        TAG_USER_STOPE_TYPING    =TAG+"user stop_typing";
        TAG_USER_OFFLINE    =TAG+"user offline";
        TAG_USER_ONLINE    =TAG+"user online";
        TAG_MESSAGE_SENT    =TAG+"message sent";
        TAG_CHANGE_USER_STATE    =TAG+"user state";

        if(AppConfig.APP_DEBUG)
            Log.e("SocketService.init",TAG);

        try {
            if(SessionsController.isLogged()){
                context.startService(new Intent(context, SocketService.class));
            }
        }catch (Exception e){
            if(APP_DEBUG)
                e.printStackTrace();
        }
    }



    public static String TAG;

    public static String TAG_CONNECTED;
    public static String TAG_CONNECT_FAILD;
    public static String TAG_NEW_MESSAGE;
    public static String TAG_USER_TYPING;
    public static String TAG_USER_STOPE_TYPING;
    public static String TAG_USER_OFFLINE;
    public static String TAG_USER_ONLINE;
    public static String TAG_MESSAGE_SENT;
    public static String TAG_CHANGE_USER_STATE ;


    public static String TAG_USERID         ="userId";
    public static String TAG_SOCKETID       ="socketId";
    public static String TAG_POST_DATA      ="post";
    public static String TAG_SENDERID       ="senderId";
    public static String TAG_RECEIVERID     ="receiverId";



    public static Socket mSocket;
    private static String socketID;
    private static final long TIMEOUT = 20 * 1000;
    private static final int RETRY_ATTEMPT = 5;
    private MessagesReceiverBroadcast mChangeListener;
    private static Realm realm;
    private static Handler handler;

    private static boolean serviceIsConnected=false;

    public static String TAG_NEED_OPEN_LIST_DISCUSSIONS="need_open_list_discussions";
    public static String TAG_NEED_OPEN_INBOX="need_open_inbox";


    /**
     * method to get socketId for the connected user
     *
     * @return socketID
     */
    public static String getSocketID() {
        return socketID;
    }

    /**
     * method to set the socketId for the connected user
     *
     * @param socketID this the parameter
     */
    private static void setSocketID(String socketID) {
        SocketService.socketID = socketID;
    }

    public static boolean serviceIsConnected(){
        return serviceIsConnected;
    }

    public static void disconnectSocket() {

        try {
            User user = SessionsController.getSession().getUser();
            if(user!=null){

                JSONObject json = new JSONObject();
                try {
                    json.put(SocketService.TAG_USERID,user.getId());
                    if(mSocket!=null)
                        mSocket.emit(TAG_USER_OFFLINE,json);
                } catch (JSONException e) {
                    if(AppContext.DEBUG)
                        e.printStackTrace();
                }

            }
        }catch (Exception e){
            if(AppContext.DEBUG)
                e.printStackTrace();
        }

        serviceIsConnected = false;

        try{

            mSocket.off(Socket.EVENT_RECONNECT);
            mSocket.off(Socket.EVENT_CONNECT);
            mSocket.off(Socket.EVENT_DISCONNECT);

            mSocket.off(TAG_NEW_MESSAGE);
            mSocket.off(TAG_CONNECTED);
            mSocket.off(TAG_CONNECT_FAILD);
            mSocket.off(TAG_USER_TYPING);
            mSocket.off(TAG_USER_ONLINE);
            mSocket.off(TAG_USER_OFFLINE);
            mSocket.off(TAG_MESSAGE_SENT);

            mSocket.off(TAG_USER_TYPING);
            mSocket.off(TAG_USER_STOPE_TYPING);


            if (mSocket != null) mSocket.disconnect();
            mSocket = null;

        }catch (Exception e){

        }




    }

    public static Socket getSocket(){
        if(mSocket==null){
            connectToServer();
        }
        return mSocket;
    }


    public static void restartSocketService(Context mContext){

        mContext.stopService(new Intent(mContext, SocketService.class));
        mContext.startService(new Intent(mContext, SocketService.class));
    }

    /**
     * method for server connection initialization
     */
    public static void connectToServer() {

        if(!AppConfig.ENABLE_CHAT || AppConfig.CHAT_WITH_FIREBASE)
            return;

        if(!SessionsController.isLogged()){
            return;
        }

        if(AppContext.DEBUG)
            Log.e("connectToServer="+Constances.SERVER_ADDRESS_IP,"User Disconnected");

        final User user = SessionsController.getSession().getUser();

        if(user!=null) {

            final Realm realm = Realm.getDefaultInstance();

            IO.Options options = new IO.Options();
            options.forceNew = true;
            options.reconnection = true;
            options.timeout = TIMEOUT; //set -1 to  disable it
            options.reconnectionDelay = 0;
            options.reconnectionAttempts = RETRY_ATTEMPT;
            try {
                mSocket = IO.socket(Constances.SERVER_ADDRESS_IP, options);
            } catch (URISyntaxException e) {
                if(AppConfig.APP_DEBUG)
                    e.printStackTrace();
                throw new RuntimeException(e);
            }


            final JSONObject paramsToAuthenticate = new JSONObject();
            try {
                paramsToAuthenticate.put("connected", true);
                paramsToAuthenticate.put(TAG_POST_DATA,
                        connectionParams(user));
            } catch (JSONException e) {
                e.printStackTrace();
            }


            mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {

                    if(paramsToAuthenticate!=null){

                        if(mSocket!=null){

                            mSocket.emit(TAG_CONNECTED, paramsToAuthenticate);
                            AppHelper.LogCat("Try to connect!" + getSocketID());
                        }

                    }


                    AppHelper.LogCat("You are connected to chat server with id " + getSocketID());
                }
            });

            mSocket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    AppHelper.LogCat("You are lost connection to chat server ");
                }
            });
            mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    AppHelper.LogCat("SOCKET TIMEOUT");
                    reconnect();
                }
            });
            mSocket.on(Socket.EVENT_RECONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {

                    AppHelper.LogCat("Reconnect");
                    if(mSocket!=null)
                        if (!mSocket.connected()) {
                            //mSocket.connect(); TODO khasni ndir custom lhad l7abs
                           // reconnect();
                            reconnect();
                        }
                    else{
                            try {
                                connectToServer();
                            }catch (Exception e){

                            }finally {

                            }

                    }
                }
            });

            mSocket.connect();





            isUserConnected();
        }
    }



    @Override
    public void onCreate() {
        super.onCreate();

        if(!AppConfig.ENABLE_CHAT || AppConfig.CHAT_WITH_FIREBASE)
            return;

        realm = Realm.getDefaultInstance();
        handler = new Handler();

        connectToServer();
        userIsOnline();
        messageSent();
        receiveNewMessage();
        userIsOffline();
        userTyping();
        userStopTyping();

        mChangeListener = new MessagesReceiverBroadcast() {
            @Override
            protected void MessageReceived(Context context , JSONObject data) {

                if(AppConfig.APP_DEBUG)
                    Log.e("INCOMING_DATA",data.toString());

                InComingDataParserSender.parseAndSend(context,data);


            }
        };

        getApplication().registerReceiver(mChangeListener, new IntentFilter("new_user_message_notification"));
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(!AppConfig.ENABLE_CHAT || AppConfig.CHAT_WITH_FIREBASE)
            return;

        // service finished

        try {
            getApplicationContext().unregisterReceiver(mChangeListener);
        }catch (Exception e){

        }
        disconnectSocket();

        try {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.close();
                }
            });
        }catch (Exception e){

        }
        stopSelf();

        if(AppConfig.APP_DEBUG)
            Log.e("service","stoped");

    }


    /**
     * method to send message
     */


    public static boolean sendMessage(JSONObject message, User user){

        JSONObject json = new JSONObject();
        try {
            json.put(TAG_POST_DATA,message);
            json.put("userId",user.getId());
            json.put("senderId",user.getSenderid());
            mSocket.emit(TAG_NEW_MESSAGE,json);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  true;
    }


    /**
     * method to reconnect sockets
     */
    private static void reconnect() {

        if(!AppConfig.ENABLE_CHAT || AppConfig.CHAT_WITH_FIREBASE)
            return;

        if (mSocket != null && mSocket.connected()) {
            disconnectSocket();

            try {

                realm = Realm.getDefaultInstance();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        connectToServer();
                    }
                });

            }catch (Exception e){

            }

        }
    }



    public void userIsOnline(){

        if(mSocket!=null)
        mSocket.on(TAG_USER_ONLINE, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
             JSONObject data = (JSONObject) args[0];
             BusStation.getBus().post(new Pusher(Pusher.ONLINE,data.toString()));
            }
        });

    }


    public static void reconnectUser(User user){


        final JSONObject paramsToAuthenticate = new JSONObject();
        try {
            paramsToAuthenticate.put("connected", true);
            paramsToAuthenticate.put(TAG_POST_DATA, connectionParams(user));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(paramsToAuthenticate!=null && mSocket!=null){

            mSocket.emit(TAG_CONNECTED, paramsToAuthenticate);
        }

    }

    public static void checkUserIsOnline(int userId){

        JSONObject json = new JSONObject();
        try {
            json.put(SocketService.TAG_USERID,userId);

            if(mSocket!=null)
                mSocket.emit(SocketService.TAG_USER_ONLINE,json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void setUserAsOffline(int userId){

        JSONObject json = new JSONObject();
        try {
            json.put(SocketService.TAG_USERID,userId);
            if(mSocket!=null)
                mSocket.emit(SocketService.TAG_USER_OFFLINE,json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setTypingState(User sender,User receiver){

        JSONObject json = new JSONObject();
        try {
            json.put(SocketService.TAG_USERID,sender.getId());
            json.put(SocketService.TAG_SENDERID,sender.getSenderid());
            json.put(SocketService.TAG_RECEIVERID,receiver.getId());
            if(mSocket!=null)
                mSocket.emit(SocketService.TAG_USER_TYPING,json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void setStopTypingState(User sender,User receiver){

        JSONObject json = new JSONObject();
        try {
            json.put(SocketService.TAG_USERID,sender.getId());
            json.put(SocketService.TAG_SENDERID,sender.getSenderid());
            json.put(SocketService.TAG_RECEIVERID,receiver.getId());
            if(mSocket!=null)
                mSocket.emit(SocketService.TAG_USER_STOPE_TYPING,json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private static void isUserConnected() {

        if(mSocket!=null)
        mSocket.on(TAG_CONNECTED, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                final JSONObject data = (JSONObject) args[0];
                //AppHelper.LogCat("Is online "+data.toString());
                BusStation.getBus().post(new Pusher(Pusher.USER_CONNECTED,data.toString()));
                serviceIsConnected = true;

            }
        });
    }



    public void userIsOffline(){

        if(mSocket!=null)
        mSocket.on(TAG_USER_OFFLINE, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];

                //AppHelper.LogCat("Is offline "+data.toString());
                BusStation.getBus().post(new Pusher(Pusher.OFFLINE,data.toString()));

            }
        });

    }


    public void messageSent(){

        if(mSocket!=null)
            mSocket.on(TAG_MESSAGE_SENT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {

                    JSONObject data = (JSONObject) args[0];
                    if(AppContext.DEBUG)
                        Log.e("qamine",data.toString());

                    BusStation.getBus().post(new Pusher(Pusher.MESSAGE_DELIVERED,data.toString()));
                }
            });

    }


    public void userTyping(){

        if(mSocket!=null)
        mSocket.on(TAG_USER_TYPING, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                BusStation.getBus().post(new Pusher(Pusher.USER_TYPING,data.toString()));

            }
        });

    }

    public void userStopTyping(){

        if(mSocket!=null)
        mSocket.on(TAG_USER_STOPE_TYPING, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                BusStation.getBus().post(new Pusher(Pusher.USER_STOP_TYPING,data.toString()));

            }
        });

    }


    public void receiveNewMessage(){

        if(mSocket!=null)
            mSocket.on(TAG_NEW_MESSAGE, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    JSONObject data = (JSONObject) args[0];

                    if(AppConfig.APP_DEBUG){
                        Log.e("receiveNewMessage",data.toString());
                    }
                    sendNewMessage(data);
                }
            });

    }


   private Intent mIntent;

    private void sendNewMessage(JSONObject data){

        mIntent = new Intent("new_user_message_notification");
        mIntent.putExtra("message", data.toString());
        sendBroadcast(mIntent);
    }


    public static LatLng latPosition;

    public static JSONObject connectionParams(User user) throws JSONException {

        JSONObject jsonToServer = new JSONObject();
        JSONObject jsonHeaders = new JSONObject();



        HashMap<String,String> params = new HashMap<>();

        params.put("email",user.getEmail());
        params.put("userid", String.valueOf(user.getId()));
        params.put("username",user.getUsername());

        jsonHeaders.put("token-1", AppController.getTokens().get("token-1"));
        jsonHeaders.put("macadr",AppController.getTokens().get("macadr"));
        jsonHeaders.put("token-0",AppController.getTokens().get("token-0"));

        jsonToServer.put("post", Security.cryptedData(params).get("post"));
        jsonToServer.put("headers",jsonHeaders);

        if(latPosition!=null){
            jsonToServer.put("lat", String.valueOf(latPosition.latitude));
            jsonToServer.put("lng", String.valueOf(latPosition.longitude));
        }else{
            jsonToServer.put("lat","0");
            jsonToServer.put("lng", "0");
        }

        return  jsonToServer;

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
