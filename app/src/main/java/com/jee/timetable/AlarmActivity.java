package com.jee.timetable;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class AlarmActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(
            WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON |
            WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
            WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
            WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        setContentView(R.layout.activity_alarm);

        String label = getIntent().getStringExtra("label");
        String sub   = getIntent().getStringExtra("sub");
        String emoji = getIntent().getStringExtra("emoji");
        if (label == null) label = "Time Slot";
        if (sub   == null) sub   = "";
        if (emoji == null) emoji = "⏰";

        ((TextView) findViewById(R.id.tv_emoji)).setText(emoji);
        ((TextView) findViewById(R.id.tv_label)).setText(label);
        ((TextView) findViewById(R.id.tv_sub)).setText(sub);

        String finalLabel = label;
        ((Button) findViewById(R.id.btn_stop)).setOnClickListener(v -> {
            stopService(new Intent(this, AlarmService.class));
            finish();
        });
    }
}
