package com.jee.timetable;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final int[][] SLOT_TIMES = {
        {6,30},{7,0},{7,20},{9,20},{10,50},{11,10},{12,40},
        {13,10},{14,10},{14,40},{15,40},{16,0},{21,30},
        {22,0},{22,40},{23,10},{23,30}
    };
    private static final String[] SLOT_LABELS = {
        "Morning Routine","Meditation","Homework Block 1",
        "Extra Practice Block 1","Break 1","Extra Practice Block 2",
        "Lunch","Homework Block 2","Revision Block 1",
        "Extra Practice Block 3","Homework Block 3","Lectures",
        "Break 2 + Dinner","Homework Block 4","Revision Block 2",
        "Wind Down","Sleep"
    };
    private static final String[] SLOT_SUBS = {
        "30 min - Freshen up","20 min - Meditation",
        "2 hr - Homework","1.5 hr - Extra Practice",
        "20 min - Break","1.5 hr - Extra Practice",
        "30 min - Lunch","1 hr - Homework",
        "30 min - Revision","1 hr - Extra Practice",
        "20 min - Homework","5.5 hr - Lectures",
        "30 min - Dinner","40 min - Homework",
        "30 min - Revision","20 min - Wind Down","7 hr - Sleep"
    };
    private static final String[] SLOT_EMOJIS = {
        "🌅","🧘","📝","💡","☕","💡","🍱",
        "📝","🔁","💡","📝","📚","🍽","📝","🔁","🌙","😴"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = findViewById(R.id.webview);
        WebSettings s = webView.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setMediaPlaybackRequiresUserGesture(false);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("file:///android_asset/timetable.html");
        scheduleAlarms();
    }

    public void scheduleAlarms() {
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if (am == null) return;
        for (int i = 0; i < SLOT_TIMES.length; i++) {
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, SLOT_TIMES[i][0]);
            cal.set(Calendar.MINUTE, SLOT_TIMES[i][1]);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            if (cal.getTimeInMillis() <= System.currentTimeMillis())
                cal.add(Calendar.DAY_OF_YEAR, 1);
            Intent intent = new Intent(this, AlarmReceiver.class);
            intent.putExtra("label", SLOT_LABELS[i]);
            intent.putExtra("sub", SLOT_SUBS[i]);
            intent.putExtra("emoji", SLOT_EMOJIS[i]);
            PendingIntent pi = PendingIntent.getBroadcast(
                this, i, intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
            am.setAlarmClock(new AlarmManager.AlarmClockInfo(cal.getTimeInMillis(), pi), pi);
        }
    }
}
