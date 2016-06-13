package com.a1336892373qq.musictest;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class signUp extends Activity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_signup);

        Button loginButton = (Button) findViewById(R.id.login);
        intent = new Intent(signUp.this, xuanchuanContent.class);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(signUp.this);
                builder.setTitle("小傻逼");
                builder.setMessage("你一直在我心中，从不需要注册，都有一席位置！");
                builder.setCancelable(false);
                builder.setPositiveButton("我明白", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(intent);
                        finish();
                    }
                });
                builder.show();
            }
        });
    }

}
