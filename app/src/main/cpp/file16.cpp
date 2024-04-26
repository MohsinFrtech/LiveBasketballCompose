#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//


extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray16(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string LiJ = "Z!l5b&&&ZYE!eWVH2un6!pGRce@7uD3lvFTgkove";
    env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(LiJ.c_str()));
    std::string Zvq = "Mm^Q7TCUS5Zn0N1M^1gdc5ckJoCHz7Q!3!uoblP&";
    env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(Zvq.c_str()));
    std::string src = "&&!5lK$NjtOeu$b4601dcnAtEI3EM5WGzbi@oX$w";
    env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(src.c_str()));
    std::string DLz = "!N!#FLFdc588DK5eWO6FpDrhRGQjAxerQcGWv@8c";
    env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(DLz.c_str()));
    std::string iI4 = "D&&Sz8xwFqTTRG5EGBfzRqCc2YrBQRk7^@y%001f";
    env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(iI4.c_str()));
    std::string I9 = "mXmQEVXr5%i!l$sj5bXRmq#2SgpYLRyvBFy1DV1b";
    env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(I9.c_str()));
    std::string guU = "emnJ^NEZlNwLYlmJPOYJ2$!6vD9wMzfF8nNcpkzc";
    env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(guU.c_str()));
    std::string Zc = "qz&jQPyuETROKWE2B!Y4L7hm6c0hvWE$T#&6TPV3";
    env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(Zc.c_str()));
    std::string Lkp = "eP@@$qdrYr3UN$NBvvVh8SPLag^HxHYx#K4aMu$j";
    env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(Lkp.c_str()));
    std::string VRa = "WFPRToSM!3mDZrAD6TVT2%pk6xe^FkWgDU4!P40I";
    env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(VRa.c_str()));

    return strarr;
}