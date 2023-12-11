package com.example.academicagendav1;

import static com.example.academicagendav1.EventNotificationManager.CHANNEL_ID;
import static com.example.academicagendav1.EventNotificationManager.NOTIFICATION_ID;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class EventNotificationManager {

    protected static final String CHANNEL_ID = "event_channel";
    protected static final int NOTIFICATION_ID = 1;

    private final Context context;

    public EventNotificationManager(Context context) {
        this.context = context;
        createNotificationChannel();
    }


    // Create Notification Channel
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Event Channel";
            String description = "Channel for event notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.createNotificationChannel(channel);
        }
    }
    }

    // Broadcast Receiver for handling the notification
    class EventNotificationReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String eventName = intent.getStringExtra("eventName");
            long eventDate = intent.getLongExtra("eventDate", 0);
            String eventDescription = intent.getStringExtra("eventDescription");

            showNotification(context, eventName, eventDate, eventDescription);
        }

        private void showNotification(Context context, String eventName, long eventDate, String eventDescription) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentTitle(eventName)
                    .setContentText("Event on " + eventDate + ": " + eventDescription)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            /*
             TODO: Consider calling
                ActivityCompat#requestPermissions
             here to request the missing permissions, see the documentation
             for ActivityCompat#requestPermissions for more details.
            */
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                notificationManager.notify(NOTIFICATION_ID, builder.build());
            }

        }
    }


