package com.android.androidnativecall;

import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import com.unity3d.player.UnityPlayer;

class PluginActivity extends UnityPlayerActivity {
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Toast.makeText(UnityPlayer.currentActivity, "Called", Toast.LENGTH_SHORT).show();
    }


}