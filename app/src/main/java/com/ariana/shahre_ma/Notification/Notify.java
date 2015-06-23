package com.ariana.shahre_ma.Notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.ariana.shahre_ma.Jobs;
import com.ariana.shahre_ma.R;
import com.ariana.shahre_ma.WebServiceGet.HTTPGetNotificationJson;

public class Notify {
    Context context;

    public Notify(Context context){

     this.context=context;

    }

   public static void Json_Notify(){




   }


    void Add_Notify_Sql(){

    }


    public static void Notificationm(Context context){
        String test="test";
        HTTPGetNotificationJson notificationJson= new HTTPGetNotificationJson(context);
        notificationJson.execute();
        test = notificationJson.test;


        NotificationManager nm=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify=new Notification(R.mipmap.ic_launcher,"شما یک پیام دارید",System.currentTimeMillis());
        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        notify.flags |= Notification.FLAG_NO_CLEAR;
        notify.flags |= Notification.FLAG_SHOW_LIGHTS;
        notify.defaults |= Notification.DEFAULT_LIGHTS;
        notify.defaults |= Notification.DEFAULT_SOUND;
        CharSequence title="شهرما";
        CharSequence detils="اعلانات";

        Intent intent=new Intent(context,Jobs.class);
        PendingIntent pend=PendingIntent.getActivity(context, 0, intent, 0);
        notify.setLatestEventInfo(context, title, test, pend);
        nm.notify(0,notify);

    }

}