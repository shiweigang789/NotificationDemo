package com.lovely.pig.notificationdemo;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by sli on 2016/11/24.
 */

public class MyNotificationListenerService extends NotificationListenerService {

    private static final String TAG = MyNotificationListenerService.class.getSimpleName();

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        Log.d("TAG", "onNotificationPosted=" + sbn.toString());
		Toast.makeText(this, "onNotificationPosted=" + sbn.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
        Log.d("TAG", "onNotificationRemoved=" + sbn.toString());
		Toast.makeText(this, "onNotificationRemoved=" + sbn.toString(), Toast.LENGTH_SHORT).show();
    }

}