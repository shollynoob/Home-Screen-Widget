package guy.droid.im.homewidget;

/**
 * Created by admin on 4/7/2017.
 */

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class MyService extends Service {
    final static String MY_ACTION = "MY_ACTION";
    public static final long NOTIFY_INTERVAL = 1 * 1000; // 10 seconds
    android.os.Handler handler = new android.os.Handler();
    java.util.Timer timer = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Toast.makeText(getApplicationContext(),"STARTED",Toast.LENGTH_SHORT).show();
        //  return super.onStartCommand(intent, flags, startId);
        return START_NOT_STICKY;

    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (timer != null) {
            timer.cancel();
        } else {
            timer = new java.util.Timer();
        }
        Toast.makeText(getApplicationContext(), "STARTED", Toast.LENGTH_LONG).show();
        timer.scheduleAtFixedRate(new Timer(), 0, NOTIFY_INTERVAL);

    }

    @Override
    public void onDestroy() {
        Toast.makeText(getApplicationContext(), "STOPPED", Toast.LENGTH_SHORT).show();
        super.onDestroy();
        timer.cancel();
    }

    class Timer extends TimerTask {

        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), Sample.class);
                    intent.setAction(MY_ACTION);
                    // And this time we are sending a broadcast with getBroadcast
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    sendBroadcast(intent);
                }
            });
        }

        private String getDateTime() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("[yyyy/MM/dd - HH:mm:ss]");
            return simpleDateFormat.format(new Date());
        }
    }
}