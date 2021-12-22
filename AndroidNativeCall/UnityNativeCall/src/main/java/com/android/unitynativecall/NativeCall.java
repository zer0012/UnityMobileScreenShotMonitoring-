package com.android.unitynativecall;

import android.os.Bundle;
import android.widget.Toast;

import com.unity3d.player.UnityPlayer;

public class NativeCall extends UnityPlayerActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            InitScreenShotDetect();
            StartShootDetect();
        }
        catch (Exception error) {
            Toast.makeText(UnityPlayer.currentActivity, error.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    ScreenShotListenManager manager;

    //初始化监测
    public void InitScreenShotDetect()
    {
        // Requires Permission: android.permission.READ_EXTERNAL_STORAGE
       manager = ScreenShotListenManager.newInstance(this);

       manager.setListener(
                     new ScreenShotListenManager.OnScreenShotListener() {
               public void onShot(String imagePath) {
                   ScreenShooting();//监测到截图后通知Unity
               }
           });
    }

    //开始监测（此方法只能在主线程中调用）
    public void StartShootDetect()
    {
        manager.startListen();
        UnityPlayer.UnitySendMessage("PluginCallBack","Shooting","StartShootDetect");
    }

    //停止监测
    public void StopShootDetect()
    {
        manager.stopListen();
        UnityPlayer.UnitySendMessage("PluginCallBack","Shooting","StopShootDetect");
    }

    //监测到截图后通知Unity
    public void ScreenShooting()
    {
        UnityPlayer.UnitySendMessage("PluginCallBack","Shooting","Shoot");
    }

}
