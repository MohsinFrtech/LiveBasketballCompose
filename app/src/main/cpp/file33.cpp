#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//

extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray33(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string LgJ = "Z!l5b&&&ZYE!eWxc2un6!pGRce@7uD3lvFTgkove";	env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(LgJ.c_str()));
    std::string Zsq = "Mm^Q7TCUS5Znvb1M^1gdc5ckJoCHz7Q!3!uoblP&";	env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(Zsq.c_str()));
    std::string sbc = "&&!5lK$NjtOeumn4601dcnAtEI3EM5WGzbi@oX$w";	env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(sbc.c_str()));
    std::string Dmz = "!N!#FLFdc588DK5eWO6xzDrhRGQjAxerQcGWv@8c";	env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(Dmz.c_str()));
    std::string im4 = "D&&Sz8xwFqTTRG5hjBfzRqCc2YrBQRk7^@y%001f";	env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(im4.c_str()));
    std::string b9 = "mXmQEVXr5%i!l$sj5bXRcv#2SgpYLRyvBFy1DV1b";	env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(b9.c_str()));
    std::string gmU = "emnJ^NEZlNwerlmJPOYJ2$!6vD9wMzfF8nNcpkzc";	env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(gmU.c_str()));
    std::string bc = "qz&jQPyuETRsdWE2B!Y4L7hm6c0hvWE$T#&6TPV3";	env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(bc.c_str()));
    std::string Lop = "eP@@$qdryu3UN$NBvvVh8SPLag^HxHYx#K4aMu$j";	env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(Lop.c_str()));
    std::string Vga = "WFPRToSM!3mDjiAD6TVT2%pk6xe^FkWgDU4!P40I";	env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(Vga.c_str()));

    return strarr;
}