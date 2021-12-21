package com.android.unitynativecall;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.telecom.Call;
import android.telecom.CallScreeningService;
import android.util.Log;
import android.widget.Toast;

import android.graphics.Bitmap;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.unity3d.player.UnityPlayer;

public class NativeCall extends UnityPlayerActivity {

    public void ShowToast(String msg)
    {
        Toast.makeText(UnityPlayer.currentActivity, msg, Toast.LENGTH_SHORT).show();
    }


    public void StartShootDetect()
    {
        onResume();
    }

    public void StopShootDetect()
    {
        onResume();
    }

    public void CallBack()
    {
        CallUnityTest();
    }

    public void CallUnityTest()
    {
        UnityPlayer.UnitySendMessage("PluginCallBack","CallUnity","TestCall");
    }

    public void ScreenShooting()
    {
        UnityPlayer.UnitySendMessage("PluginCallBack","ScreenShooting","Shoot");
    }



    private Context mContext;
    private ScreenShotListenManager screenShotListenManager;
    private boolean isHasScreenShotListener = false;
    private String path;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(setViewId());
        screenShotListenManager = ScreenShotListenManager.newInstance(this);
        mContext = this;
    }


    @Override
    protected void onResume() {
        super.onResume();
        startScreenShotListen();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopScreenShotListen();
    }

    /**
     * 监听
     */
    private void startScreenShotListen() {
        if (!isHasScreenShotListener && screenShotListenManager != null) {
            screenShotListenManager.setListener(new ScreenShotListenManager.OnScreenShotListener() {
                @Override
                public void onShot(String imagePath) {

                    path = imagePath;
                    Log.d("msg", "BaseActivity -> onShot: " + "获得截图路径：" + imagePath);

                    MyDialog ksDialog = MyDialog.getInstance()
                            .init(NativeCall.this, R.layout.dialog_layout)
                            .setCancelButton("取消", null)
                            .setPositiveButton("生成新图片", new MyDialog.OnClickListener() {
                                @Override
                                public void OnClick(View view) {
                                    //Bitmap screenShotBitmap = screenShotListenManager.createScreenShotBitmap(mContext, path);
                                    // 此处只要分享这个合成的Bitmap图片就行了
                                    // 为了演示，故写下面代码
                                    //screenShotIv.setImageBitmap(screenShotBitmap);
                                }
                            });

                    //screenShotIv = (ImageView) ksDialog.getView(R.id.iv);
                    //progressBar = (ProgressBar) ksDialog.getView(R.id.avLoad);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //progressBar.setVisibility(View.GONE);
                            //Glide.with(mContext).load(path).into(screenShotIv);

                        }
                    }, 1500);
                }
            });
            screenShotListenManager.startListen();
            isHasScreenShotListener = true;
        }
    }

    /**
     * 停止监听
     */
    private void stopScreenShotListen() {
        if (isHasScreenShotListener && screenShotListenManager != null) {
            screenShotListenManager.stopListen();
            isHasScreenShotListener = false;
        }
    }

}
