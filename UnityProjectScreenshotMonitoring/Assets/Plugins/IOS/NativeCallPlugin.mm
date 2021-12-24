//
//  NativeCall.m
//  UnityFramework
//
//  Created by Jack Hu on 2021/12/15.
//

#import <Foundation/Foundation.h>

@interface NativeCallPlugin : NSObject
{
    
}
@end

@implementation NativeCallPlugin

static NativeCallPlugin * _instance;

+(NativeCallPlugin*) Instance
{
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        NSLog(@"Crating NativeCallPlugin Instance");
        _instance =[[NativeCallPlugin alloc] init];
    });
    return _instance;
}

-(void)InstanceShooting
{
    NSOperationQueue *mainQueue = [NSOperationQueue mainQueue];
    [[NSNotificationCenter defaultCenter] addObserverForName:UIApplicationUserDidTakeScreenshotNotification
                                                      object:nil
                                                       queue:mainQueue
                                                  usingBlock:^(NSNotification *note) {
        BackToUnityScene:nil;
    }];
}

-(void)BackToUnityScene:(id)sender{
    UnitySendMessage("PluginCallBack", "Callbake_iosMessage", "Shoot");
}

-(void)Test
{
    NSLog(@"testtesttest");
}

@end



extern "C"
{
    //初始化截图监测
    void InitScreenCaptureMonitoring()
    {
        [[NativeCallPlugin Instance]InstanceShooting];
    }

    void TestCall()
    {
        [[NativeCallPlugin Instance]Test];
    }
}
