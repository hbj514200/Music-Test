package com.a1336892373qq.musictest;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class Aboutme extends Activity implements View.OnTouchListener {

    private Button backButton;
    private Button connectButton;
    private LinearLayout layout;
    private ImageView dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_aboutme);
        layout = (LinearLayout) findViewById(R.id.layout_aboutme);

        dialog = (ImageView) findViewById(R.id.my_phonenum);
        backButton = (Button) findViewById(R.id.about_back);
        connectButton = (Button) findViewById(R.id.about_connectme);
        connectButton.setOnTouchListener(this);
        backButton.setOnTouchListener(this);
        dialog.setOnTouchListener(this);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButton.setVisibility(View.VISIBLE);
                connectButton.setVisibility(View.VISIBLE);
                try{
                    layout.setBackground(ContextCompat.getDrawable(Aboutme.this, R.drawable.about_back));
                } catch (Exception e){
                    Toast.makeText(Aboutme.this, "更换背景失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean onTouch(View v, MotionEvent event) {
        switch (v.getId()) {
            case R.id.about_back:
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    backButton.setBackgroundResource(R.drawable.aboutme_back2);
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    backButton.setBackgroundResource(R.drawable.aboutme_back2);
                    finish();
                }
                break;
            case R.id.about_connectme:
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    connectButton.setBackgroundResource(R.drawable.connectme_2);
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    connectButton.setBackgroundResource(R.drawable.connectme_1);
                    dialog.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.my_phonenum :
                dialog.setVisibility(View.GONE);
                break;
            default:
                break;
        }
        return false;
    }

}