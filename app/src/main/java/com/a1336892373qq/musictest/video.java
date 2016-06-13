package com.a1336892373qq.musictest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;
import android.widget.VideoView;


public class video extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_video);

        VideoView player = (VideoView) findViewById(R.id.videoview);
        player.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.show));
        player.start();

        new Thread() {
            public void run() {
                try {
                    sleep(6500);
                    Intent intent = new Intent(video.this, MainActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                }
            }
        }.start();


        new Thread() {
            public void run() {
                Intent intent = new Intent(video.this, xuanchuanContent.class);
                intent.putExtra("from", 1);
                try {
                    sleep(12000);
                    PendingIntent pi = PendingIntent.getActivity(video.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    Notification.Builder builder = new Notification.Builder(video.this);
                    builder.setSmallIcon(R.drawable.icon);
                    builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.icon));
                    builder.setTicker(getString(R.string.say_to_you));
                    builder.setContentTitle(getString(R.string.say_to_you));
                    builder.setContentText("请点击查看这句心里话");
                    builder.setContentIntent(pi);
                    Notification notification = builder.build();
                    manager.notify(1, notification);
                    finish();
                } catch (Exception e) {
                    startActivity(intent);
                }
            }
        }.start();

    }

}
