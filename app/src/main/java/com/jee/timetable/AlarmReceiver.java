package com.jee.timetable;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String label = intent.getStringExtra("label");
        String sub   = intent.getStringExtra("sub");
        String emoji = intent.getStringExtra("emoji");

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        if (pm != null) {
            PowerManager.WakeLock wl = pm.newWakeLock(
                PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP,
                "JEETimetable:WakeLock");
            wl.acquire(60000);
        }

        Intent service = new Intent(context, AlarmService.class);
        service.putExtra("label", label);
        service.putExtra("sub", sub);
        service.putExtra("emoji", emoji);
        context.startForegroundService(service);

        Intent activity = new Intent(context, AlarmActivity.class);
        activity.putExtra("label", label);
        activity.putExtra("sub", sub);
        activity.putExtra("emoji", emoji);
        activity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(activity);
    }
}
