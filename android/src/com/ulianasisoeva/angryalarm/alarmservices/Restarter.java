package com.ulianasisoeva.angryalarm.alarmservices;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.ulianasisoeva.angryalarm.screen.TabLayoutActivity;

import java.util.Calendar;

public class Restarter extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onReceive(final Context context, Intent intent) {
        Log.i("Broadcast Listened", "start broadcaster");

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        AlarmManager.OnAlarmListener alarmListener = new AlarmManager.OnAlarmListener() {
            @Override
            public void onAlarm() {
                final MediaPlayer player = MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
                player.setLooping(true); // Set looping
                player.setVolume(100, 100);
                player.start();
                Log.i("Broadcast Listened", "start ringtone");
            }
        };
        Handler targetHandler = new Handler();


        Calendar currentCalendar = Calendar.getInstance();
        Calendar calendar = (Calendar) intent.getSerializableExtra("calendar");
        if (currentCalendar.getTimeInMillis() <= calendar.getTimeInMillis()) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), "Alarm", alarmListener, targetHandler);
        }

//        Intent launchIntent = new Intent(context, TabLayoutActivity.class);
//        launchIntent.setFlags(
//                Intent.FLAG_ACTIVITY_REORDER_TO_FRONT |
//                        Intent.FLAG_ACTIVITY_NEW_TASK |
//                        Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
//        context.startActivity(launchIntent);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            context.startForegroundService(new Intent(context, AlarmService.class));
//        } else {
//            context.startService(new Intent(context, AlarmService.class));
//        }

//        PackageManager pm = context.getPackageManager();

//        Intent launchIntent = context
//                .getPackageManager()
//                .getLaunchIntentForPackage(context.getPackageName());
//
//        launchIntent.setFlags(
//                Intent.FLAG_ACTIVITY_REORDER_TO_FRONT |
//                        Intent.FLAG_ACTIVITY_NEW_TASK |
//                        Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
//        );
//        context.startActivity(launchIntent);
    }
}