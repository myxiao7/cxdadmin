package com.zh.cxdadmin.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.zh.cxdadmin.R;


/**
 * 通知管理
 * Created by zhanghao on 2016/9/28.
 */
public class MyNotificationManager {

    private static MyNotificationManager notificationManager;
    private MyNotificationManager(){
    }

    public static MyNotificationManager getInstance(){
        if(notificationManager == null){
            notificationManager = new MyNotificationManager();
        }
        return notificationManager;
    }

    /**
     * 显示通知
     * @param context
     * @param title
     * @param message
     */
    public void showNotifi(Context context, String title, String message){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        builder.setContentTitle(title)
                .setContentText(message)
                .setTicker("消息")
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setSmallIcon(R.mipmap.ic_launcher);
        ((NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE)).notify(1, builder.build());
    }
}
