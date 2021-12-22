package com.android.androidnativecall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    TextView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (TextView) findViewById(R.id.text1);
        listView.setText("Init");

        Log.w("Log","Init");
        startScreenShotDetect();

//        String [] permission = new String[1];
//        permission[0] = "android.permission.READ_EXTERNAL_STORAGE";
//
//        int [] grant = new int[1];
//        grant[0] = 0;
//
//        super.onRequestPermissionsResult(,permission,grant);
        requestPower();
    }

    public void requestPower() {
//判断是否已经赋予权限
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //如果应用之前请求过此权限但用户拒绝了请求，此方法将返回 true。
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {//这里可以写个对话框之类的项向用户解释为什么要申请权限，并在对话框的确认键后续再次申请权限.它在用户选择"不再询问"的情况下返回false
            } else {
                //申请权限，字符串数组内是一个或多个要申请的权限，1是申请权限结果的返回参数，在onRequestPermissionsResult可以得知申请结果
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,}, 1);
            }
        }
    }


    private void startScreenShotDetect()
    {
        // Requires Permission: android.permission.READ_EXTERNAL_STORAGE
        ScreenShotListenManager manager = ScreenShotListenManager.newInstance(this);

        manager.setListener(
                new ScreenShotListenManager.OnScreenShotListener() {
                    public void onShot(String imagePath) {
                        listView.setText("Shoot");
                    }
                });

        manager.startListen();

        //manager.stopListen();
    }

}