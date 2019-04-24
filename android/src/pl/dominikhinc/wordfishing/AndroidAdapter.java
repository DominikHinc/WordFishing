package pl.dominikhinc.wordfishing;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;


import pl.dominikhinc.wordfishing.service.NotificationHandler;

public class AndroidAdapter implements NotificationHandler {

    private AndroidLauncher androidLauncher;
    public AndroidAdapter(AndroidLauncher androidLauncher) {
        this.androidLauncher = androidLauncher;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void showNotification(String title, String text ,int secs, int id) {
        AlarmManager alarmManager = (AlarmManager) androidLauncher.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(androidLauncher, AlertReceiver.class);
        intent.putExtra("title",title);
        intent.putExtra("text",text);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(androidLauncher, id, intent, 0);


        alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+1000*secs, pendingIntent);
    }
    public void cancelNotification(int id){
        AlarmManager alarmManager = (AlarmManager) androidLauncher.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(androidLauncher, AlertReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(androidLauncher, id, intent, 0);

        alarmManager.cancel(pendingIntent);
    }
}
