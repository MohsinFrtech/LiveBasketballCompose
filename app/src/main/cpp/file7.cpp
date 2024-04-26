#include <jni.h>
#include <string>

//
// Created by FrTech on 21-Jan-23.
//


extern "C"
JNIEXPORT jobjectArray JNICALL
Java_com_example_livebasketballcompose_cppfiles_NativeClass_getStringArray7(JNIEnv *env,jobject thiz) {
    jobjectArray strarr = env->NewObjectArray(10, env->FindClass("java/lang/String"), nullptr);
    std::string dLc = "O!B6$GAAquaSEGWIvT1YTT6vJW2thgRLZ7n3^016";	env->SetObjectArrayElement(strarr, 0, env->NewStringUTF(dLc.c_str()));
    std::string Vy = "!kp%NeSCgNCv17AglttCMnKa$uXZTqzUJqHO#BL@";	env->SetObjectArrayElement(strarr, 1, env->NewStringUTF(Vy.c_str()));
    std::string s1 = "Asyxp&b9EFg0!VV9!QV%m$lnY%&g^Z0a2r@A3^xb";	env->SetObjectArrayElement(strarr, 2, env->NewStringUTF(s1.c_str()));
    std::string xJv = "f^Ub^UO1upjLJcW62GPOYd&3yHRPJcE&IZrtjbj8";	env->SetObjectArrayElement(strarr, 3, env->NewStringUTF(xJv.c_str()));
    std::string zo = "BW4p87@7IMglnqhVdbPtkNVIyq4f0964i6eeOBxZ";	env->SetObjectArrayElement(strarr, 4, env->NewStringUTF(zo.c_str()));
    std::string qrp = "X&QIKlitgbhiwP3S4@c8ZvF@vtgYcf8QqwQw2kt0";	env->SetObjectArrayElement(strarr, 5, env->NewStringUTF(qrp.c_str()));
    std::string dp2 = "X&QIKlitgbhiwP3S4@c8ZvF@vtgYcf8QqwQw2kt0";	env->SetObjectArrayElement(strarr, 6, env->NewStringUTF(dp2.c_str()));
    std::string r05 = "F14YaavhpH7TR^wirEXxKRu!UwLjv@v#LYdgT8kq";	env->SetObjectArrayElement(strarr, 7, env->NewStringUTF(r05.c_str()));
    std::string UN = "i2^rMwnfWwpwSzP3bXg!Q6uUY0MGlDXZJia&Ii&2";	env->SetObjectArrayElement(strarr, 8, env->NewStringUTF(UN.c_str()));
    std::string Rzf = "ZF7tRy068tLoc%lCPo20byHOyO1sHXVZyoSnsQ8T";	env->SetObjectArrayElement(strarr, 9, env->NewStringUTF(Rzf.c_str()));

    return strarr;
}