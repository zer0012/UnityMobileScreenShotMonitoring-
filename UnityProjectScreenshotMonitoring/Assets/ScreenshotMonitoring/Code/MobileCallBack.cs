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
