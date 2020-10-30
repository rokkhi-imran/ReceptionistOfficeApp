package com.rokkhi.receptionistofficeapp.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.rokkhi.receptionistofficeapp.R;


public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.e(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            handleNow(remoteMessage);
            Log.e(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

    }


    @Override
    public void onNewToken(String token) {
        Log.e(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token);
    }


    private void handleNow(RemoteMessage remoteMessage) {

        String messageTitle = remoteMessage.getNotification().getTitle();
        String messageBody = remoteMessage.getNotification().getBody();


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, "home")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(messageBody))
                .setLights(Color.WHITE, 3000, 3000)
                .setPriority(NotificationCompat.PRIORITY_MAX);

        int mNotificationID = (int) System.currentTimeMillis();



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel("home",
                    "Default channel", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
            manager.notify(mNotificationID, mBuilder.build());
        } else {

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
            notificationManagerCompat.notify(mNotificationID, mBuilder.build());
        }

        Log.e(TAG, "Short lived task is done. handle now");
    }



    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }


}
