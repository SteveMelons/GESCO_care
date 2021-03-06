package com.example.gesco;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class ReminderActivity extends AppCompatActivity implements View.OnClickListener {

    private int notificationId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_view);

        // Set onClick Listener
        findViewById(R.id.setBtn).setOnClickListener(this);
        findViewById(R.id.cancelBtn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        EditText editText = findViewById(R.id.editText);
        TimePicker timePicker = findViewById(R.id.timePicker);

        // Intent
        Intent intent = new Intent(ReminderActivity.this, AlarmReceiver.class);
        intent.putExtra("notificationId", notificationId);
        intent.putExtra("message", editText.getText().toString());

        // PendingIntent
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                ReminderActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT
        );

        // AlarmManager
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        switch (view.getId()) {
            case R.id.setBtn:
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                // Create time.
                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hour);
                startTime.set(Calendar.MINUTE, minute);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();

                // Set Alarm
                alarmManager.set(AlarmManager.RTC_WAKEUP, alarmStartTime, pendingIntent);
                Toast.makeText(this, "Reminder is set!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cancelBtn:
                alarmManager.cancel(pendingIntent);
                Toast.makeText(this, "Reminder Canceled.", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}

//public class ReminderActivity extends AppCompatActivity {

//@Override
// protected void onCreate(Bundle savedInstanceState) {
//   super.onCreate(savedInstanceState);
// setContentView(R.layout.activity_reminder);


//Button button_back = (Button) findViewById(R.id.button_back);
//button_back.setOnClickListener(new View.OnClickListener() {
//  @Override
//public void onClick(View v) {
//  finish();
//}
//});

//Button button_finish = (Button) findViewById(R.id.button_finish);
// button_finish.setOnClickListener(new View.OnClickListener() {
//   @Override
// public void onClick(View v) {
//   finish();
//}
//});
//}
//}