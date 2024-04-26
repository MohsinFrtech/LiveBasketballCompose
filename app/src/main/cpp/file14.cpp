#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//

extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray14(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string gd = "X@1g!nyPE&XF8wYe7&T9Bnr3#y6jv9gc#RnP1t8Y";
    env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(gd.c_str()));
    std::string kwR = "m5^Ajc4CAYJ6Q7i3pvXmf037Pp$KBs^55V7$tA$w";
    env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(kwR.c_str()));
    std::string HHy = "eil1vvj^&rrJszaaJwe7yQfNBzm7fsiF6xMDz7ux";
    env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(HHy.c_str()));
    std::string O08 = "jf1pD$lC8%SM&tHa8t!ddtUqwyxFLCGXpVq%Cs3r";
    env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(O08.c_str()));
    std::string RZP = "s6QHeMgy6G0uG2E$NuRl#uMB@W0#5mYf6Tez!Joe";
    env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(RZP.c_str()));
    std::string pXO = "wpicjF$uuTJ^2n5eNL!ZSEjFp8cGkUPmsOEMSiXj";
    env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(pXO.c_str()));
    std::string eZn = "W9E1ayozvKJI#xI09cYZq0Zp9Pxw894QSwDC5KtW";
    env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(eZn.c_str()));
    std::string IqX = "xJVyRNIAbIKVeVH%BZqxNfyO0jnPnvAQ1m5tW7q0";
    env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(IqX.c_str()));
    std::string hVY = "60CBhkUUnPiG^zAtACs6%QeCdj&YhLHg37&p6t05";
    env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(hVY.c_str()));
    std::string OeN = "k4SISzXIcUqL63q8Dbks5WtkKWgsAY2J^#xU^dSo";
    env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(OeN.c_str()));

    return strarr;
}