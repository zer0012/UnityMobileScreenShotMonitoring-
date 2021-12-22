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
        mobileCallBack.Init();//初始化
        mobileCallBack.Shoot += AddCube;//截图调用
    }

    //生成方块
    private void AddCube()
    {
        var ins = GameObject.CreatePrimitive(PrimitiveType.Cube);
        ins.transform.position = Vector3.zero;
        ins.AddComponent<Rigidbody>();

        Destroy(ins, 5);
    }


}
