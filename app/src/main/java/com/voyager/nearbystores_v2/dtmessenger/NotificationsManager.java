package com.voyager.nearbystores_v2.dtmessenger;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.voyager.nearbystores_v2.R;
import com.voyager.nearbystores_v2.activities.MainActivity;
import com.voyager.nearbystores_v2.activities.SplashActivity;
import com.voyager.nearbystores_v2.appconfig.AppContext;
import com.voyager.nearbystores_v2.classes.Message;
import com.voyager.nearbystores_v2.controllers.sessions.SessionsController;
import com.voyager.nearbystores_v2.dtmessenger.socket.SocketService;
import com.voyager.nearbystores_v2.utils.Translator;

import java.util.List;

/**
 * Created by Amine on 10/9/2016.
 */

public class NotificationsManager {


    public static String TAG_NEED_TO_OPEN_NOTIFICATION="open_notification";


    public static void pushNotifnewMessage(Context context, List<Message> messages){
        //prepare intent
        try {

            Intent resultIntent;

            if(MainActivity.isOpend()==true){
                resultIntent = new Intent();
            }else{
                resultIntent = new Intent(context, SplashActivity.class);
            }

            resultIntent.putExtra(TAG_NEED_TO_OPEN_NOTIFICATION,false);
            resultIntent.putExtra(SocketService.TAG_NEED_OPEN_LIST_DISCUSSIONS,true);

            String message = "";
            Uri defaultSoundUri;

            if(messages.size()>1){
                message = Translator.print("You have %d messages",null, MessengerHelper.NbrMessagesManager.getNbrTotalMessages());
                //intent.putExtra(TAG_NEED_OPEN_INBOX,false);
                defaultSoundUri = null;

            }else{

                message = Translator.print("You have new message");
                defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            }

            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, resultIntent,
                    PendingIntent.FLAG_ONE_SHOT);

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setContentTitle(context.getResources().getString(R.string.app_name))
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);


            if(defaultSoundUri!=null)
                notificationBuilder.setSound(defaultSoundUri);


            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            try {
                if(SessionsController.isLogged()) {

                    MessengerHelper.updateInbox(messages);
                    notificationManager.notify(2002, notificationBuilder.build());

                }
            }catch (Exception e){
                if(AppContext.DEBUG)
                    e.printStackTrace();
            }


        }catch (Exception e){
            if(AppContext.DEBUG)
                e.printStackTrace();
        }

    }



    public static void customView(Context context){

        RemoteViews contentView = new RemoteViews(context.getPackageName(), R.layout.custom_view_notification);
        contentView.setImageViewResource(R.id.image, R.mipmap.ic_launcher);
//        contentView.setTextViewText(R.id.title, "Custom notification");
//        contentView.setTextViewText(R.id.text, "This is a custom layout");

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContent(contentView);

        Notification notification = mBuilder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.defaults |= Notification.DEFAULT_SOUND;
        notification.defaults |= Notification.DEFAULT_VIBRATE;


        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.notify(201, notification);
    }

}
