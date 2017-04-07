package guy.droid.im.homewidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.WindowManager;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */
public class Sample extends AppWidgetProvider {

    private static final String ACTION_BROADCASTWIDGETSAMPLE = "ACTION_BROADCASTWIDGETSAMPLE";
    final static String MY_ACTION = "MY_ACTION";
    TextView textView;
    RemoteViews views;
    String G_VAL = "";


    void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                         int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object

        Intent intent = new Intent(context, MainActivity.class);

        intent.setAction(ACTION_BROADCASTWIDGETSAMPLE);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);


        views = new RemoteViews(context.getPackageName(), R.layout.sample);
        views.setTextViewText(R.id.texta, "TEST");

        views.setOnClickPendingIntent(R.id.texta, pendingIntent);


        // Instruct the widget manager to update the widget

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public void Displayer() {
        views.setTextViewText(R.id.texta, "RAZik");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (ACTION_BROADCASTWIDGETSAMPLE.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.sample);
            views.setTextViewText(R.id.texta, getCurrentDateTime());
            views.setImageViewBitmap(R.id.image_one, BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_round));

            // This time we don't have widgetId. Reaching our widget with that way
            ComponentName appWidget = new ComponentName(context, Sample.class);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

            // Update the widget
            appWidgetManager.updateAppWidget(appWidget, views);
        } else if (MY_ACTION.equals(intent.getAction())) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.sample);
            views.setTextViewText(R.id.texta, getCurrentDateTime());
            views.setImageViewBitmap(R.id.image_one, BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_round));

            // This time we don't have widgetId. Reaching our widget with that way
            ComponentName appWidget = new ComponentName(context, Sample.class);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

            // Update the widget
            appWidgetManager.updateAppWidget(appWidget, views);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
        context.startService(new Intent(context.getApplicationContext(), MyService.class));

    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
        Toast.makeText(context, "D", Toast.LENGTH_SHORT).show();
        context.stopService(new Intent(context.getApplicationContext(), MyService.class));
    }

    private String getCurrentDateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }


}

