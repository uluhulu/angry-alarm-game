package com.ulianasisoeva.angryalarm.alarmservices;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.ulianasisoeva.angryalarm.model.Repository;
import com.ulianasisoeva.angryalarm.model.domain.Alarm;
import com.ulianasisoeva.angryalarm.screen.AlarmSettingsActivity;
import com.ulianasisoeva.angryalarm.screen.AndroidLauncher;
import com.ulianasisoeva.angryalarm.screen.MainActivity;

import java.util.ArrayList;
import java.util.Calendar;

public class AlarmService extends Service {
    MediaPlayer player;

    public int counter = 0;

    Repository repository;

    @Override
    public void onCreate() {
//        super.onCreate();
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O)
//            startMyOwnForeground();
//        else
//            startForeground(1, new Notification());
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private void startMyOwnForeground() {
        String NOTIFICATION_CHANNEL_ID = "example.permanence";
        String channelName = "Background Service";
        NotificationChannel chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_NONE);
        chan.setLightColor(Color.BLUE);
        chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        manager.createNotificationChannel(chan);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        Notification notification = notificationBuilder.setOngoing(true)
                .setContentTitle("App is running in background")
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        startForeground(2, notification);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        repository = new Repository(this);
//        ArrayList<Alarm> alarms = repository.getAlarms();
//
//        for (int i = 0; i < alarms.size(); i++) {
//            Alarm alarm = alarms.get(i);
//            Calendar calendar = Calendar.getInstance();
//            int hours = Integer.parseInt(alarm.time.split(":")[0]);
//            int minutes = Integer.parseInt(alarm.time.split(":")[1]);
//
//            System.out.println("time - " + hours + " : " + minutes);
//            System.out.println("calendar - " + calendar.toString());
//            calendar.set(calendar.get(Calendar.YEAR),
//                    calendar.get(Calendar.MONTH),
//                    calendar.get(Calendar.DAY_OF_MONTH),
//                    hours,
//                    minutes,
//                    0
//            );
//
//            player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
//            player.setLooping(true); // Set looping
//            player.setVolume(100, 100);
//            if (alarm.isEnable) {
//                Intent broadcastIntent = new Intent();
//                broadcastIntent.setAction("restartservice");
//                broadcastIntent.setClass(this, Restarter.class);
//                startAlarm(calendar, broadcastIntent);
//            }
//        }
        return START_STICKY;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void startAlarm(final Calendar calendar, final Intent broadcastIntent) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        AlarmManager.OnAlarmListener alarmListener = new AlarmManager.OnAlarmListener() {
            @Override
            public void onAlarm() {
                player.start();
                AlarmService.this.sendBroadcast(broadcastIntent);
            }
        };
        Handler targetHandler = new Handler();


        Calendar currentCalendar = Calendar.getInstance();
        if (currentCalendar.getTimeInMillis() <= calendar.getTimeInMillis()) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), "Alarm", alarmListener, targetHandler);
        }
    }

    public void stopMediaPlayer() {
        if (player != null) {
            player.stop();
            player = null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        stopMediaPlayer();
//
//        Intent broadcastIntent = new Intent();
//        broadcastIntent.setAction("restartservice");
//        broadcastIntent.setClass(this, Restarter.class);
//        this.sendBroadcast(broadcastIntent);
    }


}

