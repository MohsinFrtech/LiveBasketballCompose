#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//

extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray28(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string snL = "XVqLn2f6K0vT&IGtZzioqY5IBmmw8HpTU0jMV6bI";	env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(snL.c_str()));
    std::string wqw = "DZM51mtkJ4kl$BG$Fy81mNNqJwko&meqdyknnTjz";	env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(wqw.c_str()));
    std::string oi2 = "Yr&ZciAz$VR4zVXAR41vQioCDrb9sPjuco^Qbee";	env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(oi2.c_str()));
    std::string bN = "5M@0$sXtQwncioke!WFrFDMISAIsQyuAZh#q1GOX";	env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(bN.c_str()));
    std::string tU = "zeBpK$ADpoWV!171M1QVu57AMLDL@T2N!QwTLwCK";	env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(tU.c_str()));
    std::string uox = "pYHvApbkYk2mmnb8^^XKgQv6SFXWHhNF4@FQmdI!";	env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(uox.c_str()));
    std::string vt = "z&CNYbr9^nam#KOl6uG1!3X3YpKTnE2OASrDkizA";	env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(vt.c_str()));
    std::string Zoy = "n2sj8DnCz0d!oDvcU3X@&VpIopIrqo9d6jMdzqHK";	env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(Zoy.c_str()));
    std::string Pkv = "$yI%k6TFRbioeY%Z$yo@MNbQYLGBX9&JwBfwRO8z";	env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(Pkv.c_str()));
    std::string xk8 = "$0UanP2^FAghRi0b7#K^LE8&63IpNETSGaj6cGog";	env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(xk8.c_str()));

    return strarr;
}