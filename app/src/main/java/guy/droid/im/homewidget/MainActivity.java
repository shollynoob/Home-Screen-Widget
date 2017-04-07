package guy.droid.im.homewidget;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private static final String ACTION_BROADCASTWIDGETSAMPLE = "ACTION_BROADCASTWIDGETSAMPLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void BroadCast(View view) {
        Intent intent = new Intent(getApplicationContext(), Sample.class);
        intent.setAction(ACTION_BROADCASTWIDGETSAMPLE);
        // And this time we are sending a broadcast with getBroadcast
        //  PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        sendBroadcast(intent);

        // startService(new Intent(getApplicationContext(), MyService.class));
    }
}
