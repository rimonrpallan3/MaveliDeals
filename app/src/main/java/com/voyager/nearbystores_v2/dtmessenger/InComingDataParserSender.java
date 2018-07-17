package com.voyager.nearbystores_v2.dtmessenger;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.voyager.nearbystores_v2.AppController;
import com.voyager.nearbystores_v2.R;
import com.voyager.nearbystores_v2.Services.BusStation;
import com.voyager.nearbystores_v2.Services.Pusher;
import com.voyager.nearbystores_v2.activities.MainActivity;
import com.voyager.nearbystores_v2.activities.SplashActivity;
import com.voyager.nearbystores_v2.appconfig.AppConfig;
import com.voyager.nearbystores_v2.classes.Message;
import com.voyager.nearbystores_v2.controllers.sessions.SessionsController;

import org.json.JSONObject;

/**
 * Created by Amine on 1/26/2018.
 */

public class InComingDataParserSender {

    public static String TAG_NEED_OPEN_LIST_DISCUSSIONS="need_open_list_discussions";
    public static String TAG_NEED_OPEN_INBOX="need_open_inbox";


    public static void parseAndSend(final Context context , final JSONObject data){

        if(MainActivity.isOpend()==false){

            Intent intent  = new Intent(context, SplashActivity.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("chat",true);

            String message ="" ;
            Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

            if(MessengerHelper.NbrMessagesManager.getNbrTotalDiscussion()>1
                    && MessengerHelper.NbrMessagesManager.getNbrTotalMessages()>1){

                message = String.format(context.getString(R.string.youHaveDiscussions),
                        MessengerHelper.NbrMessagesManager.getNbrTotalDiscussion(),
                        MessengerHelper.NbrMessagesManager.getNbrTotalMessages());

                //intent.putExtra(TAG_NEED_OPEN_INBOX,false);
                intent.putExtra(TAG_NEED_OPEN_LIST_DISCUSSIONS,true);
                defaultSoundUri = null;


            }else{

                Message messageData = MessengerHelper.parshToObj( data.toString());
                //MANAGE NBR MESSAGES
                MessengerHelper.NbrMessagesManager.upNbrDiscussion(messageData.getDiscussionId());

                message = context.getString(R.string.youHaveMessage);
                //intent.putExtra(TAG_NEED_OPEN_INBOX,false);
                intent.putExtra(TAG_NEED_OPEN_LIST_DISCUSSIONS,true);
                defaultSoundUri=  Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                        + "://" + AppController.getInstance().getApplicationContext().getPackageName() + "/raw/all_eyes_on_me");

            }

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,
                    PendingIntent.FLAG_ONE_SHOT);

            final NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(context.getResources().getString(R.string.app_name))
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);

            if(defaultSoundUri!=null)
                notificationBuilder.setSound(defaultSoundUri);

            final NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);




            //check user login
            if(SessionsController.getLocalDatabase.isLogged()) {

                boolean notif_messenger = PreferenceManager.getDefaultSharedPreferences(context)
                        .getBoolean("notif_messenger", true);

                if(notif_messenger){
                    Pusher pusher = new Pusher(Pusher.MESSAGE, data.toString());
                    Message messageData = MessengerHelper.pushMessageInsideUi(
                            pusher,
                            SessionsController.getLocalDatabase.getUserId(),
                            false
                    );
                    notificationManager.notify(0, notificationBuilder.build());
                }

            }


        }else {

            try {


                //get default realm
                //check user is connected

                if(AppConfig.APP_DEBUG)
                    Log.e("getLocalDatabase-1", String.valueOf(SessionsController.getLocalDatabase.getUserId()));

                if(SessionsController.getLocalDatabase.isLogged()) {

                    if(AppConfig.APP_DEBUG)
                        Log.e("onMessageReceived-1",data.toString());

                    Pusher pusher = new Pusher(Pusher.MESSAGE, data.toString());
                    Message message = MessengerHelper.pushMessageInsideUi(pusher, SessionsController.getLocalDatabase.getUserId());
                    MessengerHelper.NbrMessagesManager.upNbrDiscussion(message.getDiscussionId());

                    BusStation.getBus().post( message );

//                    if(!user.isBlocked())
//                        BusStation.getBus().post( message );
                }



            }catch (Exception e){
                e.printStackTrace();
            }

        }

    }



}


