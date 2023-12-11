package com.example.academicagendav1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        long eventId = intent.getLongExtra("EVENT_ID", -1);
        String eventName = intent.getStringExtra("EVENT_NAME");

        if (eventId != -1) {
            // Handle the notification, e.g., show a notification using NotificationManager
            String notificationMessage = "Event Reminder: " + eventName;
            Toast.makeText(context, notificationMessage, Toast.LENGTH_SHORT).show();
        }
    }
}