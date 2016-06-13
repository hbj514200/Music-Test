package com.a1336892373qq.musictest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


public class MainActivity extends Activity implements View.OnTouchListener {

    private MediaPlayer player = new MediaPlayer();
    private Button playButton;
    private Button pauseButton;
    private Button stopButton;
    private Button killButton;
    private Button aboutButton;
    private ImageView singer;
    private ImageView touxiang;
    private HorizontalScrollView henList;
    private ImageView signUpButton;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //判断DPI高低做适配
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        double ysize = point.y;
        double xsize = point.x;
        Toast.makeText(MainActivity.this, ysize+"", Toast.LENGTH_LONG).show();
        if(ysize==1776)     setContentView(R.layout.activity_main2);              //nexus5      适配完毕
        if(ysize==1794)     setContentView(R.layout.activity_main3);              //nexus5x     适配完毕
        if(ysize==1184)     setContentView(R.layout.activity_main4);              //Galaxy720P  适配完毕
        if(xsize==480)      setContentView(R.layout.activity_main5);              //主页正常

        touxiang = (ImageView) findViewById(R.id.touxiang);
        layout = (LinearLayout) findViewById(R.id.layout_main);
        playButton = (Button) findViewById(R.id.play);
        pauseButton = (Button) findViewById(R.id.pause);
        stopButton = (Button) findViewById(R.id.stop);
        killButton = (Button) findViewById(R.id.kill);
        aboutButton = (Button) findViewById(R.id.aboutme);
        singer = (ImageView) findViewById(R.id.singer);
        henList = (HorizontalScrollView) findViewById(R.id.henlist);
        signUpButton = (ImageView) findViewById(R.id.signUp);
        touxiang.setOnTouchListener(this);
        singer.setOnTouchListener(this);
        playButton.setOnTouchListener(this);
        pauseButton.setOnTouchListener(this);
        stopButton.setOnTouchListener(this);
        killButton.setOnTouchListener(this);
        aboutButton.setOnTouchListener(this);
        signUpButton.setOnTouchListener(this);

        final RotateAnimation animation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(40000);       //设置动画持续时间
        animation.setRepeatCount(-1); //设置重复次数
        animation.setFillAfter(true); //动画执行完后是否停留在执行完的状态
        animation.setStartOffset(0); //执行前的等待时间
        touxiang.setAnimation(animation);
        animation.startNow();

        initplayer();

    }

    protected void initplayer() {
        try {
            player = MediaPlayer.create(MainActivity.this, R.raw.music);
            player.setLooping(true);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "初始化报错！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.play:
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    playButton.setBackgroundResource(R.drawable.bofang_1);
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    playButton.setBackgroundResource(R.drawable.start_2);
                    if (!player.isPlaying()) {
                        Toast.makeText(MainActivity.this, "享受音乐", Toast.LENGTH_SHORT).show();
                        player.start();
                    }
                }
                break;
            case R.id.pause:
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    pauseButton.setBackgroundResource(R.drawable.pause_1);
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    pauseButton.setBackgroundResource(R.drawable.pause_2);
                    if (player.isPlaying()) player.pause();
                }
                break;
            case R.id.stop:
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    stopButton.setBackgroundResource(R.drawable.stop_1);
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    stopButton.setBackgroundResource(R.drawable.stop_2);
                    if (player.isPlaying()) {
                        player.reset();
                        initplayer();
                    }
                }
                break;
            case R.id.kill:
                killButton.setBackgroundResource(R.drawable.back_2);
                finish();
                break;
            case R.id.aboutme:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    aboutButton.setBackgroundResource(R.drawable.aboutme_2);
                    Intent intent = new Intent(MainActivity.this, Aboutme.class);
                    startActivity(intent);
                }
                if (event.getAction() == MotionEvent.ACTION_UP)
                    aboutButton.setBackgroundResource(R.drawable.aboutme_1);
                break;
            case R.id.touxiang:
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    touxiang.setBackgroundResource(R.drawable.touxiang_2);
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    touxiang.setBackgroundResource(R.drawable.touxiang);
                    playButton.setVisibility(View.GONE);
                    pauseButton.setVisibility(View.GONE);
                    stopButton.setVisibility(View.GONE);
                    singer.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.singer:
                singer.setVisibility(View.GONE);
                playButton.setVisibility(View.VISIBLE);
                pauseButton.setVisibility(View.VISIBLE);
                stopButton.setVisibility(View.VISIBLE);
                break;
            case R.id.signUp:
                Intent intent = new Intent(MainActivity.this, signUp.class);
                startActivity(intent);
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        player.stop();
        player.release();
        initplayer();
        super.onDestroy();
        Toast.makeText(MainActivity.this, getString(R.string.bye), Toast.LENGTH_SHORT).show();
    }

}
