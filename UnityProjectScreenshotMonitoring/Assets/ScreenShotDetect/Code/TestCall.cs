using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class TestCall : MonoBehaviour
{
    [SerializeField]
    private MobileCallBack mobileCallBack;

    // Start is called before the first frame update
    void Start()
    {
#if UNITY_ANDROID
        mobileCallBack.Init();//初始化
        mobileCallBack.Shoot += AddCube;//截图调用
#elif UNITY_IOS
        mobileCallBack.Init();//初始化
        mobileCallBack.Shoot += AddCube;//截图调用
#endif

    }

    //生成方块
    private void AddCube()
    {
        var ins = GameObject.CreatePrimitive(PrimitiveType.Cube);
        ins.transform.position = Vector3.zero;
        ins.AddComponent<Rigidbody>();

        Destroy(ins, 5);
    }

    private void OnGUI()
    {
        GUIContent g1 = new GUIContent();
        g1.text = "init";
        var btn1 = GUILayout.Button(g1, GUILayout.Width(200), GUILayout.Height(80));
        if (btn1)
        {
            mobileCallBack.Test();
        }
    }
}
