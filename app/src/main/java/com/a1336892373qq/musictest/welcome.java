package com.a1336892373qq.musictest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


public class welcome extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        new Thread() {
            public void run() {
                try {
                    sleep(1800);
                    Intent intent = new Intent(welcome.this, video.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                }
            }
        }.start();

    }

}
