using System.Collections;
using System.Collections.Generic;
using System.Runtime.InteropServices;
using UnityEngine;

public class MobileCallBack : MonoBehaviour
{

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
        AddCube();
    }


#elif UNITY_ANDROID

    private AndroidJavaObject _pluginActivity;

    public UnityEngine.UI.InputField input;
    public UnityEngine.UI.Button sendButton;
    public UnityEngine.UI.Button instanceNutton;

    private void Start()
    {
        ins();
        instanceNutton.onClick.AddListener(() => { CallUnityTest(); });
        sendButton.onClick.AddListener(() => { ShowToast(); });
    }

    private void ins()
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
        InitScreenShootDetect();


    }

    //public void Add()
    //{
    //    if (_pluginActivity !=null)
    //    {
    //        var result = _pluginActivity.Call<int>("Add", 7, 8);
    //        text.text = result.ToString();
    //        Debug.Log("OutPut" + result);
    //    }
    //}

    private void InitScreenShootDetect()
    {
        if (_pluginActivity == null) return;
        Debug.Log("InitScreenShootDetect");
        _pluginActivity.Call("InitScreenShootDetect");
    }


    private void CallUnity(string message)
    {
        Debug.Log(message);
    }

    public void ScreenShooting(string str)
    {
        if (_pluginActivity == null) return;
        Debug.Log("Shoot");
        AddCube();
    }

    private void CallUnityTest()
    {
        if (_pluginActivity == null) return;
        Debug.Log("CallBack");
        _pluginActivity.Call("CallBack");
    }

    private void ShowToast()
    {
        if (_pluginActivity == null) return;
        _pluginActivity.Call("ShowToast", input.text); 
    }



#else

    private void Update()
    {
        if (Input.GetKeyDown(KeyCode.A))
        {
            AddCube();
        }
    }

#endif

    private void AddCube()
    {
        var ins = GameObject.CreatePrimitive(PrimitiveType.Cube);
        ins.transform.position = Vector3.zero;
        ins.AddComponent<Rigidbody>();

        Destroy(ins, 5);
    }


}
