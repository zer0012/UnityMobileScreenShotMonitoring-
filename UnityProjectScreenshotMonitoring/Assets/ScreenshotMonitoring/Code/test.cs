using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class test : MonoBehaviour
{

    int index = 0;
    // Update is called once per frame
    void Update()
    {
        if (index <= 200)
        {
            index++;
            return;
        } 

        
        Debug.Log("Poop");
        index = 0;
    }
}
