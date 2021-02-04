package com.example.notification24112020;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mBtnNotify;
    String CHANNEL_ID = "MY_CHANNEL";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnNotify = findViewById(R.id.buttonNotification);

        mBtnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder notify = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                                    .setSmallIcon(android.R.drawable.btn_star)
                                    .setShowWhen(true)
                                    .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.hinhnotification))
                                    .setContentTitle("Thông báo mới")
                                    .setContentText("Có phiên bản mới")
                                    .setPriority(2)
                                    .setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.tetdongday));

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    NotificationChannel notificationChannel = new NotificationChannel(
                            CHANNEL_ID,
                            "Notification",
                            NotificationManager.IMPORTANCE_HIGH
                            );
                    AudioAttributes audioAttributes = new AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                            .build();
                    notificationChannel.setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.tetdongday),audioAttributes);
                    notificationChannel.enableVibration(true);
                    notificationChannel.enableLights(true);
                    notificationChannel.setLightColor(Color.RED);
                    notificationManager.createNotificationChannel(notificationChannel);
                }


                notificationManager.notify(1 , notify.build());

            }
        });
    }
}