#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//


extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray11(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string syL = "XVqLn2f6K0vT&IGtZzeeqY5IBmmw8HpTU0jMV6bI";
    env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(syL.c_str()));
    std::string wPw = "DZM51mtkJ4nW$BG$Fy81mNNqJwko&meqdyknnTjz";
    env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(wPw.c_str()));
    std::string o62 = "Yr&ZciAz$VR4XDVXAR41vQioCDrb9sPjuco^Qbee";
    env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(o62.c_str()));
    std::string FN = "5M@0$sXtQwncvdke!WFrFDMISAIsQyuAZh#q1GOX";
    env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(FN.c_str()));
    std::string PU = "zeBpK$ADpWWV!171M1QVu57AMLDL@T2N!QwTLwCK";
    env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(PU.c_str()));
    std::string u3x = "pYHvApbkYk2mNsb8^^XKgQv6SFXWHhNF4@FQmdI!";
    env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(u3x.c_str()));
    std::string vu = "z&CNYbr9^sam#KOl6uG1!3X3YpKTnE2OASrDkizA";
    env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(vu.c_str()));
    std::string Z3y = "n2sj8DnCz0d!oDrxU3X@&VpIopIrqo9d6jMdzqHK";
    env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(Z3y.c_str()));
    std::string Prv = "$yI%k6TFRbdteY%Z$yo@MNbQYLGBX9&JwBfwRO8z";
    env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(Prv.c_str()));
    std::string xl8 = "$0UanP2^FAMqRi0b7#K^LE8&63IpNETSGaj6cGog";
    env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(xl8.c_str()));

    return strarr;
}