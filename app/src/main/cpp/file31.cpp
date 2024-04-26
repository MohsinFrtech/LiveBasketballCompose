#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//

extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray31(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string ud = "X@1g!nyPE&XF8wYe7&T9yor3#y6jv9gc#RnP1t8Y";	env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(ud.c_str()));
    std::string kmR = "m5^Ajc4CAYmnQ7i3pvXmf037Pp$KBs^55V7$tA$w";	env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(kmR.c_str()));
    std::string Hay = "eil1vvj^&rrokzaaJwe7yQfNBzm7fsiF6xMDz7ux";	env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(Hay.c_str()));
    std::string Oj8 = "jf1pD$lC8%SM&tHa8t!ddtUqomxFLCGXpVq%Cs3r";	env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(Oj8.c_str()));
    std::string RnP = "s6QHeMgy6G0po2E$NuRl#uMB@W0#5mYf6Tez!Joe";	env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(RnP.c_str()));
    std::string pzO = "wpicjF$uuTJ^2n5eNL!ZSEcvp8cGkUPmsOEMSiXj";	env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(pzO.c_str()));
    std::string exn = "W9E1ayozvKJI#xI09cYyu0Zp9Pxw894QSwDC5KtW";	env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(exn.c_str()));
    std::string ImX = "xJVyRNIAtyKVeVH%BZqxNfyO0jnPnvAQ1m5tW7q0";	env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(ImX.c_str()));
    std::string huY = "60CBhkUUnPiG^zbnACs6%QeCdj&YhLHg37&p6t05";	env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(huY.c_str()));
    std::string OpN = "k4SISzXIcUqL63q8Dbks5mukKWgsAY2J^#xU^dSo";	env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(OpN.c_str()));

    return strarr;
}