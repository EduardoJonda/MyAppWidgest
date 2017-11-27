package com.android.jonda.seichi.c15.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.android.jonda.seichi.c15.myappwidgest.MainActivity;
import com.android.jonda.seichi.c15.myappwidgest.R;

/**
 * Created by lucho on 27/11/2017.
 */

public class MyJobService extends JobService {

    private static final String TAG = MyJobService.class.getSimpleName();

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d(TAG, "Calling onStartJob...");

        // TODO...

        // Notification Builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this  );

        // Intent
        Intent intent =  new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        // Notification
        Notification notification = builder
                .setContentTitle("Notifiación de MyJobService")
                .setContentText("Esto se ejecuta cada 5 a 30 segundos...")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        // Notification manager
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);

        // Play sound
        RingtoneManager.getRingtone(this, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)).play();

        return false;

    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
