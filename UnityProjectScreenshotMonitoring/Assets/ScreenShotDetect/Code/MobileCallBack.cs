using System.Collections;
using System.Collections.Generic;
using System.Runtime.InteropServices;
using UnityEngine;
using System;

#if UNITY_ANDROID
using UnityEngine.Android;
#endif

[HelpURL("https://github.com/zer0012/UnityMobileScreenshotMonitoring-")]

public class MobileCallBack : MonoBehaviour
{
    /// <summary>
    /// 截图调用
    /// </summary>
    public Action Shoot;

#if UNITY_IOS

    [DllImport("__Internal")]
    private static extern void InitScreenCaptureMonitoring();

    // Start is called before the first frame update
    void Awake()
    {
        Debug.Log("InitScreenCaptureMonitoringForIOS");
        InitScreenCaptureMonitoring();
    }
    
    private void Callbake_iosMessage(string message)
    {
        Shoot?.Invoke();
        Debug.Log("Shoot");
    }


#elif UNITY_ANDROID

    private AndroidJavaObject _pluginActivity;

    //初始化插件
    public void Init()
    {
        FileAccessPermissions();
        InstallCallAndroid();
    }

    /// <summary>
    /// 初始化安卓调用
    /// </summary>
    private void InstallCallAndroid()
    {
        try
        {
            _pluginActivity = new AndroidJavaObject("com.android.unitynativecall.NativeCall");
        }
        catch (System.Exception)
        {
            Debug.LogError("Can't InstanceAndroidJavaObject");
            throw;
        }

    }

    /// <summary>
    /// 请求文件读取权限
    /// </summary>
    private void FileAccessPermissions()
    {
        if (!Permission.HasUserAuthorizedPermission(Permission.ExternalStorageRead))
        {
            Permission.RequestUserPermission(Permission.ExternalStorageRead);
        }
    }

    enum ShootingType
    {
        StartShootDetect,//初始化并开始监听截图
        StopShootDetect,//停止监听截图
        Shoot//截图
    }

    /// <summary>
    /// Android监测到截图后调用此方法
    /// </summary>
    /// <param name="str"></param>
    public void Shooting(string str)
    {
        if (_pluginActivity == null) return;

        ShootingType shootingType;
        var typ = System.Enum.TryParse(str, out shootingType);
        if (!typ) return;

        switch (shootingType)
        {
            case ShootingType.StartShootDetect:
                Debug.Log("StartShootDetect");
                break;
            case ShootingType.StopShootDetect:
                Debug.Log("StopShootDetect");
                break;
            case ShootingType.Shoot:
                Shoot?.Invoke();
                Debug.Log("Shoot");
                break;
            default:
                Debug.LogError("ErrorEnumType");
                break;
        }

    }

#endif

}
